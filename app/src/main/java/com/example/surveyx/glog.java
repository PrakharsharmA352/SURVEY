package com.example.surveyx;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.FloatRange;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import android.os.Bundle;
import com.google.android.gms.common.SignInButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class glog extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    TextView textView;
    String Name,Email;
    private static final int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glog);

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    GoogleSignInAccount acct= GoogleSignIn.getLastSignedInAccount( this);
    if(acct!=null)
    {
        Intent  intn=new Intent(getApplicationContext(),Home.class);
        startActivity(intn);
        finish();
    }
    else{
       /* Intent bk=new Intent(getApplicationContext(),glog.class);
        startActivity(bk);
        finish();*/
    }

        signInButton=(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
      //  Log.e("TAG", "RESPONSE IS " + result);
        if(result.isSuccess()) {
            String url = "http://192.168.227.45/android_conn/emailcheck.php";

            // creating a new variable for our request queue
            RequestQueue queue = Volley.newRequestQueue(glog.this);
            Log.e("TAG","QUEUE"+queue);
            // System.out.println("starting google Signing result");
            StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("TAG", "RESPONSE IS " + response);
                    try {
                        // on below line passing our response to json object.
                        JSONObject jsonObject = new JSONObject(response);

                        Log.e("TAG", "JSON IS " + jsonObject.get("Name"));

                        // on below line we are checking if the response is null or not.
                   /* if (jsonObject("Name") == null) {
                        // displaying a toast message if we get error
                       // Toast.makeText(MainActivity.this, "Please enter valid id.", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"Invalid Details",Toast.LENGTH_LONG).show();
                    } else {
                        // if we get the data then we are setting it in our text views in below line.
                        courseNameTV.setText(jsonObject.getString("Name"));
                        courseDescTV.setText(jsonObject.getString("Email"));
                        courseDurationTV.setText(jsonObject.getString("Phone"));
                        courseCV.setVisibility(View.VISIBLE);
                    }
                    // on below line we are displaying*/
                        // a success toast message.
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // method to handle errors.
                    Log.e("TAG", "ERROR IS " + error);
                    Toast.makeText(glog.this, "Fail to get course" + error, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public String getBodyContentType() {
                    // as we are passing data in the form of url encoded
                    // so we are passing the content type below
                    return "application/x-www-form-urlencoded; charset=UTF-8";
                }

                @Override
                protected Map<String, String> getParams() {

                    // below line we are creating a map for storing our values in key and value pair.
                    Map<String, String> params = new HashMap<String, String>();

                    // on below line we are passing our key and value pair to our parameters.
                //    params.put("id", id);
                    // at last we are returning our params.
                    return params;
                }
            };
            // below line is to make
            // a json object request.
            queue.add(request);


            gotoProfile();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }
    private void gotoProfile(){

      Intent intent=new Intent(getApplicationContext(),Home.class);
        startActivity(intent);
   finish();
    }
public class FromJSON{
    public boolean error;
    public String message;	//Retrieval Successful!
    public String Name;	//prakhar
    public String Email;	//a@gmail.com




}
}