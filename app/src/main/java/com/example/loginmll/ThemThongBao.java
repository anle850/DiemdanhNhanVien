package com.example.loginmll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThemThongBao extends AppCompatActivity {
    private EditText tieude, noidung, mathongbao;
    private Button btn_regist, btn_huy;



    private String url_regist =  "http://192.168.1.28/webservice/thongbao.php", ma;

    @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_them_thong_bao);

            tieude = findViewById(R.id.txtTieuDe);
            noidung = findViewById(R.id.txtNoiDung);
            mathongbao = findViewById(R.id.txtMTB);


        Intent intent = getIntent();
        ma = intent.getStringExtra("ma");

        btn_regist = findViewById(R.id.btnThemThongBao);
        btn_huy = findViewById(R.id.btnHuy);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String tma = id.getText().toString().trim();
//                String tmatkhau = pass.getText().toString().trim();
//                String tname = name.getText().toString().trim();
//                String temail = email.getText().toString().trim();

                if (mathongbao.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Bạn chưa mã thông báo.", Toast.LENGTH_SHORT).show();
                else if (tieude.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập tiêu đề.", Toast.LENGTH_SHORT).show();
                else if (noidung.getText().toString().isEmpty() )

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập nội dung.", Toast.LENGTH_SHORT).show();

                else {
                    RegistRR(ma);
                }

            }

        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
    private void RegistRR(final String id){
//        loading.setVisibility(View.VISIBLE);
//        btn_regist.setVisibility(View.GONE);

        final String td = this.tieude.getText().toString().trim();
        final String nd = this.noidung.getText().toString().trim();
        final String mathongbao = this.mathongbao.getText().toString().trim();

        //final String c_pass = this.c_pass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_regist.trim(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if(response.trim().equals("true")) {
                            //showToast("Đăng ký thành công");
                            Toast.makeText(ThemThongBao.this,"Thêm thông báo thành công",Toast.LENGTH_SHORT).show();


                            Intent chuyenlayout = new Intent(ThemThongBao.this, ListThongBao.class);
                            chuyenlayout.putExtra("ma", ma);
                            startActivity((chuyenlayout));
                        }
                        else{
                            Toast.makeText(ThemThongBao.this,"Thêm thông báo thất bại có thể do trùng mã thông báo",Toast.LENGTH_SHORT).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThemThongBao.this, "Thêm thông báo lỗi  !" + error.toString(), Toast.LENGTH_SHORT ).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("td",td);
                params.put("nd",nd);
                params.put("ma",id);
                params.put("mathongbao",mathongbao);

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
