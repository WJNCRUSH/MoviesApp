package com.cary.moviesapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.moviesapp.R;
import com.cary.moviesapp.application.MyApplication;
import com.cary.moviesapp.entity.MoviesEntity;
import com.cary.moviesapp.ui.changci.ChangciActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.Serializable;

public class MovieDetailActity extends AppCompatActivity {
    DisplayImageOptions options;
    MoviesEntity.DataBean data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_actity);
        ImageView iv_movie = findViewById(R.id.imageView2);
        TextView tv_name = findViewById(R.id.textView8);
        TextView tv_au = findViewById(R.id.textView10);
        TextView tv_time = findViewById(R.id.textView12);
        TextView tv_des = findViewById(R.id.textView14);
        Button btn = findViewById(R.id.button);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(false) //设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                /*  .showImageOnLoading(R.drawable.zhanwei_shape)
                  .showImageOnFail(R.drawable.zhanwei_shape)*/
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)//是否考虑JPEG图像EXIF参数（旋转，翻转）
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplication())
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

        data = (MoviesEntity.DataBean) getIntent().getSerializableExtra("data");
        ImageLoader.getInstance().displayImage("http://"+ MyApplication.IP+":8080/lazyrest"+data.getImgsrc() , iv_movie, options);
        tv_name.setText(data.getName());
        tv_au.setText(data.getActor());
        tv_time.setText(data.getPerformtime());
        tv_des.setText(data.getDescrition());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MovieDetailActity.this, ChangciActivity.class);
                i.putExtra("data",data);
                startActivity(i);
            }
        });
    }
}
