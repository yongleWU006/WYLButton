# WYLButton

# 通用常见样式按钮

## 使用步骤
1. 添加依赖
```
compile 'com.yonglelib:WYLButton:1.0.0'
```
注意:如果添加依赖报错，在gradle中的allprojects/repositories下添加 maven { url 'https://dl.bintray.com/wuyongle006/maven' }
```
allprojects {
    repositories {
        maven { url 'https://dl.bintray.com/wuyongle006/maven' }
    }
}
```
2. 在布局文件中的使用
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <yongle.wylbtn.WYLButton
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:text="WYLBUTTON"
        app:text_color="@color/colorAccent"
        app:press_text_color="@color/colorPrimary"
        app:stroke_width="2dp"
        app:stroke_color="#000"
        app:press_stroke_color="#999"
        app:dashGap="5dp"
        app:dashWidth="4dp"
        app:radius_lb="20dp"
        app:radius_rt="20dp"
        app:startColor="#fff"
        app:centerColor="#000"
        app:gradientOrientation="RIGHT_LEFT"
        app:gradientType="sweep"
        app:press_startColor="@color/colorPrimary"
        app:press_centerColor="@color/colorAccent"/>
</LinearLayout>
```
3. 在代码中使用
```
public class MainActivity extends AppCompatActivity {
    private WYLButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (WYLButton) findViewById(R.id.wyl_btn);
        btn.setBackColor(Color.parseColor("#ef8200"));
        btn.setStroke(5,Color.parseColor("#000000"),5,5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(it);
            }
        });
    }
}
```
## 使用API
1. 设置按钮的背景色
```
setBackColor(int backColor)
```
2. 设置按钮按下后的背景色
```
setBackColorSelected(int backColorSelected)
```
3. 设置按钮的背景图
```
setBackGroundImage(int backGroundImage) 
```
4. 设置按钮按下的背景图
```
setBackGroundImageSeleted(int backGroundImageSeleted)
```
5. 设置按钮圆角半径大小
```
setRadius(float radius) 
设置所有角的圆角半径

setRadius(float radius_lt,float radius_rt,float radius_rb,float radius_lb)
radius_lt 左上角半径
radius_rt 右上角半径
radius_rb 右下角半径
radius_lb 左下角半径
```
6. 设置按钮文字颜色
```
settextColor(int textColor)
```
7. 设置按钮按下的文字颜色
```
setTextColorSelected(int textColor)
```
8. 按钮的形状
```
setShape(int shape)
```
9. 按钮的边框
```
setStroke(int strokeWidth, int strokeColor, int dasWidth, int dashGap)
strokeWidth 边框宽度
strokeColor 边框颜色
dasWidth 破折线宽度
dashGap 破折线间隔
```
10. 按钮按下的边框颜色
```
setStrokeSelect(int strokeSelectColor)
```
11. 设置按钮渐变背景
```
setGradientColor(int... color)
最多设置3个颜色
```
## 布局属性
1. 按钮背景颜色(设置后23~25不起作用)
```
app:background_color="@color/colorPrimary"
```
2. 按钮按下背景颜色(设置后26~28不起作用)
```
app:press_background_color="@color/colorAccent"
```
3. 按钮文字颜色
```
app:text_color="@color/colorAccent" 
```
4. 按钮按下的文字颜色
```
app:press_text_color="@color/colorPrimary"
```
5. 按钮边框宽度
```
app:stroke_width="2dp"
```
6. 按钮边框颜色
```
app:stroke_color="#000"
```
7. 按钮按下的边框颜色
```
app:press_stroke_color="#999"
```
8. 破折线间隔
```
app:dashGap="5dp"
```
9. 破折线宽度
```
app:dashWidth="4dp"
```
10. 按钮圆角（设置后11~14不起效果）
```
app:radius="20dp"
```
11. 按钮右上圆角
```
app:radius_rt="20dp"
```
12. 按钮右下圆角
```
app:radius_rb="20dp"
```
13. 按钮左上圆角
```
app:radius_lt="20dp"
```
14. 按钮左下圆角
```
app:radius_rb="20dp"
```
15. 按钮背景图
```
app:background_image="@mipmap/ic_launcher"
```
16. 按钮按下的背景图
```
app:press_background_image="@mipmap/ic_launcher"
```
17. 按钮按下不回弹
```
app:keep_color="true"
```
18. 按钮内左边距
```
app:padding_left="5dp"
```
19. 按钮内右边距
```
app:padding_right="5dp"
```
20. 按钮内上边距
```
app:padding_top="5dp"
```
21. 按钮内下边距
```
app:padding_bottom="5dp"
```
22. 按钮形状（默认矩形，LINEAR_GRADIENT--矩形，OVAL--椭圆，LINE--线）
```
app:shapeType="LINEAR_GRADIENT"
```
23. 渐变背景开始颜色
```
app:startColor="#fff"
```
24. 渐变背景中间颜色
```
app:centerColor="#000"
```
25. 渐变背景结束颜色
```
app:endColor="#000"
```
26. 渐变背景按下开始颜色
```
app:press_startColor="@color/colorPrimary"
```
27. 渐变背景按下中间颜色
```
app:press_centerColor="@color/colorAccent"
```
28. 渐变背景按下结束颜色
```
app:press_endColor="@color/colorAccent"
```
30. 渐变方向（RIGHT_LEFT：右-左，LEFT_RIGHT：左-右，BOTTOM_TOP：下-上，TOP_BOTTOM：上-下）
```
app:gradientOrientation="RIGHT_LEFT"
```
31. 渐变方式（默认线性，linear--线性，sweep--锥形，radial--圆周形）
```
app:gradientType="sweep"
```

