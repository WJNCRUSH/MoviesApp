package com.cary.moviesapp.ui.home;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cary.moviesapp.R;
import com.cary.moviesapp.application.MyApplication;
import com.cary.moviesapp.entity.MoviesEntity;
import com.cary.moviesapp.ui.detail.MovieDetailActity;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.File;
import java.util.List;

public class HomeFragment extends Fragment {
    ListView lv_movies;
    List<MoviesEntity.DataBean> data;
    MyBaseAdaper myBaseAdaper;
    DisplayImageOptions options;
    MyApplication application;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

         application = (MyApplication) getActivity().getApplication();
        lv_movies=root.findViewById(R.id.lv_movies);

        lv_movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     Intent i=new Intent(HomeFragment.this.getActivity(), MovieDetailActity.class);
                     i.putExtra("data",data.get(position));
                     startActivity(i);
            }
        });

         options = new DisplayImageOptions.Builder()
                .cacheInMemory(false) //设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
              /*  .showImageOnLoading(R.drawable.zhanwei_shape)
                .showImageOnFail(R.drawable.zhanwei_shape)*/
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)//是否考虑JPEG图像EXIF参数（旋转，翻转）
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplication())
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3) //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
                .diskCacheSize(50 * 1024 * 1024)  // 50 Mb sd卡(本地)缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(options)// 由原先的discCache -> diskCache
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);//全局初始化此配置

        final Gson gs = new Gson();
        FinalHttp fh = new FinalHttp();

        String url = "http://"+ MyApplication.IP+":8080/lazyrest/movie/all";
        fh.post(url,  new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Log.d("LoginActivity", o.toString());
                MoviesEntity result = gs.fromJson(o.toString(), MoviesEntity.class);
                data = result.getData();
                myBaseAdaper = new MyBaseAdaper();
                lv_movies.setAdapter(myBaseAdaper);
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(HomeFragment.this.getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    
    class MyBaseAdaper extends BaseAdapter{

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
              view = getLayoutInflater().inflate(R.layout.item_movies, null);
            }
            ImageView iv = view.findViewById(R.id.imageView);
            TextView tv_name = view.findViewById(R.id.textView3);
            TextView tv_au = view.findViewById(R.id.textView6);
            TextView tv_time= view.findViewById(R.id.textView7);
            tv_name.setText(data.get(position).getName());
            tv_au.setText(data.get(position).getActor());
            tv_time.setText(data.get(position).getPerformtime());
            ImageLoader.getInstance().displayImage("http://"+MyApplication.IP+":8080/lazyrest"+data.get(position).getImgsrc() , iv, options);
            return view;
        }
    }
}