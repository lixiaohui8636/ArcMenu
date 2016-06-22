# ArcMenu
  散开式菜单
  需要的可以下载参考
-----------------------------------------------------------------------------------------------------------------------------
#代码说明
####layout引用
```xml
 <com.example.administrator.myapplication.view.arcmenu.ArcMenu
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/arcmenu"
        android:visibility="invisible"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_above="@+id/zhongjian"
        app:childSize="60dp"
        android:layout_marginBottom="-80dp"
        >
    </com.example.administrator.myapplication.view.arcmenu.ArcMenu>
```
```java
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
        initCenter();//初始化中间的图标及处理点击事件
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
```
-----------------------------------------------------------------------------------------------------------------------------
#效果图
![image](https://github.com/lixiaohui8636/ArcMenu/blob/master/aaa.png)
