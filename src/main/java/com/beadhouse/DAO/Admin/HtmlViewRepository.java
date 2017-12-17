package main.java.com.beadhouse.DAO.Admin;

import main.java.com.beadhouse.model.admin.HtmlView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HtmlViewRepository extends JpaRepository<HtmlView, Integer> {
    HtmlView findByUrl(String url);
}
