package cn.com.lyk.wenote.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.lyk.greendao.FolderInfoDao;
import cn.com.lyk.greendao.NoteDao;
import cn.com.lyk.wenote.R;
import cn.com.lyk.wenote.app.MyApplication;
import cn.com.lyk.wenote.been.FolderInfo;
import cn.com.lyk.wenote.been.Note;
import cn.com.lyk.wenote.utils.Const;
import cn.com.lyk.wenote.utils.DateUtil;
import cn.com.lyk.wenote.utils.UUIDUtil;
import jp.wasabeef.richeditor.RichEditor;

public class AddNoteActivity extends AppCompatActivity {
    @ViewInject(R.id.editor)
    private RichEditor mEditor;

    @ViewInject(R.id.action_undo)
    private ImageButton action_undo;

    @ViewInject(R.id.action_redo)
    private ImageButton action_redo;

    @ViewInject(R.id.action_bold)
    private ImageButton action_bold;

    @ViewInject(R.id.action_heading1)
    private ImageButton action_heading1;

    @ViewInject(R.id.action_heading2)
    private ImageButton action_heading2;

    @ViewInject(R.id.action_heading3)
    private ImageButton action_heading3;

    @ViewInject(R.id.action_heading4)
    private ImageButton action_heading4;

    @ViewInject(R.id.action_heading5)
    private ImageButton action_heading5;

    @ViewInject(R.id.action_heading6)
    private ImageButton action_heading6;

    @ViewInject(R.id.action_indent)
    private ImageButton action_indent;

    @ViewInject(R.id.action_outdent)
    private ImageButton action_outdent;

    @ViewInject(R.id.action_align_left)
    private ImageButton action_align_left;

    @ViewInject(R.id.action_align_center)
    private ImageButton action_align_center;

    @ViewInject(R.id.action_align_right)
    private ImageButton action_align_right;

    @ViewInject(R.id.action_insert_bullets)
    private ImageButton action_insert_bullets;

    @ViewInject(R.id.action_insert_numbers)
    private ImageButton action_insert_numbers;

    @ViewInject(R.id.action_insert_checkbox)
    private ImageButton action_insert_checkbox;
    //相机按钮
    @ViewInject(R.id.imCamera)
    private ImageButton imCamera;
    @ViewInject(R.id.imAddNoteDone)
    private ImageButton imAddNoteDone;
    @ViewInject(R.id.edTitle)
    private EditText edTitle;

