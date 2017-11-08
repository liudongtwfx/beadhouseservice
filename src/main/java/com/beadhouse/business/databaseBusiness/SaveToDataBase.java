package main.java.com.beadhouse.business.databaseBusiness;

import main.java.com.beadhouse.DAO.Admin.BeadhouseCommentRepository;
import main.java.com.beadhouse.model.admin.BeadhouseComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.lang.reflect.Method;

@Service
public class SaveToDataBase {
    @Inject
    BeadhouseCommentRepository commentRepository;

    @Transactional
    public void saveToDataBase(String table, String value) {
        if (table.equals("beadhousecomment")) {
            this.commentRepository.save(getBeadhouesCommentByValue(value));
        }
    }

    public static BeadhouseComment getBeadhouesCommentByValue(String content) {
        BeadhouseComment comment = new BeadhouseComment();
        String[] fields = content.split("\\|\\|");

        for (String field : fields) {
            try {
                String[] f = field.split(":");
                char[] fieldName = f[0].toCharArray();
                fieldName[0] -= 32;
                Class<?> fieldtype = BeadhouseComment.class.getDeclaredField(f[0]).getType();
                Method method = BeadhouseComment.class.getMethod("set" + String.valueOf(fieldName), fieldtype);
                if (fieldtype.getName().equals("int")) {
                    method.invoke(comment, Integer.valueOf(f[1]));
                } else if (fieldtype.getName().equals("boolean")) {
                    method.invoke(comment, Boolean.valueOf(f[1]));
                } else if (fieldtype.getName().equals("float")) {
                    method.invoke(comment, Float.valueOf(f[1]));
                } else {
                    method.invoke(comment, f[1]);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + " " + field);
            }
        }
        return comment;
    }
}
