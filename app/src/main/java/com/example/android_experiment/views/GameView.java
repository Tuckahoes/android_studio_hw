package com.example.android_experiment.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.android_experiment.R;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private float touchedX;
    private float touchedY;
    private boolean isTouched=false;

    public GameView(Context context) {
        super(context);
        initView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_UP==event.getAction())
        {
            touchedX = event.getRawX();
            touchedY = event.getRawY();
            Log.i("testtouched",""+touchedX);
            isTouched = true;
        }
        Log.i("test","move action");
        return true;
    }

    private void initView()
    {
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
    }

    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread=null;

    private ArrayList<Spriter> spriterArrayList=new ArrayList<>();
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        for(int i=0;i<8;++i)
        {
            Spriter spriter=new Spriter(this.getContext());
            spriter.setX(i*50);
            spriter.setY(i*50);
            spriter.setDirection((float) (Math.random()*2*Math.PI));
            spriterArrayList.add(spriter);
        }

        drawThread=new DrawThread();
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        drawThread.stopThread();
    }


    class DrawThread extends Thread {
        private boolean isDrawing=true;

        public void stopThread()
        {
            isDrawing=false;

            try {
                join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            Paint bgpaint = new Paint(); //新建一个画笔对象
            bgpaint.setAntiAlias(true);//抗锯齿功能
            bgpaint.setColor(Color.RED);  //设置画笔颜色
            bgpaint.setStyle(Paint.Style.STROKE);//设置填充样式
            bgpaint.setStrokeWidth(30);//设置画笔宽度 ，单位px

            //将图片转化成 bitmap 对象
            Bitmap bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
            int hitCount=0;
            while(isDrawing)
            {
                Canvas canvas =null;
                try {

                    canvas = surfaceHolder.lockCanvas();
                    //canvas.drawColor(Color.WHITE);
                    canvas.drawBitmap(bgBitmap,0,0,bgpaint);

                    if(isTouched) {
                        float tempX = touchedX;
                        float tempY = touchedY;
                        isTouched = false;
                        for (Spriter spriter : spriterArrayList) {
                            if(spriter.isTouched(tempX, tempY))hitCount++;
                        }
                    }
                    Paint textPaint = new Paint();
                    textPaint.setColor(Color.WHITE);
                    textPaint.setStrokeWidth(50);
                    textPaint.setTextSize(60);
                    canvas.drawText("SCORE: "+hitCount+"  !",50,50,textPaint);

                    for (Spriter spriter: spriterArrayList) {
                        spriter.move(canvas.getHeight(), canvas.getWidth());
                    }
                    for (Spriter spriter: spriterArrayList) {
                        spriter.draw(canvas);
                    }
                }
                catch(Exception e)
                {

                }
                finally {
                    if(null!=canvas)surfaceHolder.unlockCanvasAndPost(canvas);
                }


                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //drawing
            }
        }
    }
}
