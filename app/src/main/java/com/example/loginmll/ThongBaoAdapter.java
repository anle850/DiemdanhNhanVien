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


public  class ThongBaoAdapter extends  BaseAdapter {

    private ListThongBao context;
    private int layout;
    private List<ThongBao> ThongBaoList;

    public ThongBaoAdapter(ListThongBao context, int layout, List<ThongBao> thongBaoList) {
        this.context = context;
        this.layout = layout;
        this.ThongBaoList = thongBaoList;
    }

    @Override
    public int getCount() {
        return ThongBaoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    private  class ViewHolder{
        TextView txtTenTB, txtNoiDung, txtNgayTao, txtTen, txtMaTB;
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
            holder.txtTenTB     = (TextView) convertView.findViewById(R.id.textviewTenThongBao);
            //holder.txtId        = (TextView) convertView.findViewById(R.id.textViewMa);
            holder.txtNoiDung     = (TextView) convertView.findViewById(R.id.textviewNoiDungThongBao);
            holder.txtNgayTao     = (TextView) convertView.findViewById(R.id.textviewNgayTao);
            holder.txtTen     = (TextView) convertView.findViewById(R.id.textviewTenAdmin);
            holder.txtMaTB     = (TextView) convertView.findViewById(R.id.MTB);


            holder.imgDelete    = (ImageView) convertView.findViewById(R.id.imgRemove);

            holder.imgEdit      = (ImageView) convertView.findViewById(R.id.imgEdit);


            if(context.Act().equals( "1")){
                holder.imgDelete.setVisibility(View.VISIBLE);
                holder.imgEdit.setVisibility(View.VISIBLE);
            }

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }



        final ThongBao thongBao = ThongBaoList.get(position);

        holder.txtTenTB.setText(thongBao.getName());
        //holder.txtId.setText("( ID:"+nguoiDung.getId() +" )");
        holder.txtNoiDung.setText( thongBao.getNoidung() );
        holder.txtTen.setText( thongBao.getTennguoitao() );
        holder.txtNgayTao.setText(  thongBao.getNgaytao() );
        holder.txtMaTB.setText( "#" +thongBao.getMathongbao() );

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(context, ThemThongBao.class);
                //chuyenlayout.putExtra("zid", thongBao.getId());
                chuyenlayout.putExtra("activity", "1");
                chuyenlayout.putExtra("ima", context.chuyenLayout());
//                                        chuyenlayoutGV.putExtra("pass", pass);
                context.startActivity(chuyenlayout);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDelete(thongBao.getName(), thongBao.getMathongbao());
            }
        });
        return convertView;
    }
    private void ConfirmDelete(final String ten, final String id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có chắc muốn xóa thông báo " + ten + " không ?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteUser(id);
//                context.Act();


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