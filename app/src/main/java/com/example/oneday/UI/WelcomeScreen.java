package com.example.oneday.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.oneday.R;
import com.example.oneday.UI.DashBoard.DashBoard;
import com.example.oneday.UI.PhoneAuthentication.PhoneAuthActivity;
import com.example.oneday.UI.Profile.ProfileSetup;
import com.example.oneday.Utils.AuthMethod;
import com.example.oneday.Utils.PrefManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {

    private static final String EMAIL = "email";
    private static final int RC_GOOGLE_SIGN_IN = 101;
    LoginButton loginButton;
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    String TAG="FACEBOOK AUTH";
    private GoogleSignInClient mGoogleSignInClient;
    String auth_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.oneday",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {
        }
        catch (NoSuchAlgorithmException e) {
        }
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Facebook Callback Manager
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,loginResultFacebookCallback);

        //GOOGLE Sign in
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_sign_in_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    //Facebook Login CallBack
    FacebookCallback<LoginResult> loginResultFacebookCallback= new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d(TAG, "FB AUTH SUCCESS:" + loginResult.getAccessToken().toString());
            handleFacebookAccessToken(loginResult.getAccessToken());
        }

        @Override
        public void onCancel() {
            Toast.makeText(WelcomeScreen.this, "Authentication Cancelled.",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(WelcomeScreen.this, "Error: "+error.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    };

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(WelcomeScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void handleGoogleAccessToken(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(WelcomeScreen.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    void updateUI(FirebaseUser user)
    {
        if(user!=null)
        {
            if(!PrefManager.isProfileSetupComplete(this))
            {
                Intent intent= new Intent(this,ProfileSetup.class);
                startActivity(intent);
            }
            else
                startActivity(new Intent(this, DashBoard.class));

        }

    }
    public void PhoneAuth(View view) {
        PrefManager.setAuthMethodSelected(this, AuthMethod.PHONE_AUTH);
        startActivity(new Intent(this, PhoneAuthActivity.class));
    }

    public void onFbLoginClick(View view) {
        PrefManager.setAuthMethodSelected(this, AuthMethod.FB_AUTH);
        LoginManager.getInstance().logInWithReadPermissions(
                this,
                Arrays.asList("email")
        );
    }
    public void onGoogleLoginClick(View view) {
        PrefManager.setAuthMethodSelected(this, AuthMethod.GOOGLE_AUTH);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
            updateUI(currentUser);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Facebook handling Response
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //Google Handling Response
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                handleGoogleAccessToken(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}