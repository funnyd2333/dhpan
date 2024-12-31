package com.dh.dhpan.model.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Table(name ="files")
@Entity
public class Files implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    //@ManyToOne // 表示多对一的关系
    @JoinColumn(name = "user_id", nullable = true) // 指定外键列名，并且)不(允许为空
    private String userid;
    @Column(nullable = false)
    private String fileName;  //文件名称

    @Column(nullable = false)
    private String fileType;  //文件类型

    @Column(nullable = false)
    private long fileSize;  //文件大小

    @Column(nullable = false)
    private Date fileDate;  //上传日期

    @Column(nullable = false)
    private String url;   //下载链接

    private Boolean isDelete;//是否删除

    private String uuid;   //uuid

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    public String getFilename() {
        return fileName;
    }
}
