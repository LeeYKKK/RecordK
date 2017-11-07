package cn.com.lyk.recordk.service;

import cn.com.lyk.recordk.been.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lyk on 2017/11/7.
 */

public interface ApiService {
    @GET("/userAdd")
    Call<User> user(@Query("phoneNumber")String phoneNumber,@Query("password")String password,@Query("registerTime")String registerTime);


}
