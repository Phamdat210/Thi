package com.example.hp.thi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hp.thi.adapter.GhiChuAdapter;
import com.example.hp.thi.model.GhiChu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imgAdd;
    private ListView lv;
    public static List<GhiChu> dsGhiChu = new ArrayList<>();
    Sqlite sqLite;
    GhiChuAdapter adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        lv = (ListView) findViewById(R.id.lv);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ThemActivity.class);
                startActivity(i);
            }
        });

        sqLite = new Sqlite(MainActivity.this);
        dsGhiChu = sqLite.getAllGhiChu();

        adapter = new GhiChuAdapter(MainActivity.this,dsGhiChu);
        lv.setAdapter(adapter);
    }
}
