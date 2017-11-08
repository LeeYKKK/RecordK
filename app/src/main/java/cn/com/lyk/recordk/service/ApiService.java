package cn.com.lyk.recordk.service;

import cn.com.lyk.recordk.utils.ResultMsg;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lyk on 2017/11/7.
 */

public interface ApiService {
    //添加用户
    @POST("/userAdd")
    Call<ResultMsg> user(@Query("phoneNumber")String phoneNumber, @Query("password")String password,
                         @Query("registerTime")String registerTime);


}
