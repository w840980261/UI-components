package com.example.wyq.ch3;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View;

public class Main2Activity extends AppCompatActivity {
    private void showLayoutDialog() {
        //加载布局并初始化组件
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog,null);
//        TextView dialogText = (TextView) dialogView.findViewById(R.id.dialog_text);
        Button dialogBtnSignIn = (Button) dialogView.findViewById(R.id.dialog_btn_signIn);
        Button dialogBtnCancel = (Button) dialogView.findViewById(R.id.dialog_btn_cancel);

        final AlertDialog.Builder layoutDialog = new AlertDialog.Builder(this);
//        layoutDialog.setTitle("11");
//        layoutDialog.setIcon(R.mipmap.header_logo);

        layoutDialog.setView(dialogView);

        //设置组件
        dialogBtnSignIn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialogBtnCancel .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        layoutDialog.create().show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        showLayoutDialog();
    }
}
