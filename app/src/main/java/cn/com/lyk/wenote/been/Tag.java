package cn.com.lyk.wenote.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lyk on 2017/12/4.
 */
@Entity
public class Tag {
    @Id
    private int tagId;
    private String name;
    @Generated(hash = 783172439)
    public Tag(int tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }
    @Generated(hash = 1605720318)
    public Tag() {
    }
    public int getTagId() {
        return this.tagId;
    }
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
