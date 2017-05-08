package com.example.sudarshan.sonicshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.link_signup)
    TextView linkSignup;
    private static final int REQUEST_SIGNUP =100;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitle("Login");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFormValid()) {
                    login();
                }
            }
        });
        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
    }
    private void login(){
        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

       final String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();
        users u;
        RetrofitInterface client= RetrofitBuilder.createService(RetrofitInterface.class);
        Call<users> call = client.auth(email,password);
        call.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if(response.isSuccessful())
                {
                   users u= response.body();
                    Log.e(TAG, "onResponse: "+u.getUname());
                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
//        if(!email.contentEquals("fag")&&!password.contentEquals("12345")){
//            progressDialog.dismiss();
//            Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_LONG);
//            btnLogin.setEnabled(false);
//
//        }
//        else {


//            // TODO: Implement your own authentication logic here.
//            new android.os.Handler().postDelayed(
//                    new Runnable() {
//                        public void run() {
//                            // On complete call either onLoginSuccess or onLoginFailed
//                            if(email.contentEquals("fag")&&password.contentEquals("12345")){
//                                onLoginSuccess();
//                            }
//                            else {
//                                progressDialog.dismiss();
//
//                                onLoginFailed();
//
//                            }
//                        }
//                    }, 2000);
    }
    private boolean isFormValid(){
        if(inputEmail.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Username cannot be empty",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(inputPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }
    private void onLoginSuccess(){
        btnLogin.setEnabled(true);
        finish();
        Intent intent = new Intent(this,NavDrawer.class);
        startActivity(intent);
    }
    private void onLoginFailed(){
        Log.i(TAG,"It's getting called");
        inputEmail.setText("");
        inputPassword.setText("");
        Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }
}
