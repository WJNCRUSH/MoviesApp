package com.cary.moviesapp.application;

import android.app.Application;
import android.content.SharedPreferences;

import com.cary.moviesapp.entity.UserEntity;


/**
 * Created by Administrator on 2019/5/14.
 */

public class MyApplication extends Application {
  public static String IP= null;
  @Override
  public void onCreate() {
    super.onCreate();
    SharedPreferences preferences = getSharedPreferences("conf", MODE_PRIVATE);
    IP= preferences.getString("ip", "");
  }

  private UserEntity.DataBean userBean;

  public UserEntity.DataBean getUserBean() {
    return userBean;
  }

  public void setUserBean(UserEntity.DataBean userBean) {
    this.userBean = userBean;
  }
}
