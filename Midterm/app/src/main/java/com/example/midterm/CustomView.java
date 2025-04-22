package com.example.midterm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    public CustomView (Context context) {
        super(context);
    }

    public CustomView (Context context, AttributeSet attrs) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawArc(20, 20, 300, 300, 180, 315, true, paint);
        paint.setColor(Color.RED);
        canvas.drawArc(320, 20, 600, 300, 90, 315, true, paint);
        paint.setColor(Color.GREEN);
        canvas.drawArc(620, 20, 900, 300, 0, 315, true, paint);
    }
}
