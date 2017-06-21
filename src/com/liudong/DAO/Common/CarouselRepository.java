package com.liudong.DAO.Common;

import com.liudong.model.Common.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudong on 17-6-19.
 */
public interface CarouselRepository extends JpaRepository<Carousel, Short> {
    List<Carousel> findByLocation(String location);
}
