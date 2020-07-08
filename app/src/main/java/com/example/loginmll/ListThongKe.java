package com.example.loginmll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ListThongKe extends AppCompatActivity {
    String urlget = "http://192.168.1.28/webservice/getThongKe.php", zid, activity;
    //String urldelete = "http://192.168.1.28/webservice/deleteThongbao.php", zid, activity ;


    ListView lvthongke;
    ArrayList<ThongKe> arrayThongKe;
    ThongKeAdapter adapter;
    //FloatingActionButton them;
    Button btn_TK, btn_TB, btn_user;
    ImageView img_back, img_next;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        lvthongke = (ListView) findViewById(R.id.listviewThongKe);
        //them = findViewById(R.id.buttonThem);
        btn_TB = findViewById(R.id.buttonThongbao);
        btn_TK = findViewById(R.id.buttonTK);
        btn_user = findViewById(R.id.buttonND);

        final TextView month = findViewById(R.id.txtTHANG);

        img_back = findViewById(R.id.imgBack);
        img_next = findViewById(R.id.imgNext);

        Calendar ngay = Calendar.getInstance();
        final int[] date = {ngay.get(Calendar.MONTH)};
        month.setText("Tháng " + date[0]);


        Intent intent = getIntent();
        zid = intent.getStringExtra("ma");
        activity = intent.getStringExtra("activity");

        String metvl = String.valueOf(date[0]);
        //Toast.makeText(ListThongKe.this, ""+metvl , Toast.LENGTH_SHORT).show();

        getData(metvl);





        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(date[0] <= 12 && date[0] > 1){
                    month.setText("Tháng " + (date[0] - 1));
                    date[0] = date[0] - 1;
                } else if( date[0] == 1){
                    month.setText("Tháng " + 12);
                    date[0] = 12;
                }
                String metvl = String.valueOf(date[0]);
                getData(metvl);
                adapter.notifyDataSetInvalidated();
            }
        });

        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date[0] < 12 && date[0] >= 1){
                    month.setText("Tháng " + (date[0] + 1));
                    date[0] = date[0] + 1;
                } else if( date[0] == 12){
                    month.setText("Tháng " + 1);
                    date[0] = 1;
                }
                String metvl = String.valueOf(date[0]);
                getData(metvl);
                adapter.notifyDataSetInvalidated();

            }
        });






        arrayThongKe = new ArrayList<ThongKe>();
        adapter = new ThongKeAdapter(ListThongKe.this, R.layout.dong_thong_ke, arrayThongKe);
        lvthongke.setAdapter(adapter);

//        if(Act() == "0") them.hide();

//        them.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent chuyenlayout = new Intent(ListThongBao.this, ThemThongBao.class);
//                chuyenlayout.putExtra("ma", zid);
//                startActivity((chuyenlayout));
//
//            }
//        });

        btn_TK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongKe.this, User.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
            }
        });
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongKe.this, InfUser.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
            }
        });

        btn_TB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongKe.this, ListThongBao.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));

            }
        });


    }

    public String Act (){
        return activity;
    }

    private void getData (final String laythang){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlget.trim(),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            lvthongke.setVisibility(View.VISIBLE);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            arrayThongKe.clear();



                            if(success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String tennhanvien = object.getString("tennhanvien").trim();
                                    String manv = object.getString("manv").trim();
                                    String songay = object.getString("songay").trim();

                                    arrayThongKe.add(new ThongKe(manv,tennhanvien,songay));

                                }

                            } else Toast.makeText(ListThongKe.this, "Lỗi dữ liệu mã tài khoản" , Toast.LENGTH_SHORT).show();



                        }catch (
                                JSONException e){
                            e.printStackTrace();
                            Toast.makeText(ListThongKe.this, "Không có dữ liệu!" , Toast.LENGTH_SHORT ).show();
                            lvthongke.setVisibility(View.INVISIBLE);
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);

                        }
                        adapter.notifyDataSetChanged();



                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListThongKe.this, "Hiển thị thông tin lỗi!  !" + error.toString(), Toast.LENGTH_SHORT ).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("thang", laythang);;

                return params;
            }
        };



        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        requestQueue.add(stringRequest);

    }
    public String chuyenLayout(){
        return zid;
    }

//    public void DeleteUser(final String inus) {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, urldelete,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.trim().equals("true")){
//                            finish();
//                            startActivity(getIntent());
//                            Toast.makeText(ListThongBao.this, "Xóa thành công !" , Toast.LENGTH_SHORT ).show();
//
//                        }else {
//                            Toast.makeText(ListThongBao.this, "Xóa lỗi !" , Toast.LENGTH_SHORT ).show();
//
//                        }
//                    }
//
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ListThongBao.this, "Lỗi !" + error.toString(), Toast.LENGTH_SHORT ).show();
////                        loading.setVisibility(View.GONE);
////                        btn_regist.setVisibility(View.VISIBLE);
//
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("id",inus);
//
//
//
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//
//    }

}

