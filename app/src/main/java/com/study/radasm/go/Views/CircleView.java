package com.study.radasm.go.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.study.radasm.go.R;

/**
 * Created by RadAsm on 15/5/14.
 */
public class CircleView extends View{
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    private Bitmap mSrc;

    private int mRadius;


    private Paint paint;

    //子控件的宽度和高度
    private int mWidth;
    private int mHeight;

    private static final int USERALPHA = 255;
    private static final int USER_R = 119;
    private static final int USER_G = 98;
    private static final int USER_B = 108;


    public CircleView(Context context) {
        this(context, null);
    }


    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获取到自定义的属性
         */
        //获取到属性集合
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);

        int attrCount = typedArray.getIndexCount();
        for (int i = 0; i < attrCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.CircleView_type1:
                    //默认是圆形的图片
                    type = typedArray.getInt(i, 0);
                    break;
                case R.styleable.CircleView_borderRadius:
                    mRadius = typedArray.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CircleView_src:
                    mSrc= BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(index, 0));
                    break;
                default:
                    break;
            }
        }

        typedArray.recycle();
        init();

    }

    private void init() {
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setARGB(USERALPHA,USER_R,USER_G,USER_B);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 移动应用开发移动应用开发.png
         */

        //获取到父控件给子空间的模式和大小以方便下面的判断
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);

        //如果是精确的话，就将这个值赋给子空间的大小
        if(mode== View.MeasureSpec.EXACTLY){

            mWidth=size;
        }else{
            //padding是内边距
            int desireByImg = getPaddingLeft() + getPaddingRight() + mSrc.getWidth();
            //我们要取得这个desireByImag和size的最小值
            //如果不是精确的话，就按照子控件的大小来进行显示
            if(mode== View.MeasureSpec.AT_MOST){
                mWidth=Math.min(desireByImg,size);
            }
        }
        //测量高度
        mode = View.MeasureSpec.getMode(heightMeasureSpec);
        size = View.MeasureSpec.getSize(heightMeasureSpec);

        //如果是精确的话，就将这个值赋给子空间的大小
        if(mode== View.MeasureSpec.EXACTLY){

            mHeight=size;
        }else{
            //padding是内边距
            int desireByImg = getPaddingTop() + getPaddingBottom() + mSrc.getHeight();
            //我们要取得这个desireByImag和size的最小值
            //如果不是精确的话，就按照子控件的大小来进行显示
            if(mode== View.MeasureSpec.AT_MOST){
                mWidth=Math.min(desireByImg,size);
            }
        }
        //以上测量完毕之后，需要设置大小尺寸
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //按照type类型，画出不同的图形
        switch (type){
            case TYPE_ROUND:

                break;
            case TYPE_CIRCLE:
                //得到宽和高最小的那个去画圆
                int min=Math.min(mWidth,mHeight);
                //创建Bitmap对象,将图片拉伸至整个屏幕
                Bitmap.createScaledBitmap(mSrc,min,min,false);
                //使用canvas来绘制bitmap
                canvas.drawBitmap(mSrc,0,0,paint);
                break;
            default:
                break;
        }

    }
}
