package com.example.mydrawingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

class Circle {
    float x, y, radius;
    int color;

    Circle(float x, float y, int color, float radius) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.radius = radius;
    }
}

public class DrawingView extends View {
    private Paint paint = new Paint();
    private List<Circle> circles = new ArrayList<>();
    private final String TAG = "test";

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow() 호출됨");
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        Log.d(TAG, "onMeasure() called 호출됨 width: " + width + " height: " + height);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 일반적으로 ViewGroups에만 재정의됨
        Log.d(TAG, "onLayout() 호출됨");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Circle circle : circles) {
            paint.setColor(circle.color);
            canvas.drawCircle(circle.x, circle.y, circle.radius, paint);
        }
        Log.d(TAG, "onDraw() 호출됨");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d(TAG, "dispatchDraw() 호출됨");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 터치 했을 때
            int color = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
            float initialRadius = 50;
            // 반지름을 50으로 초기 설정
            circles.add(new Circle(event.getX(), event.getY(), color, initialRadius));
            invalidate(); // 변경 사항 알림
            Log.d(TAG, "invalidate() 호출됨");
            return true;
        }
        return false;
    }

    public void changeCircleSize() {
        for (Circle circle : circles) {
            circle.radius += 10; // 원의 반지름을 10 증가
        }
        requestLayout(); // 레이아웃 재계산을 요청
        Log.d(TAG, "requestLayout() 실행");
        invalidate(); // 뷰의 내용을 다시 그림
        Log.d(TAG, "invalidate() 실행");
    }
}
