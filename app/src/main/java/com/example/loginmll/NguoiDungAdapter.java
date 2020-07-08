package com.example.loginmll;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import  android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public  class NguoiDungAdapter extends  BaseAdapter {

    private InfUser context;
    private int layout;
    private List<NguoiDung> nguoiDungList;

    public NguoiDungAdapter(InfUser context, int layout, List<NguoiDung> nguoiDungList) {
        this.context = context;
        this.layout = layout;
        this.nguoiDungList = nguoiDungList;
    }

    @Override
    public int getCount() {
        return nguoiDungList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    private  class ViewHolder{
        TextView txtHoten, txtId, txtEmail;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView    = inflater.inflate(layout, null);
            holder.txtHoten     = (TextView) convertView.findViewById(R.id.textViewHT);
            //holder.txtId        = (TextView) convertView.findViewById(R.id.textViewMa);
            holder.txtEmail     = (TextView) convertView.findViewById(R.id.textViewEm);

            holder.imgDelete    = (ImageView) convertView.findViewById(R.id.imgRemove);
            holder.imgEdit      = (ImageView) convertView.findViewById(R.id.imgEdit);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final NguoiDung nguoiDung = nguoiDungList.get(position);

        holder.txtHoten.setText(nguoiDung.getName()+ " - " + nguoiDung.getId());
        //holder.txtId.setText("( ID:"+nguoiDung.getId() +" )");
        holder.txtEmail.setText( "  Email:"+nguoiDung.getEmail() );

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(context, UpdateUser.class);
                chuyenlayout.putExtra("zid", nguoiDung.getId());
                chuyenlayout.putExtra("activity", "1");
                chuyenlayout.putExtra("ima", context.chuyenLayout());
//                                        chuyenlayoutGV.putExtra("pass", pass);
                context.startActivity(chuyenlayout);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDelete(nguoiDung.getName(), nguoiDung.getId());
            }
        });
        return convertView;
    }
    private void ConfirmDelete(final String ten, final String id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có chắc muốn xóa nhân viên " + ten + " không ?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteUser(id);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}