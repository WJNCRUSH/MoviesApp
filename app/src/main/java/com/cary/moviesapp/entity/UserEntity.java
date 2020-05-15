package com.cary.moviesapp.entity;

/**
 * Created by Administrator on 2019/5/13.
 */

public class UserEntity {


  /**
   * msg : 登录成功
   * code : 0
   * data : {"uid":3,"username":"qqq"}
   */

  private String msg;
  private int code;
  private DataBean data;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {

    private int id;
    private String username;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }
  }
  /**
   * uid : 3
   * username : qqq
   */

}
