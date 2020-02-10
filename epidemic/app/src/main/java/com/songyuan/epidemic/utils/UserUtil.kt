package com.songyuan.epidemic.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.songyuan.epidemic.App

/**
 * Created by admin on 2017/2/9.
 */
class UserUtil {

    companion object {
        private const val PREF_NAME = SPUtil.EPIDEMIC_APP
        private val USER_ID = "user_id"
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


        @JvmStatic
        fun isUserLogin(): Boolean {
            return StringUtils.isNotEmpty(userId)
        }

    }
}