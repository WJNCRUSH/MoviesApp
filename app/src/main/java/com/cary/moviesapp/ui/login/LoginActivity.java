package com.cary.moviesapp.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.moviesapp.MainActivity;
import com.cary.moviesapp.R;
import com.cary.moviesapp.application.MyApplication;
import com.cary.moviesapp.entity.UserEntity;
import com.cary.moviesapp.ui.config.ConfigAtivity;
import com.cary.moviesapp.ui.register.RegisterActivity;
import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

public class LoginActivity extends Activity {
  EditText et_username;
  EditText et_password;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    Button btn_login = findViewById(R.id.button2);
    final CheckBox cb_admin = findViewById(R.id.checkBox8);
    TextView tv_register = findViewById(R.id.textView);
    TextView tv_forget = findViewById(R.id.textView2);
    Button tt = findViewById(R.id.button17);
    tt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i=new Intent(LoginActivity.this, ConfigAtivity.class);
        startActivity(i);
      }
    });
    et_username = findViewById(R.id.editText3);
    et_password = findViewById(R.id.editText4);
    tv_register.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
      }
    });
    btn_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Gson gs = new Gson();
        FinalHttp fh = new FinalHttp();
        if(!cb_admin.isChecked()){
          String url = "http://" + MyApplication.IP + ":8080/lazyrest/user/login";
          AjaxParams ap = new AjaxParams();
          ap.put("username", et_username.getText().toString());
          ap.put("password", et_password.getText().toString());
          fh.post(url, ap, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
              Log.d("LoginActivity", o.toString());
              UserEntity userEntity = gs.fromJson(o.toString(), UserEntity.class);
              if (userEntity.getCode() == 0) {
                MyApplication application = (MyApplication) getApplication();
                application.setUserBean(userEntity.getData());
                Intent i=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
              } else {
                Toast.makeText(LoginActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
              }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
              Toast.makeText(LoginActivity.this, "用户名密码错误", Toast.LENGTH_SHORT).show();
            }
          });
        }
      }
    });

  }


}
