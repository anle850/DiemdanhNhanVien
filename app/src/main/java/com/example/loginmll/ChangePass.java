package com.example.loginmll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class ChangePass extends AppCompatActivity {

    private EditText pass, cpass;
    private Button btn_accpet, btn_cancel;
    String url ="http://192.168.1.28/webservice/changePass.php", ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        btn_accpet = findViewById(R.id.btnAccpet);
        btn_cancel = findViewById(R.id.btnHuy);

        pass = findViewById(R.id.editTextPass);
        cpass = findViewById(R.id.editTextCpass);

        Intent intent = getIntent();
        ma = intent.getStringExtra("ma");



        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mpass = pass.getText().toString().trim();
                String mcpass = cpass.getText().toString().trim();

                // Toast.makeText(DoiMatKhauActivity.this,tdn+mkcu+mkmoi+mknhaplai,Toast.LENGTH_SHORT).show();
                if (pass.getText().toString().isEmpty() || pass.length() < 2 )

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập mật khẩu hoặc mật khẩu quá ngắn.", Toast.LENGTH_SHORT).show();
                else if (cpass.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập lại mật khẩu.", Toast.LENGTH_SHORT).show();
                else if (!mpass.equals(mcpass)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không trùng nhau thử lại.", Toast.LENGTH_SHORT).show();

                } else {
                    doimatkhau(ma, mpass);
                }


            }

    });
    }

    private void doimatkhau(final String ma, final String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")){

                    Toast.makeText(ChangePass.this,"Đổi mật khẩu thành công",Toast.LENGTH_LONG).show();
                    Intent ii = new Intent(ChangePass.this,MainActivity.class);
                    startActivity(ii);
                }else{
                    Toast.makeText(ChangePass.this,"Lỗi đổi mật khẩu  ",Toast.LENGTH_LONG).show();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChangePass.this,"Xay Ra Loi" + error,Toast.LENGTH_LONG).show();
                Log.d("AAA","loi\n"+error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("ma",ma);
                params.put("pass", pass);


                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }


}
