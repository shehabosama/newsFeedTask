package com.example.newsfeedtask.network.configs

import android.content.Context
import android.content.SharedPreferences
import com.example.newsfeedtask.util.Constants

class ConfigServiceImpl(val context: Context, configFileName: String) : ConfigService {

    private val mSharedPreferences: SharedPreferences =
        context.getSharedPreferences(configFileName, Context.MODE_PRIVATE)

    override fun setAccessToken(token: String?) {
        mSharedPreferences.edit().putString(Constants.PREF_ACCESS_TOKEN, token).apply()
    }

    override fun getAccessToken(): String {
        return mSharedPreferences.getString(Constants.PREF_ACCESS_TOKEN, "")!!
    }


    override fun clearUserInfo() {
        mSharedPreferences.edit().remove(Constants.PREF_ACCESS_TOKEN).apply()
    }
}