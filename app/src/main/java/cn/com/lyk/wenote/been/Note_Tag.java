package cn.com.lyk.wenote.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lyk on 2017/12/5.
 */

@Entity
public class Note_Tag {
    @Id
    private Long id;
    private Long noteId;
    private Long tagId;
    @Generated(hash = 1692154726)
    public Note_Tag(Long id, Long noteId, Long tagId) {
        this.id = id;
        this.noteId = noteId;
        this.tagId = tagId;
    }
    @Generated(hash = 1052291531)
    public Note_Tag() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getNoteId() {
        return this.noteId;
    }
    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
    public Long getTagId() {
        return this.tagId;
    }
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
