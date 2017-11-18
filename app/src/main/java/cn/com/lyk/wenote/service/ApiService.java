package cn.com.lyk.wenote.service;

import cn.com.lyk.wenote.utils.ResultMsg;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lyk on 2017/11/7.
 */

public interface ApiService {
    //添加用户
    @POST("/yk//userAdd")
    Call<ResultMsg> user(@Query("email")String email, @Query("password")String password,
                         @Query("registerTime")String registerTime);
    //登录
    @POST("/yk//userLogin")
    Call<ResultMsg> userLogin(@Query("email") String email,@Query("password")String password);


}
