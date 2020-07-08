package com.example.loginmll;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.media.Image;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private GhiDanh context;

    public FingerprintHandler(GhiDanh context){

        this.context = context;

    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("Lỗi trong khi xác thực. " + errString, false);

    }

    @Override
    public void onAuthenticationFailed() {

        this.update("Xác thực lỗi. ", false);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Error: " + helpString, false);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        this.update("", true);

    }

    private void update(String s, boolean b) {

        //TextView paraLabel = (TextView) ((FingerprintActivity).findViewById(R.id.errorText);


        if(b == false){

            //paraLabel.setText(s);
            Toast.makeText(context, "Lỗi !" + s , Toast.LENGTH_SHORT).show();

        } else {

            context.ThemNgayLam(context.getma());


        }

    }
}
