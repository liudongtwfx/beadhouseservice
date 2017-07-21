package com.liudong.model.admin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liudong on 2017/5/25.
 */

@Entity
@Table(name = "articleforelder")
public class ArticleForElder {
    private int id;
    private String title;
    private String content;
    private String sourceurl;
    private int strategytype;
    private Date addtime;
    private Date updatetime;
    private int operator;

    public String getArticletag() {
        return articletag;
    }

    public void setArticletag(String articletag) {
        this.articletag = articletag;
    }

    private String articletag;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public int getStrategytype() {
        return strategytype;
    }

    public void setStrategytype(int strategytype) {
        this.strategytype = strategytype;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }
}
