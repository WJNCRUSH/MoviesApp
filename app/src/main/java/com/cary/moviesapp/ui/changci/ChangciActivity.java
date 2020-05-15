package com.cary.moviesapp.ui.changci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.moviesapp.R;
import com.cary.moviesapp.application.MyApplication;
import com.cary.moviesapp.entity.ChangciListEntity;
import com.cary.moviesapp.entity.MoviesEntity;
import com.cary.moviesapp.ui.home.HomeFragment;
import com.cary.moviesapp.ui.seat.SeatActivity;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.List;

public class ChangciActivity extends AppCompatActivity {
    ListView lv_cc;
    MyBaseAdaper myBaseAdaper;
    List<ChangciListEntity.DataBean> data;
    MoviesEntity.DataBean movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changci);
        lv_cc = findViewById(R.id.lv_cc);

        lv_cc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(ChangciActivity.this, SeatActivity.class);
                i.putExtra("data",data.get(position));
                i.putExtra("movie",movie);
                startActivity(i);
            }
        });

       movie= (MoviesEntity.DataBean) getIntent().getSerializableExtra("data");

        final Gson gs = new Gson();
        FinalHttp fh = new FinalHttp();

        String url = "http://"+  MyApplication.IP+":8080/lazyrest/movie/cc";
        AjaxParams params=new AjaxParams();
        params.put("mid",movie.getId()+"");

        fh.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Log.d("LoginActivity", o.toString());
                ChangciListEntity result = gs.fromJson(o.toString(), ChangciListEntity.class);
                data = result.getData();
                myBaseAdaper = new MyBaseAdaper();
                lv_cc.setAdapter(myBaseAdaper);
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(ChangciActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
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
                view = getLayoutInflater().inflate(R.layout.item_changci, null);
            }
            TextView tv_time = view.findViewById(R.id.textView15);
            TextView tv_plae= view.findViewById(R.id.textView16);
            tv_time.setText(data.get(position).getShowtime());
            tv_plae.setText(data.get(position).getPlace());
            return view;
        }
    }
}
