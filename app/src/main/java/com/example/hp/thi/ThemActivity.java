package com.example.hp.thi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.thi.model.GhiChu;

public class ThemActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtContent;
    private EditText edtTime;
    Sqlite sqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        edtName = (EditText) findViewById(R.id.edtName);
        edtContent = (EditText) findViewById(R.id.edtContent);
        edtTime = (EditText) findViewById(R.id.edtTime);
    }

    public void AddGhiChu(View view) {
        sqLite = new Sqlite(ThemActivity.this);
        GhiChu ghiChu = new GhiChu(edtName.getText().toString(),
                edtContent.getText().toString(),
                edtTime.getText().toString());
        if (sqLite.insertGhiChu(ghiChu)>0){
            Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ThemActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
        }
    }
}
