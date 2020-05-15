package com.cary.moviesapp.ui.seat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.moviesapp.R;
import com.cary.moviesapp.application.MyApplication;
import com.cary.moviesapp.entity.ChangciListEntity;
import com.cary.moviesapp.entity.MoviesEntity;
import com.cary.moviesapp.entity.SeatListEntity;
import com.cary.moviesapp.ui.changci.ChangciActivity;
import com.cary.moviesapp.ui.order.OrderActivity;
import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.widget.Toast.LENGTH_SHORT;

public class SeatActivity extends AppCompatActivity {
    ChangciListEntity.DataBean changci;

    List<CheckBox> cbList;
    private static final String TAG = "TAG";
    private static final int PORT = 9999;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    int cid;
    int mid;
    FinalHttp fh;
    Gson gs;
    MoviesEntity.DataBean movie;
    MyApplication  application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        cbList=new ArrayList<>();

                application = (MyApplication) getApplication();
        CheckBox viewById = findViewById(R.id.checkBox);
        CheckBox viewById1 = findViewById(R.id.checkBox2);
        CheckBox viewById2 = findViewById(R.id.checkBox3);
        CheckBox viewById3 = findViewById(R.id.checkBox4);
        CheckBox viewById4 = findViewById(R.id.checkBox5);
        CheckBox viewById5 = findViewById(R.id.checkBox6);
        Button viewById6 = findViewById(R.id.button3);
        cbList.add(viewById);
        cbList.add(viewById1);
        cbList.add(viewById2);
        cbList.add(viewById3);
        cbList.add(viewById4);
        cbList.add(viewById5);
        MyOnClickLisenner myOnCheckChangeLisenner=new MyOnClickLisenner();
        viewById.setOnClickListener(myOnCheckChangeLisenner);
        viewById1.setOnClickListener(myOnCheckChangeLisenner);
        viewById2.setOnClickListener(myOnCheckChangeLisenner);
        viewById3.setOnClickListener(myOnCheckChangeLisenner);
        viewById4.setOnClickListener(myOnCheckChangeLisenner);
        viewById5.setOnClickListener(myOnCheckChangeLisenner);

        viewById6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=(TextView) findViewById(R.id.textView18);
                if (tv.getText().equals("")) {
                    Toast.makeText(SeatActivity.this, "请选择座位", Toast.LENGTH_SHORT).show();
                }
                else {
                    String url = "http://" + MyApplication.IP + ":8080/lazyrest/order/save";
                    AjaxParams params = new AjaxParams();
                    params.put("mname", movie.getName());
                    params.put("showtime", changci.getShowtime() + "");
                    params.put("place", changci.getPlace() + "");
                    params.put("price", changci.getPrice() + "");
                    // params.put("seat",changci.getSeatnum()+"");
                    params.put("uid", application.getUserBean().getId() + "");

                    fh.post(url, params, new AjaxCallBack<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Log.d("LoginActivity", o.toString());
                            Intent intent = new Intent(SeatActivity.this, OrderActivity.class);
                            intent.putExtra("changci", changci);
                            intent.putExtra("movie", movie);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onFailure(Throwable t, int errorNo, String strMsg) {
                            Toast.makeText(SeatActivity.this, "网络错误", LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        mExecutorService = Executors.newCachedThreadPool();
        connect();


        changci = (ChangciListEntity.DataBean) getIntent().getSerializableExtra("data");
        movie = (   MoviesEntity.DataBean ) getIntent().getSerializableExtra("movie");
         cid = changci.getId();
         mid = changci.getMid();

          gs = new Gson();
         fh = new FinalHttp();

        String url = "http://"+  MyApplication.IP+":8080/lazyrest/movie/getSeat";
        AjaxParams params=new AjaxParams();
        params.put("mid",mid+"");
        params.put("cid",cid+"");
        fh.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Log.d("LoginActivity", o.toString());
                SeatListEntity seatListEntity = gs.fromJson(o.toString(), SeatListEntity.class);
                List<SeatListEntity.DataBean> seats = seatListEntity.getData();
                for(int i=0;i<seats.size();i++){
                    //可选座位
                    if(seats.get(i).getStatus()==0){
                        cbList.get(i).setChecked(false);
                    }else{
                        cbList.get(i).setChecked(true);
                        cbList.get(i).setClickable(false);
                    }
                    cbList.get(i).setTag(seats.get(i).getId());
                }


            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(SeatActivity.this,"网络错误", LENGTH_SHORT).show();
            }
        });
    }

    public void getData(){

        String url = "http://"+  MyApplication.IP+":8080/lazyrest/movie/getSeat";
        AjaxParams params=new AjaxParams();
        params.put("mid",mid+"");
        params.put("cid",cid+"");
        fh.post(url,params,  new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Log.d("LoginActivity", o.toString());
                SeatListEntity seatListEntity = gs.fromJson(o.toString(), SeatListEntity.class);
                List<SeatListEntity.DataBean> seats = seatListEntity.getData();
                for(int i=0;i<seats.size();i++){
                    //可选座位
                    if(seats.get(i).getStatus()==0){
                        cbList.get(i).setChecked(false);
                    }else{
                        cbList.get(i).setChecked(true);
                        cbList.get(i).setClickable(false);
                    }
                }


            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(SeatActivity.this,"网络错误", LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        send("0");
        super.onDestroy();
    }

    class MyOnClickLisenner implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            CheckBox checkBox= (CheckBox) v;
            boolean isChecked = checkBox.isChecked();
            Object id = checkBox.getTag();
            TextView tv=(TextView) findViewById(R.id.textView18);
            int status;


            if(isChecked){
                status=1;

                tv.setText(checkBox.getText());

            }else{
                tv.setText("");
                status=0;
            }
            String url = "http://"+  MyApplication.IP+":8080/lazyrest/movie/useat";
            AjaxParams params=new AjaxParams();
            params.put("id",id+"");
            params.put("status",status+"");
            fh.post(url,  params,new AjaxCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    Log.d("LoginActivity", o.toString());
                    //SeatListEntity seatListEntity = gs.fromJson(o.toString(), SeatListEntity.class);

                }
                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    Toast.makeText(SeatActivity.this,"网络错误", LENGTH_SHORT).show();
                }
            });
            send("座位状态已变更");
        }
    }

    public void connect() {
        mExecutorService.execute(new connectService());  //在一个新的线程中请求 Socket 连接
    }

    public void send(String sendMsg) {
        //String sendMsg = mEditText.getText().toString();
        mExecutorService.execute(new sendService(sendMsg));
    }

    public void disconnect(View view) {
        mExecutorService.execute(new sendService("0"));
    }


    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }



    //socket连接，实现座位状态更新
    private class connectService implements Runnable {
        @Override
        public void run() {                                                                           //可以考虑在此处添加一个while循环，结合下面的catch语句，实现Socket对象获取失败后的超时重连，直到成功建立Socket连接
            try {
                Socket socket = new Socket( MyApplication.IP, PORT);                                   //步骤一
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(                       //步骤二
                        socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                receiveMsg();
            } catch (Exception e) {
                //Log.e(TAG, ("connectService:" + e.getMessage()));                                      //如果Socket对象获取失败，即连接建立失败，会走到这段逻辑
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {                                                                      //步骤三
                if ((receiveMsg = in.readLine()) != null) {
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getData();
                                                                                    //mTextView.setText(receiveMsg + "\n\n" + mTextView.getText());
                        }
                    });
                }
            }
        } catch (IOException e) {
           Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }

}
