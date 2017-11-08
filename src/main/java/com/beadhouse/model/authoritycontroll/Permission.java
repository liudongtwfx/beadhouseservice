package main.java.com.beadhouse.model.authoritycontroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
@Data
public class Permission {
    @Id
    private int id;
    private int roleId;
    private String permissionName;
    private String permissionDetails;
}
