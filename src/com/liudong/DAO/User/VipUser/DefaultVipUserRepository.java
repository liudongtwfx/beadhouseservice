//package com.liudong.DAO.User.VipUser;
//
//import com.liudong.exception.sqlexception.NotOnlyOneResultException;
//import com.liudong.model.User.VipUser;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
///**
// * Created by liudong on 2016/12/20.
// */
//
//@Repository
//public class DefaultVipUserRepository  {
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public List<VipUser> getAll() {
//        return this.entityManager.createQuery("select a from VipUser a order by a.userName", VipUser.class
//        ).getResultList();
//    }
//
//    @Override
//    public VipUser get(int id) {
//        return this.entityManager.createQuery("select a from VipUser a where a.id=:id", VipUser.class
//        ).setParameter("id", id).getSingleResult();
//    }
//
//    @Override
//    public VipUser getByName(String username) {
//        List<VipUser> userlist = this.entityManager.createQuery("select a from VipUser a where a.userName=:username", VipUser.class
//        ).setParameter("username", username).getResultList();
//        return handleResult(userlist);
//    }
//
//    @Override
//    public VipUser getByPhoneNumber(String phoneNumber) {
//        List<VipUser> userlist = this.entityManager.createQuery("select a from VipUser a where a.telephoneNumber=:phoneNumber", VipUser.class
//        ).setParameter("phoneNumber", phoneNumber).getResultList();
//        return handleResult(userlist);
//    }
//
//    @Override
//    public VipUser getByEmailAddress(String emailAddress) {
//        List<VipUser> userlist = this.entityManager.createQuery("select a from VipUser a where a.emailAddress=:emailAddress", VipUser.class
//        ).setParameter("emailAddress", emailAddress).getResultList();
//        return handleResult(userlist);
//    }
//
//    @Override
//    public void add(VipUser user) {
//        this.entityManager.persist(user);
//    }
//
//    @Override
//    public void update(VipUser user) {
//        this.entityManager.merge(user);
//    }
//
//    @Override
//    public void delete(VipUser user) {
//        this.entityManager.remove(user);
//    }
//
//    @Override
//    public void delete(long id) {
//        this.entityManager.createQuery("delete from VipUser a where a.id=:id", VipUser.class
//        ).setParameter("id", id).getSingleResult();
//    }
//
//    private VipUser handleResult(List<VipUser> userlist) {
//        try {
//            if (userlist.size() > 1) {
//                throw new NotOnlyOneResultException();
//            }
//            if (userlist.size() == 1) {
//                return userlist.get(0);
//            } else {
//                return null;
//            }
//        } catch (NotOnlyOneResultException e) {
//            System.out.println("not only one result");
//        }
//        return null;
//    }
//}
