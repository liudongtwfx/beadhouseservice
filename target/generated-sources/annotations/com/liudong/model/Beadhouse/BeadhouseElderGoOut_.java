package com.liudong.model.Beadhouse;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BeadhouseElderGoOut.class)
public abstract class BeadhouseElderGoOut_ {

	public static volatile SingularAttribute<BeadhouseElderGoOut, String> elderIdNumber;
	public static volatile SingularAttribute<BeadhouseElderGoOut, String> reason;
	public static volatile SingularAttribute<BeadhouseElderGoOut, Integer> beadhouseId;
	public static volatile SingularAttribute<BeadhouseElderGoOut, Date> updateTime;
	public static volatile SingularAttribute<BeadhouseElderGoOut, Long> id;
	public static volatile SingularAttribute<BeadhouseElderGoOut, Date> goOutTime;
	public static volatile SingularAttribute<BeadhouseElderGoOut, Date> backTime;

}

