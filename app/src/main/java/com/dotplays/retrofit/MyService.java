package com.dotplays.retrofit;

import com.dotplays.retrofit.model.Login;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyService {
    // path
    @POST("/idocNet.Test.Logistic.WMS/Services/Login")
    Call<Login> requestLogin(@Query("username") String username
            , @Query("password") String password);



}
