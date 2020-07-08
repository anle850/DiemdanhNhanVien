package com.example.loginmll;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

public class InfUser extends AppCompatActivity {
    String urlget = "http://192.168.1.28/webservice/getdata.php";
    String urldelete = "http://192.168.1.28/webservice/delete.php", zid, activity;

    ListView lvuser;
    ArrayList<NguoiDung> arrayNguoidung;
    NguoiDungAdapter adapter;
    FloatingActionButton them;
    Button btn_TK, btn_TB, btn_ThongKe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nguoi_dung);

        lvuser = (ListView) findViewById(R.id.listUser);
        them = findViewById(R.id.buttonThem);
        btn_TB = findViewById(R.id.buttonThongbao);
        btn_TK = findViewById(R.id.buttonTK);
        btn_ThongKe = findViewById(R.id.buttonThongke);

        Intent intent = getIntent();
        zid = intent.getStringExtra("ma");
        activity = intent.getStringExtra("activity");

        arrayNguoidung = new ArrayList<>();
        adapter = new NguoiDungAdapter(InfUser.this, R.layout.dong_nguoi_dung, arrayNguoidung);
        lvuser.setAdapter(adapter);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(InfUser.this, Register.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
            }
        });

        btn_TK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(InfUser.this, User.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
            }
        });
        btn_ThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(InfUser.this, ListThongKe.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
            }
        });

        btn_TB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(InfUser.this, ListThongBao.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", activity);
                startActivity((chuyenlayout));
                //Toast.makeText(InfUser.this, "Chưa có layout thông báo !" , Toast.LENGTH_SHORT).show();

            }
        });

        getData(urlget);
    }

    private void getData (final String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.trim(),


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

                                    String id = object.getString("id").trim();
                                    String email = object.getString("email").trim();
                                    String ten = object.getString("ten").trim();
                                    arrayNguoidung.add(new NguoiDung(id, ten, email));

                                }
                            } else Toast.makeText(InfUser.this, "Lỗi dữ liệu mã tài khoản" , Toast.LENGTH_SHORT).show();

                        }catch (
                                JSONException e){
                            e.printStackTrace();
                            Toast.makeText(InfUser.this, "Hiển thị thông tin lỗi!" , Toast.LENGTH_SHORT ).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);

                        }
                        adapter.notifyDataSetChanged();


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InfUser.this, "Hiển thị thông tin lỗi!  !" + error.toString(), Toast.LENGTH_SHORT ).show();


                    }
                });
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("ma",ma);
//
//                return params;
//            }
//        };



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
                            Toast.makeText(InfUser.this, "Xóa thành công !" , Toast.LENGTH_SHORT ).show();

                        }else {
                            Toast.makeText(InfUser.this, "Xóa lỗi !" , Toast.LENGTH_SHORT ).show();

                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InfUser.this, "Lỗi !" + error.toString(), Toast.LENGTH_SHORT ).show();
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

