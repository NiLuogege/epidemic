package com.songyuan.epidemic.globle

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.niluogege.yunmaiocr.bean.Idcard
import com.niluogege.yunmaiocr.idcard.CameraActivity
import com.songyuan.epidemic.BuildConfig
import com.songyuan.epidemic.R
import com.songyuan.epidemic.base.BaseActivity
import com.songyuan.epidemic.utils.*
import com.songyuan.epidemic.utils.WebViewUtils.setCookie
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xianghuanji.jsbridge.*
import com.yunmai.cc.idcard.vo.IdCardInfo
import java.io.File
import java.io.FileInputStream
import java.nio.charset.Charset

/**
 * Created by niluogege on 2020/2/11.
 */
@Route(path = Routes.A_BROWSER)
class BrowserActivity : BaseActivity() {


    @Autowired
    @JvmField
    var url: String? = ""


    lateinit var webView: BridgeWebView
    private var RESULT_CODE = 0

    private var progressbar: ProgressBar? = null

    internal var mUploadMessage: ValueCallback<Uri>? = null
    internal var mUploadMessageArray: ValueCallback<Array<Uri>>? = null
    var rxPermissions: RxPermissions? = null
    var scanIdCordFunction: CallBackFunction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        rxPermissions = RxPermissions(this)

        webView = findViewById(R.id.webView)
        progressbar = findViewById(R.id.progressbar)
        webView.setDefaultHandler(DefaultHandler())
        progressbar?.isIndeterminate = false

        webView.webChromeClient = client


        val webSettings = webView.settings
        //允许webview对文件的操作
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.allowUniversalAccessFromFileURLs = true
            webSettings.allowFileAccessFromFileURLs = true
        }
        webSettings.allowFileAccess = true


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        initCookie(Uri.parse(url))


        webView.registerHandler("scanIdCord") { data, function ->
            LogUtil.e("调用相机")
            scanIdCord()
            scanIdCordFunction = function
        }

        webView.registerHandler("finish") { data, function ->
            ToastUtils.show("录入成功")
            finish()
        }

        webView.loadUrl(url)

        LogUtil.e("url=${url}")
    }

    override fun onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun initCookie(uri: Uri?) {
        if (uri != null) {
            if (UserUtil.isUserLogin()) {
                LogUtil.d("用户登陆")
                WebViewUtils.setLoginCookie(uri.host, this)
            } else {
                LogUtil.d("用户没登陆")
                WebViewUtils.cleanLoginCookie(uri.host, this)
            }
        }
    }


    private val client = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress == 100) {
                progressbar?.visibility = View.GONE
            } else {
                if (progressbar?.visibility == View.GONE) {
                    progressbar?.visibility = View.VISIBLE
                }
                progressbar?.progress = newProgress
            }
            super.onProgressChanged(view, newProgress)
        }


        fun openFileChooser(
            uploadMsg: ValueCallback<Uri>,
            AcceptType: String,
            capture: String
        ) {
            this.openFileChooser(uploadMsg)
        }

        fun openFileChooser(uploadMsg: ValueCallback<Uri>, AcceptType: String) {
            this.openFileChooser(uploadMsg)
        }

        fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
            mUploadMessage = uploadMsg
            pickFile()
        }

        override fun onShowFileChooser(
            webView: WebView,
            filePathCallback: ValueCallback<Array<Uri>>,
            fileChooserParams: WebChromeClient.FileChooserParams
        ): Boolean {
            mUploadMessageArray = filePathCallback
            pickFile()
            return true
        }
    }


    fun pickFile() {
        val chooserIntent = Intent(Intent.ACTION_GET_CONTENT)
        chooserIntent.type = "image/*"
        startActivityForResult(chooserIntent, RESULT_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == RESULT_CODE) {//选择文件
            if (null == mUploadMessage && null == mUploadMessageArray) {
                return
            }
            if (null != mUploadMessage && null == mUploadMessageArray) {
                val result =
                    if (intent == null || resultCode != Activity.RESULT_OK) null else intent.data
                mUploadMessage?.onReceiveValue(result)
                mUploadMessage = null
            }

            if (null == mUploadMessage && null != mUploadMessageArray) {
                val result =
                    if (intent == null || resultCode != Activity.RESULT_OK) null else intent.data
                if (result != null) {
                    mUploadMessageArray?.onReceiveValue(arrayOf(result))
                }
                mUploadMessageArray = null
            }

        } else if (resultCode == 200) {//身份证识别完毕
            if (intent != null) {
                val idCardInfo = intent.getSerializableExtra("idcardinfo") as IdCardInfo
                if (idCardInfo != null) {
                    pushIdCardInfo(idCardInfo)
                }

            }

        }
    }

    private fun pushIdCardInfo(idCardInfo: IdCardInfo) {
        val cardStr = String(idCardInfo.charInfo, Charset.forName("gbk"))
        val idcard = JSON.parseObject(cardStr, Idcard::class.java)

        val json = JSONObject()
        if (idcard != null) {
            json["name"] = idcard.name?.value
            json["sex"] = idcard.sex?.value
            json["folk"] = idcard.folk?.value
            json["birt"] = idcard.birt?.value
            json["addr"] = idcard.addr?.value
            json["num"] = idcard.num?.value
            json["issue"] = idcard.issue?.value
            json["valid"] = idcard.valid?.value
            json["type"] = idcard.type?.value
            json["cover"] = idcard.cover?.value
            json["headPath"] = BridgeWebViewClient.LOCAL_IMAGE_KEY + idCardInfo.headPath
            json["imgPath"] = BridgeWebViewClient.LOCAL_IMAGE_KEY + idCardInfo.imgPath
        }
        scanIdCordFunction?.onCallBack(json.toJSONString())
    }


    @SuppressLint("CheckResult")
    private fun scanIdCord() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rxPermissions
                ?.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE)
                ?.subscribe {
                }
        }

        rxPermissions?.request(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            ?.subscribe { granted ->
                LogUtil.e("权限请求")
                if (granted) {
                    val intent = Intent(this@BrowserActivity, CameraActivity::class.java)
                    startActivityForResult(intent, 110)
                    overridePendingTransition(R.anim.stop, R.anim.start)
                } else {
                    ToastUtils.show("请开通相机权限，否则无法正常使用！")
                }
            }
    }
}