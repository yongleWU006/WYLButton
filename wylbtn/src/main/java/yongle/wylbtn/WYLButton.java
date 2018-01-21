package yongle.wylbtn;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


/**
 * 常用通用按钮
 */

@SuppressLint("AppCompatCustomView")
public class WYLButton extends Button {
    private GradientDrawable gradientDrawable;//控件的样式
    private int backColor = 0;//背景色
    private int backColorSelected = 0;//按下后的背景色
    private int backGroundImage = 0;//背景图，只提供了Id
    private int backGroundImageSeleted = 0;//按下后的背景图，只提供了Id
    private int textColor = 0;//文字颜色
    private int textColorSeleted = 0;//按下后的文字颜色
    private float radius = 0;//圆角半径
    private float radius_lt = 0;
    private float radius_rt = 0;
    private float radius_rb = 0;
    private float radius_lb = 0;
    private int shape = GradientDrawable.RECTANGLE;//圆角样式，矩形、圆形等，由于矩形的Id为0，默认为矩形
    private int strokeWidth;//边框宽度
    private int strokeColor;//边框颜色
    private int strokeSelectColor;//边框选择颜色
    private int dashGap;//破折线宽
    private int dasWidth;//破折线间隔
    private boolean isKeep;//点击后颜色保持
    private boolean change = true;
    private int pLeft,pRight,pTop,pBottom;
    private int gradientOrientation;
    private int gradientType;
    private int starColor,starColorP;
    private int endColor,endColorP;
    private int centerColor,centerColorP;

    public WYLButton(Context context) {
        super(context);
        init();
    }

