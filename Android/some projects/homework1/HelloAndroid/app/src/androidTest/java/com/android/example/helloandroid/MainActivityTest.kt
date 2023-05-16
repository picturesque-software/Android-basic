package com.android.example.helloandroid

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Main activity 测试类
 *
 * @author 高超（gaochao.cc）
 * @since 2021/6/13
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun lifecycleTest() {
        val scenario = activityScenarioRule.scenario
        onView(withId(R.id.jumpToChina)).perform(click())
    }
}