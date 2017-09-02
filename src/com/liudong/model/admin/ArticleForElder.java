package com.liudong.model.admin;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liudong on 2017/5/25.
 */

@Entity
@Table(name = "articleforelder")
@Data
public class ArticleForElder {
    @Id
    private int id;
    private String title;
    private String content;
    private String sourceurl;
    private int strategytype;
    private Date addtime;
    private Date updatetime;
    private int operator;
    private String articletag;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append("||")
                .append("title").append(":").append(title).append("||")
                .append("content").append(":").append(content).append("||")
                .append("sourceurl").append(":").append(sourceurl).append("||")
                .append("articletag").append(":").append(articletag);
        return sb.toString();
    }
}
