package com.news2day.webviewtest.helpers

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class SwipeRefresh(context: Context, attrs: AttributeSet?) : SwipeRefreshLayout(context, attrs) {
    private var mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    private var mPrevx = 0f
    private var mPrevy = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mPrevx = MotionEvent.obtain(ev).x
                mPrevy = MotionEvent.obtain(ev).y
            }

            MotionEvent.ACTION_MOVE -> {
                val evX = ev.x
                val evy = ev.y
                val xDiff = Math.abs(evX - mPrevx)
                val yDiff = Math.abs(evy - mPrevy)
                if (xDiff > mTouchSlop && xDiff > yDiff) {
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
}