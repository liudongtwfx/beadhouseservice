package com.liudong.model.User;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VipUser.class)
public abstract class VipUser_ {

	public static volatile SingularAttribute<VipUser, String> emailAddress;
	public static volatile SingularAttribute<VipUser, String> password;
	public static volatile SingularAttribute<VipUser, String> telephoneNumber;
	public static volatile SingularAttribute<VipUser, Date> addTime;
	public static volatile SingularAttribute<VipUser, Integer> id;
	public static volatile SingularAttribute<VipUser, String> userName;

}

