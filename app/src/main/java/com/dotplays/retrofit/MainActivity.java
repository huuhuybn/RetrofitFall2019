package com.dotplays.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dotplays.retrofit.model.Login;

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

        myService.requestLogin(username,password).enqueue(
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


    }
}
