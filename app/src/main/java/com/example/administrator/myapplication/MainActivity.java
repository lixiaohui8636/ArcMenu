package com.example.administrator.myapplication;

import android.os.Handler;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.administrator.myapplication.view.arcmenu.ArcMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView zhongjianIm;
    private boolean centerFlag=true;
    private ArcMenu arcMenu;
    private RadioButton home;
    private RadioButton sq;
    private RadioButton cj;
    private RadioButton personal;
    private  final int[] images={R.mipmap.shuoshuo,R.mipmap.wenwen,R.mipmap.jiaocheng,R.mipmap.huodong};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCenter();//初始化中间的图标及处理实际那
        initView();
    }

    private void initView() {
        RadioGroup radioGroup= (RadioGroup) findViewById(R.id.bottom_radio);
        home= (RadioButton) findViewById(R.id.home1);
        sq= (RadioButton) findViewById(R.id.home2);
        cj= (RadioButton) findViewById(R.id.home3);
        personal= (RadioButton) findViewById(R.id.home4);
        home.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(MainActivity.this,"checkedId:"+checkedId,Toast.LENGTH_SHORT);
            }
        });
    }


    private void initCenter() {
        zhongjianIm= (ImageView) findViewById(R.id.zhongjian);
        zhongjianIm.setOnClickListener(this);
        arcMenu= (ArcMenu) findViewById(R.id.arcmenu);
        int itemCount = images.length;
        for (int i = 0; i < itemCount; i++) {
            ImageView item = new ImageView(this);
            item.setImageResource(images[i]);
            item.setTag(i);
            final int position = i;
            arcMenu.addItem(item,position);
        }
        arcMenu.setOnItemOnClickListenr(new ArcMenu.ItemOnclickListener() {
            @Override
            public void onClick(int tag) {
                zhongjianIm.setImageResource(R.mipmap.zhongjian);
                centerFlag=true;
                switch (tag){
                    case ArcMenu.CENTER_TAG://中间按钮的点击事件
                        Toast.makeText(MainActivity.this,"中间",Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(MainActivity.this,"item"+tag,Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,"item"+tag,Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this,"item"+tag,Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this,"item"+tag,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhongjian:
                if(centerFlag){
                    arcMenu.setVisibility(View.VISIBLE);
                    zhongjianIm.setImageResource(R.mipmap.guanbi);
                    centerFlag=false;
                    arcMenu.showChild();
                }else{
                    zhongjianIm.setImageResource(R.mipmap.zhongjian);
                    centerFlag=true;
                    arcMenu.showChild();
                }
                break;
        }
    }
}
