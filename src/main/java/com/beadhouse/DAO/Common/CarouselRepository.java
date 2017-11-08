package main.java.com.beadhouse.DAO.Common;

import main.java.com.beadhouse.model.common.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 17-6-19.
 */
public interface CarouselRepository extends JpaRepository<Carousel, Short> {
    List<Carousel> findByLocation(String location);
}
