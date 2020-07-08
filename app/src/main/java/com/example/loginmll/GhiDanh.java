package com.example.loginmll;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import android.widget.Toast;
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

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class GhiDanh extends AppCompatActivity {
    DatePicker datePicker;

    Button updateDateButton, btnTk, btnTb;
    //    EditText edtNgayLam;
    //Button btnGhiDanh;
    String urlInsert = "http://192.168.1.28/webservice/ghidanh.php", zid;



    KeyStore keyStore;
    // Variable used for storing the key in the Android Keystore container
    final String KEY_NAME = "androidHive";
    Cipher cipher;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        //dateValueTextView = (TextView) findViewById(R.id.date_selected_text_view);
        updateDateButton = (Button) findViewById(R.id.update_date_button);
        btnTk = (Button) findViewById(R.id.buttonTK);
        btnTb = (Button) findViewById(R.id.buttonTB);

        TextView m = findViewById(R.id.txtTHANG);



        final Calendar calendar = Calendar.getInstance();
        final String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1) ;

        // disable dates before days and after days after today
        Calendar today = Calendar.getInstance();
        Calendar DaysAgo = (Calendar) today.clone();
        DaysAgo.add(Calendar.DATE, 0);
        Calendar DaysLater = (Calendar) today.clone();
        DaysLater.add(Calendar.DATE, 0);
        datePicker.setMinDate(DaysAgo.getTimeInMillis());
        datePicker.setMaxDate(DaysLater.getTimeInMillis());

//        m.setText(month);

        Intent intent = getIntent();
        zid = intent.getStringExtra("ma");
        //manv = intent.getStringExtra("ma");

        //date = DateFormat.getInstance().toString().trim();
        //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(datePicker);


//        ///////////////////////////////
//        final Executor executor = Executors.newSingleThreadExecutor();
//        final GhiDanh ghidanh = this;
//
//        final BiometricPrompt biometricPrompt;
////        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//            biometricPrompt = new BiometricPrompt.Builder(GhiDanh.this)
//                    .setTitle("Xác nhập vân tay")
//                    .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    }).build();
//
//
//
////        }



        updateDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                ////
                Dialog dialog = new Dialog(GhiDanh.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_fingerprint);
                //
                KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
                FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

                textView = (TextView) findViewById(R.id.errorText);
                //
                if(!fingerprintManager.isHardwareDetected()){
                    /**
                     * An error message will be displayed if the device does not contain the fingerprint hardware.
                     * However if you plan to implement a default authentication method,
                     * you can redirect the user to a default authentication activity from here.
                     * Example:
                     * Intent intent = new Intent(this, DefaultAuthenticationActivity.class);
                     * startActivity(intent);
                     */
                    textView.setText("Your Device does not have a Fingerprint Sensor");
                }else {
                    // Checks whether fingerprint permission is set on manifest
                    if (ActivityCompat.checkSelfPermission(GhiDanh.this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                        textView.setText("Fingerprint authentication permission not enabled");
                    }else{
                        // Check whether at least one fingerprint is registered
                        if (!fingerprintManager.hasEnrolledFingerprints()) {
                            textView.setText("Register at least one fingerprint in Settings");
                        }else{
                            // Checks whether lock screen security is enabled or not
                            if (!keyguardManager.isKeyguardSecure()) {
                                textView.setText("Lock screen security not enabled in Settings");
                            }else{
                                generateKey();

                                if (cipherInit()) {
                                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                                    FingerprintHandler helper = new FingerprintHandler(GhiDanh.this);
                                    helper.startAuth(fingerprintManager, cryptoObject);
                                }
                            }
                        }
                    }
                    dialog.cancel();
                } dialog.show();
                //

            }

            private boolean cipherInit(){
                try {
                    cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
                } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                    throw new RuntimeException("Failed to get Cipher", e);
                }

                try {
                    keyStore.load(null);
                    SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                            null);
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    return true;
                } catch (KeyPermanentlyInvalidatedException e) {
                    return false;
                } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
                    throw new RuntimeException("Failed to init Cipher", e);
                }
            }

            private void generateKey() {
                try {
                    keyStore = KeyStore.getInstance("AndroidKeyStore");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                KeyGenerator keyGenerator;
                try {
                    keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
                } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
                    throw new RuntimeException("Failed to get KeyGenerator instance", e);
                }

                try {
                    keyStore.load(null);
                    keyGenerator.init(new
                            KeyGenParameterSpec.Builder(KEY_NAME,
                            KeyProperties.PURPOSE_ENCRYPT |
                                    KeyProperties.PURPOSE_DECRYPT)
                            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                            .setUserAuthenticationRequired(true)
                            .setEncryptionPaddings(
                                    KeyProperties.ENCRYPTION_PADDING_PKCS7)
                            .build());
                    keyGenerator.generateKey();
                } catch (NoSuchAlgorithmException |
                        InvalidAlgorithmParameterException
                        | CertificateException | IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        /////////////////////////////

        btnTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(GhiDanh.this, User.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", "0");
                startActivity((chuyenlayout));
            }
        });

        btnTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenlayout = new Intent(GhiDanh.this, ListThongBao.class);
                chuyenlayout.putExtra("ma", zid);
                chuyenlayout.putExtra("activity", "0");
                startActivity((chuyenlayout));
                //Toast.makeText(InfUser.this, "Chưa có layout thông báo !" , Toast.LENGTH_SHORT).show();

            }
        });
    }




    //////////////
    public String getma (){
        return zid;
    }



    public void ThemNgayLam( final String manv){


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")){
                    Toast.makeText(GhiDanh.this, " Ghi danh thành công ! "  , Toast.LENGTH_SHORT).show();
                } else Toast.makeText(GhiDanh.this, " Không thể ghi danh quá 2 lần 1 ngày ! "  , Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GhiDanh.this, "Lỗi" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("manv", manv );
                return params;
            }
        }; requestQueue.add(stringRequest);
    }
}




