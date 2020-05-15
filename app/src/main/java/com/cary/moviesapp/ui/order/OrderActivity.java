package com.cary.moviesapp.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cary.moviesapp.MainActivity;
import com.cary.moviesapp.R;
import com.cary.moviesapp.entity.ChangciListEntity;
import com.cary.moviesapp.entity.MoviesEntity;
import com.cary.moviesapp.ui.changci.ChangciActivity;
import com.cary.moviesapp.ui.seat.SeatActivity;

public class OrderActivity extends AppCompatActivity {
    ChangciListEntity.DataBean changci;
    MoviesEntity.DataBean movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        TextView tv_name = findViewById(R.id.textView23);
        TextView tv_time = findViewById(R.id.textView21);
        TextView tv_place = findViewById(R.id.textView24);
        TextView tv_price = findViewById(R.id.textView29);
        Button btn = findViewById(R.id.button4);

        changci = (ChangciListEntity.DataBean) getIntent().getSerializableExtra("changci");
        movie = (   MoviesEntity.DataBean ) getIntent().getSerializableExtra("movie");

        tv_name.setText(movie.getName());
        tv_time.setText(changci.getShowtime());
        tv_place.setText(changci.getPlace());
        tv_price.setText(changci.getPrice());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OrderActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
