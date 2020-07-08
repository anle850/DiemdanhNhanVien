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

import java.util.ArrayList;
import java.util.List;


public  class ThongKeAdapter extends  BaseAdapter {

    private ListThongKe context;
    private int layout;
    private List<ThongKe> ThongKeList;

    public ThongKeAdapter(ListThongKe context, int layout, ArrayList<ThongKe> thongKeList) {
        this.context = context;
        this.layout = layout;
        this.ThongKeList = thongKeList;
    }

    @Override
    public int getCount() {
        return ThongKeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    private  class ViewHolder{
        TextView txtTenNv, txtMaNV, txtSoNgay;
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
            holder.txtTenNv     = (TextView) convertView.findViewById(R.id.textviewTenNhanVien);
            //holder.txtId        = (TextView) convertView.findViewById(R.id.textViewMa);
            holder.txtMaNV     = (TextView) convertView.findViewById(R.id.textviewMSNV);
            holder.txtSoNgay     = (TextView) convertView.findViewById(R.id.textviewSoNgay);


            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }



        final ThongKe thongke = ThongKeList.get(position);

        holder.txtTenNv.setText(thongke.getTenNv());
        //holder.txtId.setText("( ID:"+nguoiDung.getId() +" )");
        holder.txtMaNV.setText( thongke.getMaNv() );
        holder.txtSoNgay.setText( thongke.getSoNgay() + " ngày" );


        return convertView;
    }
//    private void ConfirmDelete(final String ten, final String id) {
//        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
//        dialogXoa.setMessage("Bạn có chắc muốn xóa thông báo " + ten + " không ?");
//        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                context.DeleteUser(id);
////                context.Act();
//
//
//            }
//        });
//
//        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        dialogXoa.show();
//    }
}