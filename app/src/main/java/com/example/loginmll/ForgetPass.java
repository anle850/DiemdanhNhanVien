package com.example.loginmll;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import android.app.Activity;
import android.view.Window;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPass extends AppCompatActivity {

    private EditText id, email;
    private Button btn_get_pass, btn_cancel;


    private ProgressBar loading;
    private  static String url_get_pass = "http://192.168.1.28/webservice/forgetPass.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quen_mat_khau);

        //loading = findViewById(R.id.loading);
        id = findViewById(R.id.editTextID);
        email = findViewById(R.id.editTextEmail);
        btn_get_pass = findViewById(R.id.btnGetPass);
        btn_cancel = findViewById(R.id.btnHuy);

        btn_get_pass.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             String mid = id.getText().toString().trim();
                                             String memail = email.getText().toString().trim();

                                             if(!mid.isEmpty() || !memail.isEmpty()) {
                                                 GetPass(mid, memail);
                                             } else{
                                                 id.setError("Chưa nhập mã tài khoản");
                                                 email.setError("Chưa nhập email");
                                             }
                                         }
                                     }

        );
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void GetPass(final String id, final String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_get_pass,
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
                                    //String status = object.getString("status").trim();
                                    Toast.makeText(ForgetPass.this, "Thành công" , Toast.LENGTH_SHORT).show();

                                    Intent ii = new Intent(ForgetPass.this,ChangePass.class);
                                    ii.putExtra("ma",id);
                                    startActivity(ii);
                                }
                            } else Toast.makeText(ForgetPass.this, "Sai mã tài khoản hoặc email" , Toast.LENGTH_SHORT).show();

                        }catch (
                                JSONException e){
                            e.printStackTrace();
                            Toast.makeText(ForgetPass.this, "Đổi mật khẩu lỗi!" + e.toString(), Toast.LENGTH_SHORT ).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);

                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_get_pass.setVisibility(View.VISIBLE);
                        Toast.makeText(ForgetPass.this, "Lỗi !" + error.toString(), Toast.LENGTH_SHORT ).show();
//                        loading.setVisibility(View.GONE);
//                        btn_regist.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id",id);
                params.put("email",email);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
