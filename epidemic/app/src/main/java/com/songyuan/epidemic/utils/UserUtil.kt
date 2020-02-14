package com.songyuan.epidemic.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.songyuan.epidemic.App
import com.songyuan.epidemic.mvvm.model.Address
import com.songyuan.epidemic.mvvm.model.LoginInfo

/**
 * Created by admin on 2017/2/9.
 */
class UserUtil {

    companion object {
        private const val PREF_NAME = SPUtil.EPIDEMIC_APP
        private val USER_ID = "user_id"
        private val CS_ID = "cs_id"
        private val CS_NAME = "cs_name"
        private val QR_URL = "qr_url"
        private val sp: SharedPreferences
                by lazy {
                    App.context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
                }

        fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
            val editor = edit()
            editor.func()
            editor.apply()
        }


        @JvmStatic
        var userId: String?
            get() {
                return sp.getString(USER_ID, "")
            }
            set(value) {
                sp.edit {
                    putString(USER_ID, value)
                }
            }


        var qrUrl: String?
            get() {
                return sp.getString(QR_URL, "")
            }
            set(value) {
                sp.edit {
                    putString(QR_URL, value)
                }
            }

        var csId: String?
            get() {
                return sp.getString(CS_ID, "")
            }
            set(value) {
                sp.edit {
                    putString(CS_ID, value)
                }
            }

        var csName: String?
            get() {
                return sp.getString(CS_NAME, "")
            }
            set(value) {
                sp.edit {
                    putString(CS_NAME, value)
                }
            }


        fun setLoginInfo(info: LoginInfo) {
            userId = info.cpId
        }

        fun setAddress(info: Address) {
            csId = info.csId
            csName = info.csName
            qrUrl = info.csURL
        }


        @JvmStatic
        fun isUserLogin(): Boolean {
            return StringUtils.isNotEmpty(userId)
        }

    }
}