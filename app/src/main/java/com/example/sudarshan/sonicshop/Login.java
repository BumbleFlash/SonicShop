package com.example.sudarshan.sonicshop;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;
import static android.os.Build.VERSION_CODES.M;
import static com.facebook.internal.CallbackManagerImpl.RequestCodeOffset.Login;


public class Login extends AppCompatActivity {
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    final int RC_SIGN_IN = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()

                        .setIsSmartLockEnabled(true)
                        // .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                        .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),

                                new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))

                        .build(),
                RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                startActivity(new Intent(getApplicationContext(), Shop.class));
                finish();
                return;
            } else {
                // Sign in failed
                if (response == null) {
                    Snackbar.make(findViewById(android.R.id.content), "Sign in Cancelled", Snackbar.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Shop.class));
                    finish();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(getApplicationContext(), Shop.class));
                                    finish();
                                }
                            });

                    snackbar.show();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Snackbar.make(findViewById(android.R.id.content), "Unknown error", Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }


            Snackbar.make(findViewById(android.R.id.content), "Unknown error", Snackbar.LENGTH_SHORT).show();
        }

        Toast.makeText(getApplicationContext(), "Unknown error",
                Toast.LENGTH_LONG).show();

    }
}













