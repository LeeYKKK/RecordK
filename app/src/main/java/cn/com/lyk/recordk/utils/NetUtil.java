package cn.com.lyk.recordk.utils;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lyk on 2017/11/7.
 */

public class NetUtil {
    private static Retrofit retrofit;

    public static <T> T configRetodt(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.getServerURL())
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

    private static OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Response response=chain.proceed(request);
                ResponseBody responseBody=response.body();
                BufferedSource source=responseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer=source.buffer();
                Charset UTF8=Charset.forName("UTF-8");
                try {
                    Logger.e("REQUEST_URL"+request.toString());
                    Logger.e("REQUEST_JSON"+buffer.clone().readString(UTF8));
                    Logger.json(buffer.clone().readString(UTF8));
                }catch (Exception e){
                    Logger.e("出错！！！");
                }
                return response;
            }
        };
        okHttpClient.addInterceptor(interceptor);
        return  okHttpClient.build();
    }
}
