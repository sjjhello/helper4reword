package com.example.sjj.help4reword.activies;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sjj.help4reword.R;

/**
 * Created by sjj on 2018/3/14.
 */

public class LoginActivity extends Activity{
    private Button loginButton;
    private TextView register;
    private String username_value;
    private String password_value;
    private EditText username_edit;
    private EditText password_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化，将状态栏和标题栏设为透明
        initView();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_edit.getPaint().setFlags(0);
                username_value = username_edit.getText().toString();
                password_edit.getPaint().setFlags(0);
                password_value = password_edit.getText().toString();
                if (username_value != "" && password_value != ""){
                    if (username_value.equals("sufuring") && password_value.equals("123456")) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();

                    }
                }else if(username_value.isEmpty() || username_value.equals("")){
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else if(password_value.isEmpty() || username_value.equals("")){
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }
                if (username_value.equals("sufuring") && password_value.equals("123456")) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void initView(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        loginButton = (Button) findViewById(R.id.login);
        username_edit = (EditText) findViewById(R.id.username_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        register = (TextView) findViewById(R.id.register);

    }

}
