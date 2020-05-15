package com.cary.moviesapp.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.moviesapp.R;
import com.cary.moviesapp.application.MyApplication;
import com.cary.moviesapp.entity.UserEntity;
import com.cary.moviesapp.ui.login.LoginActivity;
import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;


public class RegisterActivity extends Activity {
  EditText et_username;
  EditText et_password;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    Button btn_login = findViewById(R.id.button2);
    TextView tv_register = findViewById(R.id.textView);
    TextView tv_forget = findViewById(R.id.textView2);
    et_username = findViewById(R.id.editText3);
    et_password = findViewById(R.id.editText4);

    btn_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Gson gs = new Gson();
        FinalHttp fh = new FinalHttp();
        String url = "http://"+ MyApplication.IP+":8080/lazyrest/user/register";
        AjaxParams ap = new AjaxParams();
        ap.put("username", et_username.getText().toString());
        ap.put("password", et_password.getText().toString());
        Log.d("aaa",url);
        fh.post(url, ap, new AjaxCallBack<Object>() {
          @Override
          public void onSuccess(Object o) {
            Log.d("aaa",o.toString());
            UserEntity userEntity = gs.fromJson(o.toString(), UserEntity.class);
            if (userEntity.getCode() == 0) {
                Toast.makeText(RegisterActivity.this,userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
              Toast.makeText(RegisterActivity.this,userEntity.getMsg(), Toast.LENGTH_SHORT).show();
            }
          }
          @Override
          public void onFailure(Throwable t, int errorNo, String strMsg) {
            Toast.makeText(RegisterActivity.this,"该账户已经存在", Toast.LENGTH_SHORT).show();
          }
        });
      }
    });

  }


}
