package com.wutong.pictureinformation.entity;

	/*"data": [{
            "_id": "5ee833150bd5529b54e7130e",
            "author": "Zaylour",
            "category": "GanHuo",
            "createdAt": "2020-06-16 10:48:53",
            "desc": "Android\u4e2dView\u548c\u52a8\u753b\u7684\u9762\u8bd5\u9898\u6574\u7406\uff0c\u4e0d\u662f\u5ba3\u4f20!",
            "images": ["https://gank.io/images/987fd2d5636c4dda8936a3c89dc6a4e2"],
            "likeCounts": 0,
            "publishedAt": "2020-06-16 10:48:53",
            "stars": 1,
            "title": "View\u548c\u52a8\u753b\uff0c\u8fd9\u4e9b\u77e5\u8bc6\u70b9\u4f60\u8fd8\u8bb0\u5f97\u5417?\u300aAndroid\u9898\u96c6\u300b",
            "type": "Android",
            "url": "https://juejin.im/post/5ee7751c518825434566d599",
            "views": 10
       }],page":1,"page_count":245,"status":100,"total_counts":2445}
            */

public class Article {
    private String id;
    private String author;
    private String desc;
    private String publishedAt;
    private String title;
    private String content;

    public Article(){
    }

    public Article(String id, String author, String desc, String publishedAt, String title, String content) {
        this.id = id;
        this.author = author;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
