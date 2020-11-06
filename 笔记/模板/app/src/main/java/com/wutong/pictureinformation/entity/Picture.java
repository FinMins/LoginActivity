package com.wutong.pictureinformation.entity;

//{"data":[
//    {"_id":"5e959250808d6d2fe6b56eda",
//        "author":"\u9e22\u5a9b",
//        "category":"Girl",
//        "createdAt":"2020-05-25 08:00:00",
//        "desc":"\u4e0e\u5176\u5b89\u6170\u81ea\u5df1\u5e73\u51e1\u53ef\u8d35\uff0c\n\u4e0d\u5982\u62fc\u5c3d\u5168\u529b\u6d3b\u5f97\u6f02\u4eae\u3002 \u200b \u200b\u200b\u200b\u200b",
//        "images":["http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2"],
//        "likeCounts":0,"publishedAt":"2020-05-25 08:00:00",
//        "stars":1,
//        "title":"\u7b2c96\u671f",
//        "type":"Girl",
//        "url":"http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2",
//        "views":2235
//     }],"page":1,"page_count":10,"status":100,"total_counts":96
//}
import java.util.List;

public class Picture {
    String id;
    String url;
    String type;
    String category;
    int likeCounts;

    public Picture() {
    }

    public Picture(String id, String url, String type, String category, int likeCounts) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.category = category;
        this.likeCounts = likeCounts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }
}
