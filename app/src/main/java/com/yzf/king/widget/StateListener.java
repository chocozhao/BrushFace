package com.yzf.king.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.FrameLayout;

import cn.droidlover.xstatecontroller.XStateController;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * ClaseName：StateListener
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/10/24 10:09
 * Modified By：
 * Fixtime：2017/10/24 10:09
 * FixDescription：
 */

public class StateListener implements XStateController.OnStateChangeListener {

    public interface ChangeListener {
        void animationState(View exitView, View enterView);
    }

    @Override
    public void onStateChange(int oldState, int newState) {

    }

    @Override
    public void animationState(final View exitView, final View enterView) {

        AnimatorSet set = new AnimatorSet();
        final ObjectAnimator enter = ObjectAnimator.ofFloat(enterView, View.ALPHA, 0f, 1f);
        ObjectAnimator exit = ObjectAnimator.ofFloat(exitView, View.ALPHA, 1f, 0f);
        set.playTogether(enter, exit);
        set.setDuration(400);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                exitView.setVisibility(VISIBLE);
                enterView.setVisibility(GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                exitView.setVisibility(GONE);
                enterView.setVisibility(VISIBLE);
                checkView(enterView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        set.start();
    }

    private void checkView(View enterView) {
        int visibleChild = 0;
        FrameLayout parent = (FrameLayout) enterView.getParent();
        int childCount = parent.getChildCount();
        for (int index = 0; index < childCount; index++) {
            if (parent.getChildAt(index).getVisibility() == VISIBLE) {
                visibleChild++;
            }
        }
        if (visibleChild < 1) {
            enterView.setVisibility(VISIBLE);
            enterView.setAlpha(1);
        }
    }

}
