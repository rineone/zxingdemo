package com.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.zxingdemo.utils.zxingUtils;

import androidx.annotation.Nullable;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btCreate;
    private ImageView ivErWeiMa;
    private Button btGetInfo;
    private TextView tvGetInfo;
    private EditText edCreateInfo;
    private Bitmap bit = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ivErWeiMa = findViewById(R.id.iv_erweima);
        btCreate = findViewById(R.id.bt_create);
        btGetInfo = findViewById(R.id.bt_getinfo);
        tvGetInfo = findViewById(R.id.tv_getinfo);
        edCreateInfo = findViewById(R.id.ed_createinfo);
        btCreate.setOnClickListener(this);
        btGetInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_create:
                String info = getInfo(edCreateInfo);
                if (info.equals("")){
                    Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
                    return;
                }
                bit = zxingUtils.getErWeiMa(info);
                if (bit!=null){
                    ivErWeiMa.setImageBitmap(bit);
                }
                break;
            case R.id.bt_getinfo:
                // 创建IntentIntegrator对象
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                // 开始扫描
                intentIntegrator.initiateScan();
                break;
        }
    }

    private String getInfo(EditText ed){
        String res = "";
        try{
            res = ed.getText().toString();
        }catch (Exception e){
        }
        return res;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取解析结果
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "取消扫描", Toast.LENGTH_LONG).show();
            } else {
                String info = "扫描结果为：";
                String res = result.getContents();
                tvGetInfo.setText(info+res);
                Toast.makeText(this, info + res, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



}
