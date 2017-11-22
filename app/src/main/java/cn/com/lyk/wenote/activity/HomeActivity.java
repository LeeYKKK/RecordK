package cn.com.lyk.wenote.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import cn.com.lyk.wenote.R;
import cn.com.lyk.wenote.been.UserInfo;
import cn.com.lyk.wenote.utils.DataCacheUtil;
import cn.com.lyk.wenote.utils.PermissionUtil;

import static cn.com.lyk.wenote.utils.PermissionUtil.REQUEST_WRITE;

public class HomeActivity extends AppCompatActivity {
    private static final String USER_INFO_OBJ_NAME = "userInfo.obj";//家长信息文件名
    @ViewInject(R.id.btAdd)
    private Button btAdd;
    @ViewInject(R.id.btLogin)
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        x.view().inject(this);
        //检查是否已经授权文件管理
        PermissionUtil.checkVersion(HomeActivity.this);
        //检查登录状态
        checkLoginState();

    }


    private void checkLoginState() {
        String info = DataCacheUtil.getInfo(USER_INFO_OBJ_NAME);
        Gson gson = new Gson();
        List<UserInfo> userInfos = gson.fromJson(info, new TypeToken<List<UserInfo>>() {
        }.getType());
        if (userInfos != null && userInfos.size() != 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Event(value = {R.id.btAdd, R.id.btLogin})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdd:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.btLogin:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                break;

        }
    }
    //判断授权的方法  授权成功直接调用写入方法  这是监听的回调
    //参数  上下文   授权结果的数组   申请授权的数组
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_WRITE&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //检查登录状态
            checkLoginState();
        }

    }
}
