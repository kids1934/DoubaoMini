package com.example.doubaomini

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.doubaomini.utils.ContextUtil
import com.example.doubaomini.utils.LogUtil
import com.example.doubaomini.utils.ScreenUtil
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class ActivityLifecycleObserver: Application.ActivityLifecycleCallbacks {
    val TAG = "LectureActivityLifecycleObserver"

    /* 管理多页面Bot的全部Activity,在退出意图收到的时候，finish掉所有的activity */
    private val ACTIVITIES = ArrayList<WeakReference<Activity>>()

    private val mActivityStartCount = AtomicInteger(0)

    private val isAppBackground = AtomicBoolean(true)

    private var appStartTime = 0L

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        ACTIVITIES.add(WeakReference<Activity>(activity))
    }

    override fun onActivityStarted(activity: Activity) {
        mActivityStartCount.incrementAndGet()
        if (isApplicationToForeground()) {
            isAppBackground.set(false)
        }
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
        mActivityStartCount.decrementAndGet()
        if (isApplicationToBackground()) {
            isAppBackground.set(true)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        val iterator = ACTIVITIES.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (next.get() === activity) {
                iterator.remove()
            }
        }
    }

    /**
     * onStop的时候进行调用判断
     * @return 当前应用是否退到后台
     */
    private fun isApplicationToBackground(): Boolean {
        return ScreenUtil.isScreenOn(ContextUtil.getContext()) && mActivityStartCount.get() == 0
    }

    /**
     * onStart的时候进行调用判断
     * @return 当前应用是否在前台
     */
    private fun isApplicationToForeground(): Boolean {
        LogUtil.i(TAG, "isApplicationToForeground isAppBackground:${isAppBackground.get()} activityStartCount:${mActivityStartCount.get()}")
        return isAppBackground.get() && mActivityStartCount.get() == 1
    }
}