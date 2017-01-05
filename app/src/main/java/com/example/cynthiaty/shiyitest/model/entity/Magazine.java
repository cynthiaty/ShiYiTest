package com.example.cynthiaty.shiyitest.model.entity;

/**
 * 作者：尚萍萍
 * 功能：封装了实体类magazine的属性和方法
 * 时间：2017-1-5
 */
public class Magazine {
    private String title;           //标题
    private String text;            //文本内容
    private String image;           //图片内容

    public Magazine(String title, String text, String image) {
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
