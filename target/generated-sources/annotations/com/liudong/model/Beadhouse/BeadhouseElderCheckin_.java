package com.liudong.model.Beadhouse;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BeadhouseElderCheckin.class)
public abstract class BeadhouseElderCheckin_ {

	public static volatile SingularAttribute<BeadhouseElderCheckin, String> elderIdNumber;
	public static volatile SingularAttribute<BeadhouseElderCheckin, Date> leaveTime;
	public static volatile SingularAttribute<BeadhouseElderCheckin, Date> checkinTime;
	public static volatile SingularAttribute<BeadhouseElderCheckin, String> extraContent;
	public static volatile SingularAttribute<BeadhouseElderCheckin, String> leaveReason;
	public static volatile SingularAttribute<BeadhouseElderCheckin, Integer> beadhouseId;
	public static volatile SingularAttribute<BeadhouseElderCheckin, String> principleMan;
	public static volatile SingularAttribute<BeadhouseElderCheckin, Integer> id;

}

