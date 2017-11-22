package cn.com.lyk.wenote.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Base64;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import cn.com.lyk.wenote.been.UserInfo;

/**
 * Created by lyk on 2017/11/18.
 */

public class DataCacheUtil {
    private static final String DATA_SAVE_PATH = Environment.getExternalStorageDirectory()+"/weNote/UserInfo/";

    /* *保存对象到SharedPreferences
             * @param context 调用的Activity
             * @param obj 要保存的对象，需实现序列号接口
             * @param key 键名称
             *@return 返回是否保存成功
             */
    public static boolean saveObjectToPreference(Activity context, Object obj, String key) {
        boolean isSaveSuccess = false;
        SharedPreferences preferences = context.getPreferences(Context.MODE_PRIVATE);
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(obj);
            // 将字节流编码成base64的字符窜
            String base64Obj = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, base64Obj);
            isSaveSuccess = editor.commit();
            Logger.d("存储" + isSaveSuccess + " 数据：" + base64Obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSaveSuccess;
    }


    /**
     * 从SharedPreferences读取缓存的对象
     *
     * @param context 调用的Activity
     * @param key     键名称
     * @return 取到后返回对象，否则返回null
     */
    public static Object getObjectFromPreferences(Activity context, String key) {
        Object obj = null;
        SharedPreferences preferences = context.getPreferences(Context.MODE_PRIVATE);
        String objString = preferences.getString(key, "");
        if (objString != null && !objString.equals("")) {
            byte[] listByte = Base64.decode(objString, Base64.DEFAULT);
            InputStream sbs = new ByteArrayInputStream(listByte);
            //从流中读取对象
            try {
                ObjectInputStream objIn = new ObjectInputStream(sbs);
                obj = objIn.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static void saveInfo(List<UserInfo> content, String targetFileName) {
        Logger.i("本地路径>>"+DATA_SAVE_PATH);
        Gson gson = new Gson();
        String contentJson = gson.toJson(content);
        String sdStatus = Environment.getExternalStorageState();
        if(!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        try {
            File path = new File(DATA_SAVE_PATH);
            File file = new File(DATA_SAVE_PATH + targetFileName);
            if(!path.exists()) {
                path.mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream stream = new FileOutputStream(file);
            byte[] buf = contentJson.getBytes();
            stream.write(buf);
            stream.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String getInfo(String targetFileName) {
        Logger.i("本地路径>>"+DATA_SAVE_PATH);

        Gson gson = new Gson();
        File file = new File(DATA_SAVE_PATH + targetFileName);
        if(!file.exists() || !file.isFile()){
            return null;
        }
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line;
            while ((line = br.readLine()) != null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * 保存对象到SD卡
     * @param obj
     * @param targetFileName
     */
    public static void saveObj(Object obj, String targetFileName){
        Logger.i("保存对象：" + targetFileName);
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream(DATA_SAVE_PATH + targetFileName));
            os.writeObject(obj);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从SD卡读取对象
     * @param targetFileName
     */
    public static Object getObj(String targetFileName){
        Logger.i("读取对象：" + targetFileName);
        File objFile = new File(DATA_SAVE_PATH + targetFileName);
        if(!objFile.exists()){
            return null;
        }
        Object obj = null;
        try {
            ObjectInputStream oi = new ObjectInputStream(
                    new FileInputStream(objFile.getAbsoluteFile()));
            obj = oi.readObject();
            oi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
