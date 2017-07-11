package com.example.sudarshan.sonicshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    static LoginActivity INS;
    String data;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.link_signup)
    TextView linkSignup;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INS=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitle("Login");
        inputPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                }

                return false;
            }
        });
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
    public static LoginActivity getActivityInstance()
    {
        return INS;
    }




    public String getData()
    {
        return this.data;
    }

    private void login(){
        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);


       final String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        RetrofitInterface client= RetrofitBuilder.createService(RetrofitInterface.class);
        Call<users> call = client.auth(email,password);
//        Cart c= new Cart();
//        c.setUemail(email);
        data= email;
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        call.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {

                   if(response.isSuccessful()) {
                       users u = response.body();
                       u.setUname(email);
                       u.setUpass(password);
                       progressDialog.dismiss();
                       Intent intent = new Intent(LoginActivity.this, NavDrawer.class);
                       //intent.putExtra("Email",email);
                       startActivity(intent);

                       finish();
                   }

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
                progressDialog.dismiss();
                onLoginFailed();


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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_SIGNUP) {
//            if (resultCode == RESULT_OK) {
//
//                // TODO: Implement successful signup logic here
//                // By default we just finish the Activity and log them in automatically
//                this.finish();
//            }
//        }
//
// }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
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