    public WYLButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.WYLButton);
        if (array!=null){
            backColor = array.getColor(R.styleable.WYLButton_background_color,0);
            backColorSelected = array.getColor(R.styleable.WYLButton_press_background_color,0);
            backGroundImage = array.getResourceId(R.styleable.WYLButton_background_image,0);
            backGroundImageSeleted = array.getResourceId(R.styleable.WYLButton_press_background_image,0);
            textColor = array.getColor(R.styleable.WYLButton_text_color,Color.BLACK);
            textColorSeleted = array.getColor(R.styleable.WYLButton_press_text_color,Color.BLACK);
            radius = array.getDimension(R.styleable.WYLButton_radius,0);
            shape = array.getInt(R.styleable.WYLButton_shapeType,0);
            strokeWidth = (int) array.getDimension(R.styleable.WYLButton_stroke_width,0);
            strokeColor = array.getColor(R.styleable.WYLButton_stroke_color,0);
            strokeSelectColor = array.getColor(R.styleable.WYLButton_press_stroke_color,0);
            isKeep = array.getBoolean(R.styleable.WYLButton_keep_color,false);
            pLeft = (int) array.getDimension(R.styleable.WYLButton_padding_left,0);
            pRight = (int) array.getDimension(R.styleable.WYLButton_padding_right,0);
            pTop = (int) array.getDimension(R.styleable.WYLButton_padding_top,0);
            pBottom = (int) array.getDimension(R.styleable.WYLButton_padding_bottom,0);
            dashGap = array.getDimensionPixelOffset(R.styleable.WYLButton_dashGap,0);
            dasWidth = array.getDimensionPixelSize(R.styleable.WYLButton_dashWidth,0);
            gradientOrientation = array.getInt(R.styleable.WYLButton_gradientOrientation,-1);
            starColor = array.getColor(R.styleable.WYLButton_startColor,0);
            centerColor = array.getColor(R.styleable.WYLButton_centerColor,0);
            endColor = array.getColor(R.styleable.WYLButton_endColor,0);
            starColorP = array.getColor(R.styleable.WYLButton_press_startColor,starColor);
            centerColorP = array.getColor(R.styleable.WYLButton_press_centerColor,centerColor);
            endColorP = array.getColor(R.styleable.WYLButton_press_endColor,endColor);
            radius_lt = array.getDimension(R.styleable.WYLButton_radius_lt,0);
            radius_rt = array.getDimension(R.styleable.WYLButton_radius_rt,0);
            radius_rb = array.getDimension(R.styleable.WYLButton_radius_rb,0);
            radius_lb = array.getDimension(R.styleable.WYLButton_radius_lb,0);
            gradientType = array.getInt(R.styleable.WYLButton_gradientType,-1);
            array.recycle();
        }
        init();

    }

    public WYLButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (gradientDrawable == null) {
            gradientDrawable = new GradientDrawable();
        }
        this.setDefault();
        //设置Touch事件
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                if (isKeep){
                    keepColor(event.getAction());
                }else {
                    //按下改变样式
                    setColor(event.getAction());
                }
                //此处设置为false，防止Click事件被屏蔽
                return false;
            }
        });
    }
    //改变样式
    private void setColor(int state){
        if (state == MotionEvent.ACTION_DOWN) {
            //按下
            if (backColorSelected != 0) {
                //先判断是否设置了按下后的背景色int
                gradientDrawable.setColor(backColorSelected);
            }else{
                if (gradientOrientation !=  -1){
                    setGradientColor(starColorP,centerColorP,endColorP);
                }
            }
            if (strokeSelectColor != 0){
                gradientDrawable.setStroke(strokeWidth,strokeSelectColor,dasWidth,dashGap);
            }
            //判断是否设置了按下后文字的颜色
            if (textColorSeleted != 0) {
                setTextColor(textColorSeleted);
            }
            //判断是否设置了按下后的背景图
            if (backGroundImageSeleted != 0) {
                setBackgroundResource(backGroundImageSeleted);
            }


        }
        if (state == MotionEvent.ACTION_UP) {
            //抬起
            if (backColor != 0){
                gradientDrawable.setColor(backColor);
            }else {
                if (gradientOrientation !=  -1){
                    setGradientColor(starColor,centerColor,endColor);
                }
            }
            //如果为设置字体颜色，默认为黑色
            if (strokeColor != 0){
                gradientDrawable.setStroke(strokeWidth,strokeColor,dasWidth,dashGap);
            }
            if (textColor == 0) {
                setTextColor(Color.BLACK);
            }else {
                setTextColor(textColor);
            }
            if (backGroundImage != 0) {
                setBackgroundResource(backGroundImage);
            }
        }
    }

    //改变样式
    private void keepColor(int state){
        if (state == MotionEvent.ACTION_DOWN) {
            if (change){
                //按下
                if (backColorSelected != 0) {
                    //先判断是否设置了按下后的背景色int
                    gradientDrawable.setColor(backColorSelected);
                }else {
                    if (gradientOrientation !=  -1){
                        setGradientColor(starColorP,centerColorP,endColorP);
                    }
                }
                //判断是否设置了按下后文字的颜色
                if (textColorSeleted != 0) {
                    setTextColor(textColorSeleted);
                }

                change = false;
            }else {
                //抬起
                if (backColor != 0){
                    gradientDrawable.setColor(backColor);
                }
                //如果为设置字体颜色，默认为黑色
                if (textColor == 0) {
                    setTextColor(Color.BLACK);
                }else {
                    setTextColor(textColor);
                }
                change = true;
            }
            //判断是否设置了按下后的背景图
            if (backGroundImageSeleted != 0) {
                setBackgroundResource(backGroundImageSeleted);
            }
        }

    }
    /**
     * 设置按钮的背景色,如果未设置则默认为透明
     * @param backColor
     */
    public void setBackColor(int backColor) {
        this.backColor = backColor;
        gradientDrawable.setColor(backColor);
    }
    /**
     * 设置按钮按下后的颜色
     * @param backColorSelected
     */
    public void setBackColorSelected(int backColorSelected) {
        this.backColorSelected = backColorSelected;
    }
    
    /**
     * 设置按钮的背景图
     * @param backGroundImage
     */
    public void setBackGroundImage(int backGroundImage) {
        this.backGroundImage = backGroundImage;
        if (backGroundImage != 0) {
            setBackgroundResource(backGroundImage);
        }
    }
    /**
     * 设置按钮按下的背景图
     * @param backGroundImageSeleted
     */
    public void setBackGroundImageSeleted(int backGroundImageSeleted) {
        this.backGroundImageSeleted = backGroundImageSeleted;
    }
    /**
     * 设置按钮圆角半径大小
     * @param radius
     */
    public void setRadius(float radius) {
        gradientDrawable.setCornerRadius(radius);
    }
    /**
     * 设置按钮文字颜色
     * @param textColor
     */
    public void settextColor(int textColor) {
        this.textColor = textColor;
        setTextColor(textColor);
    }
    /**
     * 设置按钮按下的文字颜色
     * @param textColor
     */
    public void setTextColorSelected(int textColor) {
        this.textColorSeleted = textColor;
    }
    /**
     * 按钮的形状
     * @param shape
     */
    public void setShape(int shape) {
        this.shape = shape;
    }
    /**
     * 按钮的边框
     * @param strokeWidth
     */
    public void setStroke(int strokeWidth, int strokeColor, int dasWidth, int dashGap) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.dasWidth = dasWidth;
        this.dashGap = dashGap;
    }

    //控制渐变方向
    private GradientDrawable.Orientation getOrientation(int gradientOrientation) {
        GradientDrawable.Orientation orientation = null;
        switch (gradientOrientation) {
            case 0:
                orientation = GradientDrawable.Orientation.BL_TR;
                break;
            case 1:
                orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case 2:
                orientation = GradientDrawable.Orientation.BR_TL;
                break;
            case 3:
                orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 4:
                orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case 5:
                orientation = GradientDrawable.Orientation.TL_BR;
                break;
            case 6:
                orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 7:
                orientation = GradientDrawable.Orientation.TR_BL;
                break;
        }
        return orientation;
    }
    //设置按钮渐变背景
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setGradientColor(int... color){
        gradientDrawable.setOrientation(getOrientation(gradientOrientation));

        if (gradientType != -1){
            gradientDrawable.setGradientType(gradientType);
        }
        if (color[0] != 0 && color[1] != 0 && color[2] != 0) {
            gradientDrawable.setColors(new int[]{color[0],
                    color[1], color[2]});
        }else if (color[0] != 0 && color[1] != 0 && color[2] == 0){
            gradientDrawable.setColors(new int[]{color[0], color[1]});
        }else if (color[0] != 0 && color[1] == 0 && color[2] != 0){
            gradientDrawable.setColors(new int[]{color[0], color[2]});
        }else if (color[0] == 0 && color[1] != 0 && color[2] != 0){
            gradientDrawable.setColors(new int[]{color[1], color[2]});
        }
    }

    /**
     * 设置默认值
     */
    @SuppressWarnings("deprecation")
    public void setDefault() {
        if (backColor != 0){
            gradientDrawable.setColor(backColor);
        }else {
            if (gradientOrientation !=  -1){
                setGradientColor(starColor,centerColor,endColor);
            }
        }
        gradientDrawable.setShape(shape);
        if (radius != 0){
            gradientDrawable.setCornerRadius(radius);
        }else {
            gradientDrawable.setCornerRadii(new float[] {
                    radius_lt,radius_lt,
                    radius_rt,radius_rt,
                    radius_rb,radius_rb,
                    radius_lb,radius_lb});
        }
        gradientDrawable.setStroke(strokeWidth,strokeColor,dasWidth,dashGap);
        if (textColor == 0) {
            setTextColor(Color.BLACK);
        }else {
            setTextColor(textColor);
        }
        if (backGroundImage != 0) {
            setBackgroundResource(backGroundImage);
        }
        setBackgroundDrawable(gradientDrawable);
        //设置文字默认居中
        setGravity(Gravity.CENTER);
        //清除button的默认padding值
        this.setPadding(pLeft+strokeWidth,pTop+strokeWidth,pRight+strokeWidth,pBottom+strokeWidth);
    }

    public void clearDrawable() {
        if (gradientDrawable != null){
            gradientDrawable = null;
        }
    }
}
