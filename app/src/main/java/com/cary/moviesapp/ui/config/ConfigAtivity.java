package com.cary.moviesapp.ui.config;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.moviesapp.R;
import com.cary.moviesapp.application.MyApplication;


public class ConfigAtivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_config_ativity);
    final TextView tv_ip = findViewById(R.id.textView28);
    final TextView et_ip = findViewById(R.id.editText);
    Button btn = findViewById(R.id.button16);
    SharedPreferences preferences = getSharedPreferences("conf",MODE_PRIVATE);
    String ip = preferences.getString("ip", "");
    tv_ip.setText(ip);

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences preferences = getSharedPreferences("conf",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("ip",et_ip.getText().toString());
        edit.commit();
        MyApplication.IP=et_ip.getText().toString();
        tv_ip.setText(et_ip.getText().toString());
        Toast.makeText(ConfigAtivity.this, "配置成功", Toast.LENGTH_SHORT).show();
      }
    });
    

  }
}
