package main.java.com.beadhouse.model.admin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "authapplyinfo")
@Data
public class AuthApplyInfo {
    @Id
    private int id;
    @Column(name = "leaderpass")
    private String leaderPass;
    @Column(name = "departmentpass")
    private String departmentPass;
    @Column(name = "applyreason")
    private String applyReason;
    private Date addtime;
    private Date updatetime;
    @Column(name = "applydepartment")
    private String applyDepartment;
    @Column(name = "applyadminname")
    private String applyAdminName;
}
