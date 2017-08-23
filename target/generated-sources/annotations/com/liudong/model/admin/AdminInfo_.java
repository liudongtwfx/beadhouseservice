package com.liudong.model.admin;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AdminInfo.class)
public abstract class AdminInfo_ {

	public static volatile SingularAttribute<AdminInfo, String> emailAddress;
	public static volatile SingularAttribute<AdminInfo, String> password;
	public static volatile SingularAttribute<AdminInfo, String> telephoneNumber;
	public static volatile SingularAttribute<AdminInfo, Date> addTime;
	public static volatile SingularAttribute<AdminInfo, String> chineseName;
	public static volatile SingularAttribute<AdminInfo, String> employeeId;
	public static volatile SingularAttribute<AdminInfo, Integer> id;
	public static volatile SingularAttribute<AdminInfo, String> username;

}

