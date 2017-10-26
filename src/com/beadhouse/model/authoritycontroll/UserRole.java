package com.beadhouse.model.authoritycontroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "userrole")
@Data
public class UserRole {
    @Id
    private long id;
    private int roleId;
    private int userId;
}
