package com.dotdex.frenzy.util;/**
 * Created by DABBY(3pleMinds) on 02-Mar-16.
 */

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * DABBY(3pleMinds) 02-Mar-16 12:48 PM 2016 03
 * 02 12 48 UnnMobile
 **/
public class FabHideOnScroll extends FloatingActionButton.Behavior {


        public FabHideOnScroll(Context context, AttributeSet attrs) {
            super();
        }

        @Override
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
            super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

            //child -> Floating Action Button
            if (child.getVisibility() == View.VISIBLE && dyConsumed > 0) {
                child.hide();
            } else if (child.getVisibility() == View.GONE && dyConsumed < 0) {
                child.show();
            }
        }

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
            return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }

}
