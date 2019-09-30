package com.dotplays.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dotplays.retrofit.model.Login;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    String url = "http://113.190.232.235:30100/idocNet.Test.Logistic.WMS/Services/Login";
    String username = "wms.csc";
    String password = "0StFbim5pSrmBncQU0RpnA";

    static String BASE_URL = "http://113.190.232.235:30100";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void retrofit(View view) {

        MyService myService =
                MyRetrofit.getInstance().create(MyService.class);

        myService.requestLogin(username, password).enqueue(
                new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call,
                                           Response<Login> response) {
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(
                                response.body().getException().getErrorDetail());
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {

                    }
                });

    }

    public void volley(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Login login = new Gson().fromJson(response
                                , Login.class);
                        // response
                        Log.d("Response", response);
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(
                                login.getException().getErrorDetail());


                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("passwrord", password);

                return params;
            }
        };
        queue.add(postRequest);

    }
}
