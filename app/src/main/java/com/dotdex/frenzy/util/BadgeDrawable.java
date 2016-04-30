package com.dotdex.frenzy.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.dotdex.frenzy.R;

public class BadgeDrawable extends Drawable {

    private Paint mBadgePaint;
    private String mCount = "";
    private Paint mTextPaint;
    private float mTextSize;
    private Rect mTxtRect = new Rect();
    private boolean mWillDraw = false;

    public BadgeDrawable(Context paramContext) {
        this.mTextSize = paramContext.getResources().getDimension(
                R.dimen.textsize_14);
        this.mBadgePaint = new Paint();
        this.mBadgePaint.setColor(Color.TRANSPARENT);
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Paint.Style.FILL);
        this.mTextPaint = new Paint();
        this.mTextPaint.setColor(paramContext.getResources().getColor(R.color.white));
        this.mTextPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw(Canvas paramCanvas) {
        if (!this.mWillDraw) {
            return;
        }
        Rect localRect = getBounds();
        float width = localRect.right - localRect.left;
        float height = localRect.bottom - localRect.top;
        float circleRadius;
        circleRadius = Math.min(width, height) / 4.0f + 2.5F;
        if (Integer.parseInt(this.mCount) < 10) {
            circleRadius = Math.min(width, height) / 4.0f + 2.5F;
        } else {
            circleRadius = Math.min(width, height) / 4.0f + 4.5F;
        }
        float circleX = width/2.0f ;
        float circleY = height/1.25f-4.5F;

        paramCanvas.drawCircle(circleX, circleY, circleRadius, this.mBadgePaint);
        this.mTextPaint.getTextBounds(this.mCount, 0, this.mCount.length(),
                this.mTxtRect);
        float textY = circleY + (this.mTxtRect.bottom - this.mTxtRect.top) / 2.0F;
        float textX = circleX;
        if (Integer.parseInt(this.mCount) >= 10) {
            textX = textX - 1.0F;
            textY = textY - 1.0F;
        }
        paramCanvas.drawText(this.mCount, textX, textY, this.mTextPaint);
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int paramInt) {
    }

    public void setColorFilter(ColorFilter paramColorFilter) {
    }

    public void setCount(int paramInt) {
        this.mCount = Integer.toString(paramInt);
//        if (paramInt > 0) {
            this.mWillDraw = true;
            invalidateSelf();
//        }
    }

}
