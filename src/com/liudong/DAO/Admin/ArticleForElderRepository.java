package com.liudong.DAO.Admin;

import com.liudong.model.admin.ArticleForElder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liudong on 17-7-8.
 */
public interface ArticleForElderRepository extends JpaRepository<ArticleForElder, Integer> {
    Page<ArticleForElder> findAll(Pageable pageable);
}
