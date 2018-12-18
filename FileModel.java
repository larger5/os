package com.lin.os;

import java.util.Date;

public class FileModel {

    private String name; // 文件名或目录名
    private Integer attr; // 1 文件夹、2 普通文件
    private Date date = new Date(); // 创建时间，默认当前时间
    private Integer size = 1; // 文件大小，默认1k
    private String fatherIndex = "/root";    //该文件或目录的上级目录
    private String permission = "drwxr-xr-x"; // 文件读写行权限
    private String content ="xxx"; // 文件内容

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttr() {
        return attr;
    }

    public void setAttr(Integer attr) {
        this.attr = attr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFatherIndex() {
        return fatherIndex;
    }

    public void setFatherIndex(String fatherIndex) {
        this.fatherIndex = fatherIndex;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
