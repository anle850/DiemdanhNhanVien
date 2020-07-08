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

public class Register extends AppCompatActivity {
    private EditText id, name, pass, email;
    private Button btn_regist, btn_huy;



    private String url_regist =  "http://192.168.1.28/webservice/insert.php", ma,activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        id = findViewById(R.id.editTextID);
        name = findViewById(R.id.editTextName);
        pass = findViewById(R.id.editTextPW);
        email = findViewById(R.id.editTextEmail);
        //c_pass = findViewById(R.id.editTextCF);
        Intent intent = getIntent();
        ma = intent.getStringExtra("ma");
        activity =  intent.getStringExtra("activity");

        btn_regist = findViewById(R.id.btnDki);
        btn_huy = findViewById(R.id.btnHuy);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String tma = id.getText().toString().trim();
//                String tmatkhau = pass.getText().toString().trim();
//                String tname = name.getText().toString().trim();
//                String temail = email.getText().toString().trim();


                if (id.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập mã tài khoản.", Toast.LENGTH_SHORT).show();
                else if (pass.getText().toString().isEmpty() || pass.length() < 2)

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập mật khẩu hoặc mật khẩu quá ngắn.", Toast.LENGTH_SHORT).show();

                else if (name.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập họ tên.", Toast.LENGTH_SHORT).show();

                else if (email.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập email.", Toast.LENGTH_SHORT).show();

                else {
                    RegistRR();
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
    private void RegistRR(){
//        loading.setVisibility(View.VISIBLE);
//        btn_regist.setVisibility(View.GONE);

        final String id = this.id.getText().toString().trim();
        final String name = this.name.getText().toString().trim();
        final String pass = this.pass.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        //final String c_pass = this.c_pass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_regist.trim(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if(response.trim().equals("true")) {
                            //showToast("Đăng ký thành công");
                            Toast.makeText(Register.this,"Thêm nhân viên thành công",Toast.LENGTH_SHORT).show();


                            Intent chuyenlayout = new Intent(Register.this, InfUser.class);
                            chuyenlayout.putExtra("ma", ma);
                            chuyenlayout.putExtra("activity", activity);
                            startActivity((chuyenlayout));
                        }
                         else{
                            Toast.makeText(Register.this,"Thêm nhân viên thất bại",Toast.LENGTH_SHORT).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, "Thêm nhân viên lỗi  !" + error.toString(), Toast.LENGTH_SHORT ).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id",id);
                params.put("pass",pass);
                params.put("ten",name);
                params.put("email",email);

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
