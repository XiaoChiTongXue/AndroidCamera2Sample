package com.example.android.camera2basic.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class ZoomBarView extends View {
    private static String TAG = ZoomBarView.class.getSimpleName();
    Paint paint;
    Paint mYellowPaint;
    Paint mVerticalPaint;
    Paint mTextPaint;
    int minNumber = 0;//刻度最小值
    int maxNumber = 50;//刻度最大值
    int mRulerWidth = 80;//刻度宽
    int mSpacing = 20;//刻度之间的间隔
    int mRulerHeight = (maxNumber - minNumber) * mSpacing;//刻度高

    int mCountScale;//当前滑到的刻度值

    int mBaseX = 200;
    int mBaseY = 0;

    private int mMoveY = mRulerHeight;//上下滑动，滑动时y轴坐标
    private int mYellowLineDistance = mSpacing * 3;//黄色线条距离当前滑动位置的距离

    public ZoomBarView(Context context) {
        super(context);
        initView();
    }

    public ZoomBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ZoomBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
      // ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(mRulerWidth, mRulerHeight);
       //lp.setMargins(0,40,0,40);
       //this.setLayoutParams(lp);
       initPaint();
    }


    //@Override
    //protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(MeasureSpec.makeMeasureSpec(mRulerWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(mRulerHeight, MeasureSpec.AT_MOST));
    //}

    private void initPaint(){
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);// 抗锯齿
        paint.setDither(true);//图像抖动处理
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setTextAlign(Paint.Align.CENTER);

        mYellowPaint = new Paint();
        mYellowPaint.setColor(Color.YELLOW);
        mYellowPaint.setAntiAlias(true);// 抗锯齿
        mYellowPaint.setDither(true);//图像抖动处理
        mYellowPaint.setStrokeWidth(6);
        mYellowPaint.setStyle(Paint.Style.FILL);
        mYellowPaint.setTextAlign(Paint.Align.CENTER);

        mVerticalPaint = new Paint();
        mVerticalPaint.setColor(Color.WHITE);
        mVerticalPaint.setAntiAlias(true);// 抗锯齿
        mVerticalPaint.setDither(true);//图像抖动处理
        mVerticalPaint.setStyle(Paint.Style.FILL);
        mVerticalPaint.setStrokeWidth(2);
        mVerticalPaint.setTextAlign(Paint.Align.CENTER);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAntiAlias(true);// 抗锯齿
        mTextPaint.setDither(true);//图像抖动处理
        mTextPaint.setStrokeWidth(3);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(35);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    protected OnScrollListener mScrollListener;

    public interface OnScrollListener {
        void onScaleScroll(int scale);
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.mScrollListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawLine(0, mRuler_height, mRuler_width, mRuler_height, paint);//画横线
        paint.setTextSize(18);
        for (int i = 0,j = 0; i <= maxNumber - minNumber; i++) {
               Log.v(TAG,"----moveY:"+mMoveY+";i: "+i);

               mYellowLineDistance = mMoveY - i*mSpacing;

               if((mYellowLineDistance) > 2*mSpacing){
                 if(mYellowLineDistance <= 3*mSpacing + 1) {//绘制第一条黄线
                     mYellowPaint.setStrokeWidth(6);
                     canvas.drawLine(mBaseX - 5, i * mSpacing, mBaseX + 45, i * mSpacing, mYellowPaint);
                 }else if(mYellowLineDistance <= 4*mSpacing + 1){//绘制第二条黄线
                     mYellowPaint.setStrokeWidth(8);
                     canvas.drawLine(mBaseX - 9, i * mSpacing, mBaseX + 49, i * mSpacing, mYellowPaint);
                 }else if(mYellowLineDistance <= 5*mSpacing + 1){//绘制第三条黄线
                     mYellowPaint.setStrokeWidth(6);
                    canvas.drawLine(mBaseX - 5, i * mSpacing, mBaseX + 45, i * mSpacing, mYellowPaint);
                 }else{
                     canvas.drawLine(mBaseX, i * mSpacing, mBaseX+40, i * mSpacing, paint);
                 }
               } else {
                   canvas.drawLine(mBaseX, i * mSpacing, mBaseX+40, i * mSpacing, paint);
               }
        }

        for (int j = mRulerHeight; j >= mMoveY;j--){
            canvas.drawLine(mBaseX, j,mBaseX+40,j,mVerticalPaint);
        }

        //滑动的刻度
         mCountScale = (mRulerHeight - mMoveY)/mSpacing;
        //需要显示的刻度
        canvas.drawText(mCountScale+" X",50,500,mTextPaint);
        if (mScrollListener != null) { //回调方法
          mScrollListener.onScaleScroll(mCountScale);
        }
    }

    //int deltaY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMoveY = (int)event.getY();

                //postInvalidate();

                return true;
            case MotionEvent.ACTION_MOVE:
              //  deltaY = (int)event.getY()-moveY;
               // setTranslationY(deltaY);

                mMoveY = (int)event.getY();
                //Log.v(TAG,"onTounchEvent();moveY:"+moveY);
                postInvalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }
}
