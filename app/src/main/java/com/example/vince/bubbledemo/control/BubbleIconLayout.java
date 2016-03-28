package com.example.vince.bubbledemo.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.vince.bubbledemo.R;

/**
 * description:模拟海底气泡(icon)上升的自定义控件
 * author:vince
 */
public class BubbleIconLayout extends View {

    private List<Bubble> bubbles = new ArrayList<Bubble>();
    private Random random = new Random();// 生成随机数
    private int width, height;
    private boolean starting = false;
    private Bitmap iconOne;
    private Bitmap iconTwo;
    private Bitmap iconThree;
    private Bitmap iconFour;
    private boolean isVisible = true;//界面是否处于隐藏的状态

    public BubbleIconLayout(Context context) {
        super(context);
    }

    public BubbleIconLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BubbleIconLayout);

        BitmapDrawable drawable_one = (BitmapDrawable)a.getDrawable(R.styleable.BubbleIconLayout_bubble_pic_one);
        BitmapDrawable drawable_two = (BitmapDrawable)a.getDrawable(R.styleable.BubbleIconLayout_bubble_pic_two);
        BitmapDrawable drawable_three = (BitmapDrawable)a.getDrawable(R.styleable.BubbleIconLayout_bubble_pic_three);
        BitmapDrawable drawable_four = (BitmapDrawable)a.getDrawable(R.styleable.BubbleIconLayout_bubble_pic_four);

        iconOne = drawable_one.getBitmap();
        iconTwo = drawable_two.getBitmap();
        iconThree = drawable_three.getBitmap();
        iconFour = drawable_four.getBitmap();
    }

    public BubbleIconLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        if (!starting) {
            starting = true;
            new Thread() {
                public void run() {
                    while (true) {
                        if (!isVisible) {//当activity处于不可见状态时把线程关闭
                            starting = false;
                            break;
                        }
                        try {
                            Thread.sleep(random.nextInt(3) * 500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bubble bubble = new Bubble();
                        int flag = random.nextInt(3);
                        float speedY = random.nextFloat() * 5;
                        while (speedY < 1) {
                            speedY = random.nextFloat() * 5;
                        }
                        bubble.setFlag(flag);
                        bubble.setSpeedY(speedY);

                        switch ( random.nextInt(3)) {//在四个地方随机出现
                            case 0:
                                bubble.setX(width);
                                break;
                            case 1:
                                bubble.setX(width / 3);
                                break;
                            case 2:
                                bubble.setX(width / 3 * 2);
                                break;
                            case 3:
                                bubble.setX(width);
                                break;
                            default:
                                break;
                        }

                        bubble.setY(height);
                        float speedX = random.nextFloat() - 0.5f;
                        while (speedX == 0) {
                            speedX = random.nextFloat() - 0.5f;
                        }
                        bubble.setSpeedX(speedX * 2);
                        bubbles.add(bubble);
                    }
                }
            }.start();
        }

        List<Bubble> list = new ArrayList<Bubble>(bubbles);
        // 依次绘制气泡
        for (Bubble bubble : list) {
            // 碰到上边界从数组中移除
            int i;
            switch (bubble.flag) {
                case 0:
                    if (bubble.getX() - iconOne.getWidth() / 2 <= 0) {
                        bubbles.remove(bubble);
                    } else if (bubble.getX() + iconOne.getWidth() / 2 >= width) {
                        bubbles.remove(bubble);
                    } else {

                        if (bubble.getX() + bubble.getSpeedX() <= iconOne.getWidth() / 2) {
                            bubble.setX(iconOne.getWidth() / 2);
                        } else if (bubble.getX() + bubble.getSpeedX() >= width
                                - iconOne.getWidth() / 2) {
                            bubble.setX(width - iconOne.getWidth() / 2);
                        } else {
                            bubble.setX(bubble.getX() + bubble.getSpeedX());
                        }
                        bubble.setY(bubble.getY() - bubble.getSpeedY());

                        if (bubble.getY() - bubble.getSpeedY() <= 0) {
                            bubbles.remove(bubble);
                        }

                        i = bubbles.indexOf(bubble);
                        if (i >= 0 && i < bubbles.size()) {
                            bubbles.set(i, bubble);
                        }
                        canvas.drawBitmap(iconOne, bubble.getX(), bubble.getY(), null);
                    }
                    break;
                case 1:
                    if (bubble.getX() - iconTwo.getWidth() / 2 <= 0) {
                        bubbles.remove(bubble);
                    } else if (bubble.getX() + iconTwo.getWidth() / 2 >= width) {
                        bubbles.remove(bubble);
                    } else {
                        if (bubble.getX() + bubble.getSpeedX() <= iconTwo.getWidth() / 2) {
                            bubble.setX(iconTwo.getWidth() / 2);
                        } else if (bubble.getX() + bubble.getSpeedX() >= width
                                - iconTwo.getWidth() / 2) {
                            bubble.setX(width - iconTwo.getWidth() / 2);
                        } else {
                            bubble.setX(bubble.getX() + bubble.getSpeedX());
                        }
                        bubble.setY(bubble.getY() - bubble.getSpeedY());

                        if (bubble.getY() - bubble.getSpeedY() <= 0) {
                            bubbles.remove(bubble);
                        }

                        i = bubbles.indexOf(bubble);
                        if (i >= 0 && i < bubbles.size()) {
                            bubbles.set(i, bubble);
                        }

                        canvas.drawBitmap(iconTwo, bubble.getX(), bubble.getY(), null);
                    }
                    break;
                case 2:
                    if (bubble.getX() - iconThree.getWidth() / 2 <= 0) {
                        bubbles.remove(bubble);
                    } else if (bubble.getX() + iconThree.getWidth() / 2 >= width) {
                        bubbles.remove(bubble);
                    } else {
                        if (bubble.getX() + bubble.getSpeedX() <= iconThree.getWidth() / 2) {
                            bubble.setX(iconThree.getWidth() / 2);
                        } else if (bubble.getX() + bubble.getSpeedX() >= width
                                - iconThree.getWidth() / 2) {
                            bubble.setX(width - iconThree.getWidth() / 2);
                        } else {
                            bubble.setX(bubble.getX() + bubble.getSpeedX());
                        }
                        bubble.setY(bubble.getY() - bubble.getSpeedY());
                        if (bubble.getY() - bubble.getSpeedY() <= 0) {
                            bubbles.remove(bubble);
                        }
                        i = bubbles.indexOf(bubble);
                        if (i >= 0 && i < bubbles.size()) {
                            bubbles.set(i, bubble);
                        }

                        canvas.drawBitmap(iconThree, bubble.getX(), bubble.getY(), null);
                    }
                    break;
                case 3:
                    if (bubble.getX() - iconFour.getWidth() / 2 <= 0) {
                        bubbles.remove(bubble);
                    } else if (bubble.getX() + iconFour.getWidth() / 2 >= width) {
                        bubbles.remove(bubble);
                    } else {
                        if (bubble.getX() + bubble.getSpeedX() <= iconFour.getWidth() / 2) {
                            bubble.setX(iconFour.getWidth() / 2);
                        } else if (bubble.getX() + bubble.getSpeedX() >= width
                                - iconFour.getWidth() / 2) {
                            bubble.setX(width - iconFour.getWidth() / 2);
                        } else {
                            bubble.setX(bubble.getX() + bubble.getSpeedX());
                        }
                        bubble.setY(bubble.getY() - bubble.getSpeedY());
                        if (bubble.getY() - bubble.getSpeedY() <= 0) {
                            bubbles.remove(bubble);
                        }
                        i = bubbles.indexOf(bubble);
                        if (i >= 0 && i < bubbles.size()) {
                            bubbles.set(i, bubble);
                        }

                        canvas.drawBitmap(iconFour, bubble.getX(), bubble.getY(), null);
                    }
                    break;
            }
        }
        // 刷新屏幕
        if (isVisible) {
            invalidate();
        }
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    // 内部VO，不需要太多注释吧？
    private class Bubble {

        // 用于标记是哪张图片
        private int flag;
        // 上升速度
        private float speedY;
        // 平移速度
        private float speedX;
        // 气泡x坐标
        private float x;
        // 气泡y坐标
        private float y;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getSpeedY() {
            return speedY;
        }

        public void setSpeedY(float speedY) {
            this.speedY = speedY;
        }

        public float getSpeedX() {
            return speedX;
        }

        public void setSpeedX(float speedX) {
            this.speedX = speedX;
        }

    }
}