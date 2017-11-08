package main.java.com.beadhouse.DAO.Admin;

import main.java.com.beadhouse.model.admin.LeisureGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeisureGroupRepository extends JpaRepository<LeisureGroup, Integer> {
}
