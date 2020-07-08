package com.example.loginmll;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import java.util.HashMap;
import java.util.Map;

public class User extends AppCompatActivity {
    private TextView id, name, email, songay;
    private Button btn_update, btn_logout, btn_thongbao, btn_thongke, btn_nguoidung, btn_diemdanh, btn_tb;



    private String url_regist =  "http://192.168.1.28/webservice/User.php", ma, act  ;
    private String url_songay = "http://192.168.1.28/webservice/ngaylam.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);

        id = findViewById(R.id.editTextName);
        name = findViewById(R.id.editTextID);
        //pass = findViewById(R.id.editTextPW);
        email = findViewById(R.id.editTextEmail);
        songay = findViewById(R.id.editTextsongay);
        //c_pass = findViewById(R.id.editTextCF);

        Intent intent = getIntent();
        ma = intent.getStringExtra("ma");
        act = intent.getStringExtra("activity");
//        activit = "0";

        id.setText("  "+ ma);
        if(act.equals("0") ){
            findViewById(R.id.buttonofnv).setVisibility(View.VISIBLE);
            songay.setVisibility(View.VISIBLE);
            getsongay(ma);
        } else {

            findViewById(R.id.buttonofadmin).setVisibility(View.VISIBLE);
            songay.setVisibility(View.INVISIBLE);
        }

        btn_update = findViewById(R.id.btnDki);

        btn_thongbao = findViewById(R.id.buttonTB);
        btn_thongke = findViewById(R.id.buttonThongke);
        btn_nguoidung = findViewById(R.id.buttonND);
//        btn_update.setText("Sửa");
        btn_logout = findViewById(R.id.btnHuy);
//        finish();
//        startActivity(getIntent());

        btn_diemdanh = findViewById(R.id.buttondiemdanh);
        btn_tb = findViewById(R.id.buttonthongbao);

        getUser(ma);


        btn_diemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(User.this,GhiDanh.class);
                ii.putExtra("ma",ma);
                ii.putExtra("activity", act);
                startActivity(ii);
            }

        });

        btn_tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(User.this,ListThongBao.class);
                ii.putExtra("ma",ma);
                ii.putExtra("activity", act);
                startActivity(ii);
            }

        });

        btn_thongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(User.this,ListThongBao.class);
                ii.putExtra("ma",ma);
                ii.putExtra("activity", act);
                startActivity(ii);
            }

        });


        btn_nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(User.this,InfUser.class);
                ii.putExtra("ma",ma);
                ii.putExtra("activity", act);
                startActivity(ii);
            }

        });

        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(User.this,ListThongKe.class);
                ii.putExtra("ma",ma);
                ii.putExtra("activity", act);
                startActivity(ii);
            }

        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(User.this,UpdateUser.class);
                ii.putExtra("ma",ma);
                ii.putExtra("activity", act);
                startActivity(ii);
            }

        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentb3 = new Intent(User.this, MainActivity.class);
                intentb3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentb3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentb3);

            }
        });
    }
//    @Override
//    public void onBackPressed() {
//        if (act.equals("0")){
//            Intent ii = new Intent(User.this,InfUser.class);
//            ii.putExtra("ma",ma);
//           // ii.putExtra("activity", activit);
//            startActivity(ii);
//
//        }
//
//    }
private void getsongay (final String ma){
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url_songay.trim(),
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

                                String sn = object.getString("songay").trim();



//                                    TextView txtname = findViewById(R.id.editTextID);
//                                    TextView txtpass = findViewById(R.id.editTextName);

                                songay.setText("  Có "+sn+" ngày/tháng đã ghi danh");


                            }
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(User.this, " Lỗi!" + e.toString(), Toast.LENGTH_SHORT ).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);

                    }



                }


            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(User.this, "" + error.toString(), Toast.LENGTH_SHORT ).show();


                }
            })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();

            params.put("id",ma);
            return params;
        }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(this);

    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
            10000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
    ));

    requestQueue.add(stringRequest);

}

    private void getUser (final String ma){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_regist.trim(),
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

                                    String zname = object.getString("name").trim();
                                    String zemail = object.getString("email").trim();


//                                    TextView txtname = findViewById(R.id.editTextID);
//                                    TextView txtpass = findViewById(R.id.editTextName);

                                    name.setText(" "+zname);
                                    email.setText("  "+zemail);

                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(User.this, " Lỗi!" + e.toString(), Toast.LENGTH_SHORT ).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);

                        }



                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(User.this, "Trang người dùng lỗi  !" + error.toString(), Toast.LENGTH_SHORT ).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id",ma);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        requestQueue.add(stringRequest);

    }

}
