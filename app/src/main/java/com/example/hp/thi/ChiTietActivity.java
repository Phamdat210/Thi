package com.example.hp.thi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.thi.adapter.GhiChuAdapter;
import com.example.hp.thi.model.GhiChu;

import java.util.List;

public class ChiTietActivity extends AppCompatActivity {
    private TextView edtName;
    private TextView edtContent;
    private TextView edtTime;
    Sqlite sqLite;
    public List<GhiChu> ghiChuList;
    GhiChu ghiChu;
    GhiChuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        edtName = (TextView) findViewById(R.id.edtName);
        edtContent = (TextView) findViewById(R.id.edtContent);
        edtTime = (TextView) findViewById(R.id.edtTime);

        try {
            Intent intent = getIntent();
            if (intent != null) {
                Bundle bundle = intent.getBundleExtra("bundle");
                edtName.setText(bundle.getString("name"));
                edtContent.setText(bundle.getString("content"));
                edtTime.setText(bundle.getString("time"));
            }
        } catch (Exception ex){

        }
    }

    public void Del(View view) {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            sqLite.deleteGhiChu(bundle.getString("name"));
            Intent i = new Intent(ChiTietActivity.this,MainActivity.class);
            startActivity(i);
        }

    }
}
