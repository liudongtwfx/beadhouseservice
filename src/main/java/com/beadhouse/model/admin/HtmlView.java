package main.java.com.beadhouse.model.admin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "views")
@Data
public class HtmlView {
    @Id
    private int id;
    @Column(name = "chinesetitle")

    private String chineseTitle;

    private String url;

    @Column(name = "englishname")
    private String englishName;

    private String filepath;

    @Column(name = "viewcomment")
    private String viewComment;

    @Column(name = "updatetime")
    private Date updateTime;
}
