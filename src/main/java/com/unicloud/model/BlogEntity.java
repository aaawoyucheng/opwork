package com.unicloud.model;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by dzkan on 2016/3/8.
 */
@Entity
@Where(clause = "deleted=0")
@Table(name = "record", catalog = "")
public class BlogEntity {
    @Override
    public String toString() {
        return "BlogEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", costtime=" + costtime +
                ", type='" + type + '\'' +
                ", deleted=" + deleted +
                ", user=" + user +
                ", titleId='" + titleId + '\'' +
                '}';
    }

    private int id;
    private String title;
    private String content;
    private String pubDate;
    private float costtime;
    private String type;
    @Basic
    @Column(name = "deleted", nullable = false, length = 100)
    private int deleted;

    private UserEntity user;
    private String titleId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "title_id", nullable = false, length = 100)
    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "pub_date", nullable = false)
    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Basic
    @Column(name = "costtime", nullable = true)
    public float getCosttime() {
        return costtime;
    }

    public void setCosttime(float costtime) {
        this.costtime = costtime;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntity that = (BlogEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_number", referencedColumnName = "number", nullable = false)
    public UserEntity getuser() {
        return user;
    }

    public void setuser(UserEntity user) {
        this.user = user;
    }
}
