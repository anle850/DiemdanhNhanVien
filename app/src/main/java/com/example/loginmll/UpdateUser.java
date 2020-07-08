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

public class UpdateUser extends AppCompatActivity {
    private EditText  name, pass, email;
    private Button btn_update, btn_huy;



    private String url_regist =  "http://192.168.1.28/webservice/update.php", id, activity, zid, ima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_nhan_vien);

        Intent intent = getIntent();
        id = intent.getStringExtra("ma");
        zid = intent.getStringExtra("zid");
        ima = intent.getStringExtra("ima");

        activity = intent.getStringExtra("activity");

        name = findViewById(R.id.editTextName);
        pass = findViewById(R.id.editTextPW);
        email = findViewById(R.id.editTextEmail);
        //c_pass = findViewById(R.id.editTextCF);


        btn_update = findViewById(R.id.btnDki);
        btn_huy = findViewById(R.id.btnHuy);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String tma = id.getText().toString().trim();
//                String tmatkhau = pass.getText().toString().trim();
//                String tname = name.getText().toString().trim();
//                String temail = email.getText().toString().trim();


                if (pass.getText().toString().isEmpty() && name.getText().toString().isEmpty() && email.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Không được để trống nếu muốn cập nhật.", Toast.LENGTH_SHORT).show();
                else if (pass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Xác minh mật khẩu !", Toast.LENGTH_SHORT).show();

                }

                else if (pass.length() < 2){
                    Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();

                }else if(activity.equals("0")){
                    RegistRR(id, activity);
                }else RegistRR(zid, activity);

            }

        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
    private void RegistRR(final String ma, final String activity){
//        loading.setVisibility(View.VISIBLE);
//        btn_regist.setVisibility(View.GONE);
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
                            Toast.makeText(UpdateUser.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                            if(activity.equals("0")){
                                Intent chuyenlayout = new Intent(UpdateUser.this, User.class);
                                chuyenlayout.putExtra("ma", id);
                                chuyenlayout.putExtra("activity", activity);
                                startActivity((chuyenlayout));
                            }
                            else {
                                Intent chuyenlayout = new Intent(UpdateUser.this, InfUser.class);
                                chuyenlayout.putExtra("ma", ima);
                                chuyenlayout.putExtra("activity", activity);
                                startActivity((chuyenlayout));
                            }


                        }
                        else{
                            Toast.makeText(UpdateUser.this,"Cập nhật nhân viên thất bại",Toast.LENGTH_SHORT).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateUser.this, "Cập nhật nhân viên lỗi  !" + error.toString(), Toast.LENGTH_SHORT ).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id",ma);
                params.put("pass",pass);
                params.put("ten",name);
                params.put("email",email);

                params.put("activity",activity);
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
