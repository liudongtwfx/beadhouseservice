package main.java.com.beadhouse.test;

/**
 * Created by beadhouse on 17-6-25.
 */

public class PersonServiceBean{

    public void save(String name) {

        System.out.println("我是save方法");
        //  throw new RuntimeException();
    }

    public void update(String name, Integer id) {

        System.out.println("我是update()方法");
    }

    public String getPersonName(Integer id) {

        System.out.println("我是getPersonName()方法");
        return "xxx";
    }

}
