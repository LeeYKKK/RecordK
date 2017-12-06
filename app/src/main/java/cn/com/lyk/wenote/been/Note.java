package cn.com.lyk.wenote.been;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import cn.com.lyk.greendao.DaoSession;
import cn.com.lyk.greendao.NoteDao;
import cn.com.lyk.greendao.TagDao;

/**
 * Created by lyk on 2017/12/4.
 */
@Entity
public class Note {
    @Id(autoincrement = true)
    private Long noteId;
    private String title;
    private String content;
    private String summary;
    @ToMany
    @JoinEntity(entity = Note_Tag.class,
            sourceProperty = "noteId",
            targetProperty = "tagId")
    private List<Tag> tags;
    private Long folderId;//与folderInfo表关联起来
    private String time;//创建时间
    private String updateTime;//修改时间
    private String noteUUID;//新建笔记时生成
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 363862535)
    private transient NoteDao myDao;
    @Generated(hash = 1568756530)
    public Note(Long noteId, String title, String content, String summary,
            Long folderId, String time, String updateTime, String noteUUID) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.folderId = folderId;
        this.time = time;
        this.updateTime = updateTime;
        this.noteUUID = noteUUID;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public Long getNoteId() {
        return this.noteId;
    }
    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public Long getFolderId() {
        return this.folderId;
    }
    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getNoteUUID() {
        return this.noteUUID;
    }
    public void setNoteUUID(String noteUUID) {
        this.noteUUID = noteUUID;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1088510093)
    public List<Tag> getTags() {
        if (tags == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TagDao targetDao = daoSession.getTagDao();
            List<Tag> tagsNew = targetDao._queryNote_Tags(noteId);
            synchronized (this) {
                if (tags == null) {
                    tags = tagsNew;
                }
            }
        }
        return tags;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 404234)
    public synchronized void resetTags() {
        tags = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 799086675)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getNoteDao() : null;
    }



}
