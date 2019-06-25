package com.example.testsdk

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class TestSdk {

    companion object {
        private val lifecycleListener = object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
                activity.logActivity(SdkActivityEvents.EVENT_PAUSED)
            }

            override fun onActivityResumed(activity: Activity?) {
                activity.logActivity(SdkActivityEvents.EVENT_RESUMED)
            }

            override fun onActivityStarted(activity: Activity?) {
                activity.logActivity(SdkActivityEvents.EVENT_STARTED)
            }

            override fun onActivityDestroyed(activity: Activity?) {
                activity.logActivity(SdkActivityEvents.EVENT_DESTROYED)
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                activity.logActivity(SdkActivityEvents.EVENT_SAVE_STATE)
            }

            override fun onActivityStopped(activity: Activity?) {
                activity.logActivity(SdkActivityEvents.EVENT_STOPPED)
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                activity.logActivity(SdkActivityEvents.EVENT_CREATED)

                activity?.handleFragmentActivity()
            }
        }

        private val fragmentLifecycleListener = object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                f.logFragment(SdkFragmentEvents.EVENT_CREATED)
            }

            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                f.logFragment(SdkFragmentEvents.EVENT_VIEW_CREATED)
            }

            override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
                f.logFragment(SdkFragmentEvents.EVENT_STOPPED)
            }

            override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                f.logFragment(SdkFragmentEvents.EVENT_RESUMED)
            }

            override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                f.logFragment(SdkFragmentEvents.EVENT_ATTACHED)
            }

            override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
                f.logFragment(SdkFragmentEvents.EVENT_PRE_ATTACHED)
            }

            override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                f.logFragment(SdkFragmentEvents.EVENT_DESTROYED)
            }

            override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
                f.logFragment(SdkFragmentEvents.EVENT_SAVE_STATE)
            }

            override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
                f.logFragment(SdkFragmentEvents.EVENT_STARTED)
            }

            override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                f.logFragment(SdkFragmentEvents.EVENT_VIEW_DESTROYED)
            }

            override fun onFragmentPreCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                f.logFragment(SdkFragmentEvents.EVENT_PRE_CREATED)
            }

            override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                f.logFragment(SdkFragmentEvents.EVENT_ACTIVITY_CREATED)
            }

            override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                f.logFragment(SdkFragmentEvents.EVENT_PAUSED)
            }

            override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
                f.logFragment(SdkFragmentEvents.EVENT_DETACHED)
            }
        }

        fun init(application: Application) {

            application.registerActivityLifecycleCallbacks(lifecycleListener)
        }

        private fun Activity?.handleFragmentActivity() {
            if (this is FragmentActivity) {
                this.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleListener, true)
            }
        }

        private fun Any?.canonicalName(): String = this?.javaClass?.canonicalName ?: "Unknown"

        private fun Activity?.logActivity(event: SdkActivityEvents) {
            Log.d(TAG_ANALYTICS, "${event.nameEvent} [${this.canonicalName()}]")
        }

        private fun Fragment?.logFragment(event: SdkFragmentEvents) {
            Log.d(TAG_ANALYTICS, "${event.nameEvent} [${this.canonicalName()}]")
        }
    }
}