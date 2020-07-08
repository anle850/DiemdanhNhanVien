package com.example.loginmll;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.HashMap;
import java.util.Map;

public class ListThongBao extends AppCompatActivity {
    String urlget = "http://192.168.1.28/webservice/getThongbao.php";
    String urldelete = "http://192.168.1.28/webservice/deleteThongbao.php", zid, activity ;


    ListView lvthongbao;
    ArrayList<ThongBao> arrayThongBao;
    ThongBaoAdapter adapter;
    FloatingActionButton them;
    Button btn_TK, btn_TB, btn_thongke, btn_diemdanh, btn_taikhoan;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_thong_bao);

        lvthongbao = (ListView) findViewById(R.id.listThongBao);
        them = findViewById(R.id.buttonThem);
        btn_TB = findViewById(R.id.buttonND);
        btn_TK = findViewById(R.id.buttonTK);
        btn_thongke = findViewById(R.id.buttonThongke);

        btn_diemdanh = findViewById(R.id.buttondiemdanh);
        btn_taikhoan = findViewById(R.id.buttontaikhoan);



        Intent intent = getIntent();
        zid = intent.getStringExtra("ma");
        activity = intent.getStringExtra("activity");

        if(activity.equals("0")){
            findViewById(R.id.buttonofnv).setVisibility(View.VISIBLE);
            them.setVisibility(View.INVISIBLE);
        } else findViewById(R.id.buttonofadmin).setVisibility(View.VISIBLE);


        arrayThongBao = new ArrayList<>();
        adapter = new ThongBaoAdapter(ListThongBao.this, R.layout.dong_thong_bao, arrayThongBao);
        lvthongbao.setAdapter(adapter);

//        if(Act() == "0") them.setVisibility(View.INVISIBLE);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongBao.this, ThemThongBao.class);
                chuyenlayout.putExtra("ma", zid);
                startActivity((chuyenlayout));

            }
        });
        btn_diemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongBao.this, GhiDanh.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
            }
        });
        btn_taikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongBao.this, User.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
            }
        });

        btn_TK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongBao.this, User.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
            }
        });

        btn_TB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongBao.this, InfUser.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
                //Toast.makeText(ListThongBao.this, "Chưa có layout thông báo !" , Toast.LENGTH_SHORT).show();

            }
        });
        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(ListThongBao.this, ListThongKe.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
                //Toast.makeText(ListThongBao.this, "Chưa có layout thông báo !" , Toast.LENGTH_SHORT).show();

            }
        });

        getData(zid);
    }

    public String Act (){
        return activity;
    }

    private void getData (final String ma){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlget.trim(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");


                            if(success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String tenthongbao = object.getString("tenthongbao").trim();
                                    String tennguoitao = object.getString("ten").trim();
                                    String mathongbao = object.getString("mathongbao").trim();
                                    String ngaytao = object.getString("ngaytao").trim();
                                    String noidung = object.getString("noidung").trim();
                                    arrayThongBao.add(new ThongBao(mathongbao, tenthongbao, noidung, ngaytao, tennguoitao));

                                }
                            } else Toast.makeText(ListThongBao.this, "Lỗi dữ liệu mã tài khoản" , Toast.LENGTH_SHORT).show();

                        }catch (
                                JSONException e){
                            e.printStackTrace();
                            Toast.makeText(ListThongBao.this, "Hiển thị thông tin lỗi!" , Toast.LENGTH_SHORT ).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);

                        }
                        adapter.notifyDataSetChanged();


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListThongBao.this, "Hiển thị thông tin lỗi!  !" + error.toString(), Toast.LENGTH_SHORT ).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("ma",ma);

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

    public void DeleteUser(final String inus) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urldelete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("true")){
                            finish();
                            startActivity(getIntent());
                            Toast.makeText(ListThongBao.this, "Xóa thành công !" , Toast.LENGTH_SHORT ).show();

                        }else {
                            Toast.makeText(ListThongBao.this, "Xóa lỗi !" , Toast.LENGTH_SHORT ).show();

                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListThongBao.this, "Lỗi !" + error.toString(), Toast.LENGTH_SHORT ).show();
//                        loading.setVisibility(View.GONE);
//                        btn_regist.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id",inus);



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}

