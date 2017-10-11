package com.beadhouse.DAO.Common;

import com.beadhouse.model.Common.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 17-6-19.
 */
public interface CarouselRepository extends JpaRepository<Carousel, Short> {
    List<Carousel> findByLocation(String location);
}
