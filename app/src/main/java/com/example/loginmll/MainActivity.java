package com.example.loginmll;



import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class MainActivity extends AppCompatActivity {

    private EditText id, pass;
    private Button btn_login;
    private TextView  btn_forget_pass;

    public static String filename = "ValueUser";

    SharedPreferences SP;

    private ProgressBar loading;
    private  static String url_login = "http://192.168.1.28/webservice/demo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        //loading = findViewById(R.id.loading);
        id = findViewById(R.id.txtName);
        pass = findViewById(R.id.txtPass);
        btn_login = findViewById(R.id.btnThemThongBao);
        //btn_regist = findViewById(R.id.btndangki);
        btn_forget_pass = findViewById(R.id.btnForget);

        nhotk();

//        final CheckBox cbnhotaikhoan = findViewById(R.id.checkBoxForget);



//        id.setText(getname);
//        pass.setText(getpass);

//        if (getname.equals(null) || getname.equals(""))
//            cbnhotaikhoan.setChecked(false);
//        else
//            cbnhotaikhoan.setChecked(true);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mid = id.getText().toString().trim();
                String mpass = pass.getText().toString().trim();

                if (isConnected() == true) {

                    if (id.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập mã tài khoản.", Toast.LENGTH_SHORT).show();
                    else if (pass.getText().toString().isEmpty())

                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập mật khẩu.", Toast.LENGTH_SHORT).show();

                    else {
                        Login(mid,mpass);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Không có kết nối mạng.", Toast.LENGTH_SHORT).show();
                }
            }
        }




        );
//        btn_regist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Register.class));
//            }
//        });

        btn_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgetPass.class));
            }
        });
    }
    public void nhotk(){
        EditText qid = findViewById(R.id.txtName);
        EditText qpass = findViewById(R.id.txtPass);

        SP = getSharedPreferences(filename, 0);

        String getname = SP.getString("key1", "");
        String getpass = SP.getString("key2", "");
        final CheckBox cbnhotaikhoan = findViewById(R.id.checkBoxForget);

        qid.setText(getname);
        qpass.setText(getpass);


        if (getname.equals(null) || getname.equals(""))
            cbnhotaikhoan.setChecked(false);
        else
            cbnhotaikhoan.setChecked(true);

    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private void Login(final String id, final String pass) {

//        loading.setVisibility(View.VISIBLE);
//        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login,
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

                                    String name = object.getString("name").trim();
                                    String matkhau = object.getString("pass").trim();

                                    Toast.makeText(MainActivity.this, "Chào bạn! " +  name , Toast.LENGTH_SHORT).show();
                                    String status = object.getString("status").trim();

                                    TextView txtname = findViewById(R.id.txtName);
                                    EditText txtpass = findViewById(R.id.txtPass);
                                    CheckBox checkboxnhomk = findViewById(R.id.checkBoxForget);


                                    if(checkboxnhomk.isChecked()) {
                                        SharedPreferences.Editor editit = SP.edit();
                                        editit.putString("key1", txtname.getText().toString());
                                        editit.putString("key2", txtpass.getText().toString());
                                        editit.commit();
                                    }else {
                                        SharedPreferences.Editor editit = SP.edit();
                                        editit.putString("key1", null);
                                        editit.putString("key2", null);
                                        editit.commit();
                                    }

                                    if(matkhau.equals("0") ) {
                                        Intent chuyenlayout = new Intent(MainActivity.this, ChangePass.class);
                                        chuyenlayout.putExtra("ma", id);
//                                        chuyenlayoutGV.putExtra("pass", pass);
                                        startActivity((chuyenlayout));
                                    }else

                                    if (status.equals("1")) {

                                        Intent chuyenlayout = new Intent(MainActivity.this, InfUser.class);
                                        chuyenlayout.putExtra("ma", id);
                                        chuyenlayout.putExtra("activity", "1");
                                        startActivity((chuyenlayout));
                                        //Toast.makeText(MainActivity.this, "Chưa xong " +  name , Toast.LENGTH_SHORT).show();
                                    } else if (status.equals("0")) {
                                        Intent chuyenlayout = new Intent(MainActivity.this, GhiDanh.class);
                                        chuyenlayout.putExtra("ma", id);
                                        chuyenlayout.putExtra("activity", "0");
                                        startActivity((chuyenlayout));
                                    }




//                                    loading.setVisibility(View.GONE);
                                }
                            } else Toast.makeText(MainActivity.this, "Sai mã tài khoản hoặc mật khẩu" , Toast.LENGTH_SHORT).show();

                        }catch (JSONException e){
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Đăng nhập lỗi!" + e.toString(), Toast.LENGTH_SHORT ).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Đăng nhập lỗi !" + error.toString(), Toast.LENGTH_SHORT ).show();
//                        loading.setVisibility(View.GONE);
//                        btn_regist.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id",id);
                params.put("pass",pass);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
