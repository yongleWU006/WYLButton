package yongle.wylbutton;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import yongle.wylbtn.WYLButton;

public class MainActivity extends AppCompatActivity {
    private WYLButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable[] layers = new Drawable[3];
        layers[0] = getResources().getDrawable(R.mipmap.ic_launcher);
        layers[1] = getResources().getDrawable(R.mipmap.ic_launcher);
        layers[2] = getResources().getDrawable(R.mipmap.ic_launcher);
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(1, 10, 10, 0, 0);
        layerDrawable.setLayerInset(2, 20, 20, 0, 0);
        ((ImageView) findViewById(R.id.image)).setImageDrawable(layerDrawable);
        btn = (WYLButton) findViewById(R.id.wyl_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(it);
            }
        });
    }
}
