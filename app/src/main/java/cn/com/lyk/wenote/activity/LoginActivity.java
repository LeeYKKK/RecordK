package cn.com.lyk.wenote.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.com.lyk.wenote.R;
import cn.com.lyk.wenote.been.UserInfo;
import cn.com.lyk.wenote.service.ApiService;
import cn.com.lyk.wenote.utils.DataCacheUtil;
import cn.com.lyk.wenote.utils.NetUtil;
import cn.com.lyk.wenote.utils.RegexUtil;
import cn.com.lyk.wenote.utils.ResultMsg;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String USERINFO_OBJ_NAME="userInfo.obj";
    @ViewInject(R.id.edLoginEmail)
    private EditText edLoginEmail;
    @ViewInject(R.id.edLoginPassword)
    private EditText edLoginPassword;
    @ViewInject(R.id.btLoginUser)
    private Button btLoginUser;
    @ViewInject(R.id.tlLoginEmail)
    private TextInputLayout tlLoginEmail;
    private Call<ResultMsg> loginCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x.view().inject(this);
        initEvent();

    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Event(value = R.id.btLoginUser)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLoginUser:

                if (edLoginEmail.getText().toString() == null ||
                        edLoginPassword.getText().toString() == null) {
                    Toast.makeText(this, "邮箱和密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!RegexUtil.EMAIL_PATTERN.matcher(
                        edLoginEmail.getText().toString()).matches()) {
                    //设置邮箱格式错误的提示
                    tlLoginEmail.setErrorEnabled(true);
                    tlLoginEmail.setError("");
                } else {
                    //登录操作
                    loginCall();
                }
                break;
        }

    }

    /**
     * 登录的网络请求
     */
    private void loginCall() {
        loginCall = NetUtil.configRetodt(ApiService.class)
                .userLogin(edLoginEmail.getText().toString(), edLoginPassword.getText().toString());
        loginCall.enqueue(new Callback<ResultMsg>() {
            @Override
            public void onResponse(Call<ResultMsg> call, Response<ResultMsg> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //保存用户信息
                        saveUserInfo();
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);
                    } else if (response.body().getCode().equals("403")) {
                        Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResultMsg> call, Throwable t) {
                Logger.e("Error>>" + t.toString());

            }
        });

    }

    /**
     * 保存用户信息
     */
    private void saveUserInfo() {
        String loginEmail = edLoginEmail.getText().toString();
        String loginPassword = edLoginPassword.getText().toString();
        UserInfo userInfo = new UserInfo();
        userInfo.email=loginEmail;
        userInfo.password=loginPassword;
        Gson gson=new Gson();
        String info= DataCacheUtil.getInfo(USERINFO_OBJ_NAME);
        List<UserInfo>userInfos=gson.fromJson(info,new TypeToken<List<UserInfo>>(){}.getType());
        if(userInfos==null){
            userInfos=new ArrayList<>();
        }
        userInfos.add(userInfo);
        DataCacheUtil.saveInfo(userInfos,USERINFO_OBJ_NAME);

    }


    private void initEvent() {
        edLoginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tlLoginEmail.setErrorEnabled(false);

            }
        });
    }


}
