package main.java.com.beadhouse.DAO.dynamic;

import main.java.com.beadhouse.model.dynamic.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
    public Business findByEnglishName(String englishName);
}
