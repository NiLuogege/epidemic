package com.songyuan.epidemic.globle

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.songyuan.epidemic.R
import com.songyuan.epidemic.base.BaseActivity
import com.songyuan.epidemic.utils.Routes
import com.xianghuanji.jsbridge.BridgeHandler
import com.xianghuanji.jsbridge.BridgeWebView
import com.xianghuanji.jsbridge.CallBackFunction
import com.xianghuanji.jsbridge.DefaultHandler

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        webView = findViewById(R.id.webView)
        progressbar = findViewById(R.id.progressbar)
        webView.setDefaultHandler(DefaultHandler())
        progressbar?.isIndeterminate = false

        webView.webChromeClient = client

        webView.registerHandler("scanIdCord") { data, function ->
            function.onCallBack("submitFromWeb exe, response data 中文 from Java")
        }

        webView.loadUrl(url)
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
        if (requestCode == RESULT_CODE) {
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

        }
    }
}