package com.example.wyq.ch3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = (TextView) findViewById(R.id.editText);
        // 为文本框注册上下文菜单
        registerForContextMenu(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        //装填R.Menu.my_menu菜单，并添加到menu中
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.isCheckable()){
            //勾选菜单项
            item.setCheckable(true);
        }
        //switch 判断单击哪个菜单项，并有针对性的做出响应
        switch (item.getItemId()){
            case R.id.font_10:
                textView.setTextSize(10 * 2);
                break;
            case R.id.font_16:
                textView.setTextSize(16 * 2);
                break;
            case R.id.font_20:
                textView.setTextSize(20 * 2);
                break;
            case R.id.plain_item:
                Toast.makeText(getApplicationContext(), "普通菜单项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.red_font:
                textView.setTextColor(0xFFFF0000);
                break;
            case R.id.black_font:
                textView.setTextColor(0xFF000000);
                break;
        }
        return true;
    }
}
