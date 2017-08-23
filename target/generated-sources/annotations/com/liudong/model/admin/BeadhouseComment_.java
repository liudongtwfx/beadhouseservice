package com.liudong.model.admin;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BeadhouseComment.class)
public abstract class BeadhouseComment_ {

	public static volatile SingularAttribute<BeadhouseComment, Integer> commentor;
	public static volatile SingularAttribute<BeadhouseComment, Float> score;
	public static volatile SingularAttribute<BeadhouseComment, Integer> beadhousereply;
	public static volatile SingularAttribute<BeadhouseComment, Date> addtime;
	public static volatile SingularAttribute<BeadhouseComment, Integer> beadhouseid;
	public static volatile SingularAttribute<BeadhouseComment, Boolean> anonymous;
	public static volatile SingularAttribute<BeadhouseComment, Integer> id;
	public static volatile SingularAttribute<BeadhouseComment, String> content;

}

