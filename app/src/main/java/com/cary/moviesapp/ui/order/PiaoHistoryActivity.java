package com.cary.moviesapp.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.moviesapp.R;
import com.cary.moviesapp.application.MyApplication;
import com.cary.moviesapp.entity.MoviesEntity;
import com.cary.moviesapp.entity.OrderListEntity;
import com.cary.moviesapp.ui.home.HomeFragment;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.List;

public class PiaoHistoryActivity extends AppCompatActivity {
    ListView lv_piao;
    MyBaseAdaper myBaseAdaper;
    MyApplication application;
    List<OrderListEntity.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piao_history);
         lv_piao = findViewById(R.id.lv_piao);
         application = (MyApplication) getApplication();
        final Gson gs = new Gson();
        FinalHttp fh = new FinalHttp();

        String url = "http://"+ MyApplication.IP+":8080/lazyrest/order/get";
        AjaxParams params=new AjaxParams();
        params.put("uid",application.getUserBean().getId()+"");

        fh.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Log.d("LoginActivity", o.toString());
                OrderListEntity result = gs.fromJson(o.toString(), OrderListEntity.class);
                data = result.getData();
                myBaseAdaper = new MyBaseAdaper();
                lv_piao.setAdapter(myBaseAdaper);
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(PiaoHistoryActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
            }
        });

    }

    class MyBaseAdaper extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=convertView;
            if(view==null){
                view = getLayoutInflater().inflate(R.layout.history_item, null);
            }
            TextView tv_name = view.findViewById(R.id.textView32);
            TextView tv_time = view.findViewById(R.id.textView33);
            TextView tv_place= view.findViewById(R.id.textView34);
            TextView tv_price= view.findViewById(R.id.textView35);
            tv_name.setText(data.get(position).getMname());
            tv_time.setText(data.get(position).getShowtime());
            tv_place.setText(data.get(position).getPlace());
            tv_price.setText(data.get(position).getPrice());

            return view;
        }
    }
}
