package main.java.com.beadhouse.test;

import com.google.api.translate.Language;
import com.google.api.translate.Translate;
import org.junit.Test;

public class GoogleTranslate {

    @Test
    public void testTranslate() {
        Translate.setHttpReferrer("https://translate.google.cn/");

        Language langFrom = Language.CHINESE;
        Language langTo = Language.ENGLISH;
        String from = "我是中国人";
        try {
            Translate.validateReferrer();
            System.out.println(Translate.execute(from, langFrom, langTo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
