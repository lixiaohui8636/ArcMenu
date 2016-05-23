/*
 * Copyright (C) 2012 Capricorn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.administrator.myapplication.view.arcmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.administrator.myapplication.R;

/**
 * A custom view that looks like the menu in <a href="https://path.com">Path
 * 2.0</a> (for iOS).
 *
 * @author Capricorn
 *
 */
public class ArcMenu extends RelativeLayout {
    private ArcLayout mArcLayout;

    private ImageView mHintView;
    private ImageView ctroLayout;
    private ItemOnclickListener onItemClickListener;
    public static final int CENTER_TAG=-1;

    public ArcMenu(Context context) {
        super(context);
        init(context);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        applyAttrs(attrs);
    }

    private void init(Context context) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.arc_menu, this);
        mArcLayout = (ArcLayout) findViewById(R.id.item_layout);
        ctroLayout= (ImageView) findViewById(R.id.control_hint);
        final ViewGroup controlLayout = (ViewGroup) findViewById(R.id.control_layout);
        controlLayout.setClickable(true);
        controlLayout.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                }

                return false;
            }
        });

        mHintView = (ImageView) findViewById(R.id.control_hint);
        mHintView.setOnClickListener(onClickListener);
    }

    private void applyAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ArcLayout, 0, 0);

            float fromDegrees = a.getFloat(R.styleable.ArcLayout_fromDegrees, ArcLayout.DEFAULT_FROM_DEGREES);
            float toDegrees = a.getFloat(R.styleable.ArcLayout_toDegrees, ArcLayout.DEFAULT_TO_DEGREES);
            mArcLayout.setArc(fromDegrees, toDegrees);

            int defaultChildSize = mArcLayout.getChildSize();
            int newChildSize = a.getDimensionPixelSize(R.styleable.ArcLayout_childSize, defaultChildSize);
            mArcLayout.setChildSize(newChildSize);
            a.recycle();
        }
    }

    public void addItem(View item,int tag) {
        mArcLayout.addView(item);
        item.setTag(tag);
        item.setOnClickListener(onClickListener);
    }
    private static Animation createHintSwitchAnimation(final boolean expanded) {
        AlphaAnimation alphaAnimation=new AlphaAnimation(expanded?1f:0f,expanded?0f:1f);
        alphaAnimation.setStartOffset(0);
        alphaAnimation.setDuration(200);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setFillAfter(true);

        return alphaAnimation;
    }

    public void showChild(){
        mHintView.startAnimation(createHintSwitchAnimation(mArcLayout.isExpanded()));//中间的旋转动画
        mArcLayout.switchState(true);
    }
  public  interface  ItemOnclickListener{
       void onClick(int tag);
   }
    public void setOnItemOnClickListenr(ItemOnclickListener onItemOnClickListenr){
            this.onItemClickListener=onItemOnClickListenr;
    }
    private OnClickListener onClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null){
                mArcLayout.switchState(true);
                mHintView.startAnimation(createHintSwitchAnimation(true));
                onItemClickListener.onClick(v.getId()==R.id.control_hint?CENTER_TAG:(int) v.getTag());
            }
        }
    };
}
