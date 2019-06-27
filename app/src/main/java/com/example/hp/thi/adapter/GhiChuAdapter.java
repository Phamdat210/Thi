package com.example.hp.thi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.thi.ChiTietActivity;
import com.example.hp.thi.R;
import com.example.hp.thi.Sqlite;
import com.example.hp.thi.ThemActivity;
import com.example.hp.thi.model.GhiChu;

import java.util.List;

public class GhiChuAdapter extends BaseAdapter {
    private Context context;
    private List<GhiChu> ghiChuList;
    public Sqlite sqLite;

    public GhiChuAdapter(Context context, List<GhiChu> ghiChuList){
        this.ghiChuList = ghiChuList;
        this.context = context;
        sqLite = new Sqlite(context);

    }

    @Override
    public int getCount() {
        return ghiChuList.size();
    }

    @Override
    public Object getItem(int position) {
        return ghiChuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        TextView tvname=convertView.findViewById(R.id.tvName);
        TextView tvprice=convertView.findViewById(R.id.tvTime);
        ImageView btnDel=convertView.findViewById(R.id.btnDeleted);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GhiChu ghiChu = ghiChuList.get(position);
                if (sqLite.deleteGhiChu(ghiChu.getName())>0){
                    ghiChuList.remove(ghiChu);
                    notifyDataSetChanged();
                    Toast.makeText(context,"Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final GhiChu ghiChu=(GhiChu) getItem(position);
        tvname.setText(ghiChu.getName());
        tvprice.setText(ghiChu.getTime());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GhiChu ghiChu1= ghiChuList.get(position);
                Intent intent= new Intent(context,ChiTietActivity.class);
                Bundle bundle= new Bundle();
                bundle.putString("name",ghiChu1.getName());
                bundle.putString("content",ghiChu1.getContent());
                bundle.putString("time",ghiChu1.getTime());
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
