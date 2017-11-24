package main.java.com.beadhouse.model.dynamic;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "mybusiness")
@Data
public class Business {
    @Id
    private int id;
    private int level;
    @Column(name = "chinesename")
    private String chineseName;
    @Column(name = "englishname")
    private String englishName;
    @Column(name = "urlpath")
    private String URLPath;
    @Column(name = "viewpath")
    private String viewPath;
    @Column(name = "updatetime")
    private Date updateTime;
    @Column(name = "mybusinesscomment")
    private String mybusinesscomment;
    @Column(name = "parentbusinessid")
    private int parentBusinessId;
}