    //保存每次拍照后的图片地址
    private String imageFile;
    private FolderInfoDao folderInfoDao;
    private NoteDao noteDao;
    private Note note;
    private static final String regEx_html = "<[^>]+>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        x.view().inject(this);
        initView();
        //初始化DAO
        initGreenDao();
        //添加默认的文件夹
        defaultFolder();
    }

    private void initGreenDao() {
        folderInfoDao = ((MyApplication) getApplication()).getDaoSession().getFolderInfoDao();
        noteDao = ((MyApplication) getApplication()).getDaoSession().getNoteDao();
    }

    //判断有没有默认的文件夹
    private void defaultFolder() {
        List<FolderInfo> folderInfos = folderInfoDao.queryBuilder().where(FolderInfoDao.Properties.Name.eq("我的笔记")).list();
        if (folderInfos.size() == 0)
            createDefaultFolder();
    }

    private void createDefaultFolder() {
        FolderInfo folderInfo = new FolderInfo();
        folderInfo.setName("我的笔记");
        folderInfo.setTime(DateUtil.getFullDate());
        folderInfoDao.insert(folderInfo);

    }

    private void initView() {
        //高
        mEditor.setEditorHeight(200);
        //字体大小
        mEditor.setEditorFontSize(20);
        //设置字体颜色
        mEditor.setEditorFontColor(Color.BLACK);
        // mEditor.setEditorBackgroundColor(Color.BLUE);
        //设置背景颜色
        //mEditor.setBackgroundColor(Color.GREEN);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("笔记...");
        //获取焦点
//        mEditor.setFocusable(true);
//        mEditor.setFocusableInTouchMode(true);
//        mEditor.requestFocus();
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //禁止输入
        //mEditor.setInputEnabled(false);
        note = new Note();
    }

    //点击事件
    @Event(value = {R.id.action_redo, R.id.action_undo, R.id.action_align_center,
            R.id.action_align_left, R.id.action_align_right, R.id.action_bold, R.id.action_heading1
            , R.id.action_heading2, R.id.action_heading3, R.id.action_heading4, R.id.action_heading5,
            R.id.action_heading6, R.id.action_indent, R.id.action_insert_bullets, R.id.action_insert_checkbox,
            R.id.action_insert_numbers, R.id.action_outdent, R.id.imCamera, R.id.imAddNoteDone})
    private void onClick(View view) {
        switch (view.getId()) {
            //撤回
            case R.id.action_undo:
                mEditor.undo();
                break;
            //返回
            case R.id.action_redo:
                mEditor.redo();
                break;
            //加粗
            case R.id.action_bold:
                mEditor.setBold();
                break;
            case R.id.action_heading1:
                mEditor.setHeading(1);
                break;
            case R.id.action_heading2:
                mEditor.setHeading(2);
                break;
            case R.id.action_heading3:
                mEditor.setHeading(3);
                break;
            case R.id.action_heading4:
                mEditor.setHeading(4);
                break;
            case R.id.action_heading5:
                mEditor.setHeading(5);
                break;
            case R.id.action_heading6:
                mEditor.setHeading(6);
                break;
            //右缩进
            case R.id.action_indent:
                mEditor.setIndent();
                break;
            //左缩进
            case R.id.action_outdent:
                mEditor.setOutdent();
                break;
            //左对齐
            case R.id.action_align_left:
                mEditor.setAlignLeft();
                break;
            //居中
            case R.id.action_align_center:
                mEditor.setAlignCenter();
                break;
            //右对齐
            case R.id.action_align_right:
                mEditor.setAlignRight();
                break;
            //项目符号.
            case R.id.action_insert_bullets:
                mEditor.setBullets();
                break;
            //项目编号
            case R.id.action_insert_numbers:
                mEditor.setNumbers();
                break;
            //选择框
            case R.id.action_insert_checkbox:
                mEditor.insertTodo();
                break;
            //相机按钮
            case R.id.imCamera:
                //选择操作弹框
                showPhotoDialog();
                //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imAddNoteDone:
                noteDao.insert(getNote());
                Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    //添加笔记
    private Note getNote() {
        List<FolderInfo> folderInfos = folderInfoDao.queryBuilder().where(
                FolderInfoDao.Properties.Name.eq("我的笔记")).list();
        note.setTitle(edTitle.getText().toString());
        note.setContent(mEditor.getHtml().replace("'", "''").toString());
        note.setSummary(getSummary(mEditor.getHtml().replace("'", "''")
                .replace("&nbsp;", " ").toString()));
        note.setFolderId(folderInfos.get(0).getFolderId());
        note.setTime(DateUtil.getFullDate());
        note.setNoteUUID(UUIDUtil.genUUID());
        return note;
    }

    //获取笔记的原始文本
    public String getSummary(String content) {
        content = content.replace("<br>", " ");
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(content);
        content = m_html.replaceAll(""); // 过滤html标签
        if (content.length() > 100) {
            content = content.substring(0, 100);
        }
        return content;
    }

    private void showPhotoDialog() {
        final String[] itemPhoto = getResources().getStringArray(R.array.photo);
        new AlertDialog.Builder(AddNoteActivity.this).setTitle("").setItems(itemPhoto, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (itemPhoto[which].equals("拍照")) {
                    Logger.i("拍照操作》》》");
                    //拍照
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra("autofocus", true);
                    intent.putExtra("fullScreen", false);
                    intent.putExtra("showActionIcons", false);
                    String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
                    imageFile = Const.APP_BASE_DIR + "image/" + name;
                    //传递拍照后保存图片的路径，否则，图片保存到Bundle里会被压缩。
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imageFile)));
                    startActivityForResult(intent, Const.REQUEST_TAKE_PHOTO_CODE);
                }
            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.i("request>>>" + requestCode + "/////>result>>>" + resultCode);
        switch (requestCode) {
            case Const.REQUEST_TAKE_PHOTO_CODE:
                Logger.i("拍照返回");
                Toast.makeText(this, "paizhao", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();
                break;
        }
    }
}
