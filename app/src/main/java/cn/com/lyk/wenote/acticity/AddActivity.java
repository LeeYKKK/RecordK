package cn.com.lyk.wenote.acticity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.com.lyk.wenote.R;
import cn.com.lyk.wenote.service.ApiService;
import cn.com.lyk.wenote.utils.DateUtil;
import cn.com.lyk.wenote.utils.NetUtil;
import cn.com.lyk.wenote.utils.RegexUtil;
import cn.com.lyk.wenote.utils.ResultMsg;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {
    @ViewInject(R.id.tlEmail)
    private TextInputLayout tlEmail;
    //邮箱
    @ViewInject(R.id.edAddEmail)
    private EditText edAddEmail;
    //密码
    @ViewInject(R.id.edAddPassword)
    private EditText edAddPassword;
    //确认密码
    @ViewInject(R.id.edAffirmPassword)
    private EditText edAffirmPassword;
    //注册
    @ViewInject(R.id.btAdd)
    private Button btAdd;
    @ViewInject(R.id.imBack)
    private ImageView imBack;
    private Call<ResultMsg> addCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        x.view().inject(this);//注入view事件
        //EditText监控事件
        initEvent();
    }

    private void initEvent() {
        edAddEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tlEmail.setErrorEnabled(false);

            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!RegexUtil.EMAIL_PATTERN.matcher(edAddEmail.getText().toString().trim()).matches()) {
                    tlEmail.setErrorEnabled(true);
                    tlEmail.setError("邮箱格式错误");
                } else if (!edAddPassword.getText().toString().equals(edAffirmPassword.getText().toString())) {
                    Toast.makeText(AddActivity.this, "您输入的密码不匹配", Toast.LENGTH_SHORT).show();
                } else {
                    AddCallall();

                }
            }
        });
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AddCallall() {
        addCall = NetUtil.configRetodt(ApiService.class)
                .user(edAddEmail.getText().toString(), edAddPassword.getText().toString(),
                        DateUtil.getFullDate());
        addCall.enqueue(new Callback<ResultMsg>() {
            @Override
            public void onResponse(Call<ResultMsg> call, Response<ResultMsg> response) {
                if (response.isSuccessful()) {
                    if ((response.body().getCode()).equals("200")) {
                        Toast.makeText(AddActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResultMsg> call, Throwable t) {
                Logger.e("Error>>" + t.toString());

            }
        });
    }


}
