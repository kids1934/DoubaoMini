package com.example.doubaomini.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.PowerManager
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.MutableLiveData

/**
 * description:
 * @author fanxiaoshuang
 * date: 2024/7/5
 */
object ScreenUtil : BroadcastReceiver() {
    private val _isScreenOn = MutableLiveData<Boolean>()
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            /* 亮屏 */
            Intent.ACTION_SCREEN_ON -> {
                LogUtil.d("ScreenUtil", "screen on")
                _isScreenOn.postValue(true)
            }

            /* 熄屏 */
            Intent.ACTION_SCREEN_OFF -> {
                LogUtil.d("ScreenUtil", "screen off")
                _isScreenOn.postValue(false)
            }
        }
    }

    fun registerReceiver(context: Context) {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        context.registerReceiver(this, filter)
    }

    fun unregisterReceiver(context: Context) {
        context.unregisterReceiver(this)
    }

    fun isScreenOn(context: Context): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        LogUtil.d("ScreenUtil", "isSystemScreenOn: ${powerManager.isInteractive}")
        return powerManager.isInteractive
    }

    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        return getMetrics(context).heightPixels
    }
    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        return getMetrics(context).widthPixels
    }
    @JvmStatic
    fun getDensityDpi(context: Context): Int {
        return getMetrics(context).densityDpi
    }

    fun getMetrics(context: Context): DisplayMetrics {
        val metric = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getRealMetrics(metric)
        return metric
    }
    @JvmStatic
    fun setFullScreen(window: Window) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

}