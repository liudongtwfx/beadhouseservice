package com.beadhouse.DAO.Admin;

import com.beadhouse.model.admin.BeadhouseComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 17-7-14.
 */
public interface BeadhouseCommentRepository extends JpaRepository<BeadhouseComment, Integer> {
    List<BeadhouseComment> findAllByBeadhouseidAndReplyed(int beadhouseid, boolean b);
}
