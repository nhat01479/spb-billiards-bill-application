package com.cg.repository;

import com.cg.model.Desk;
import com.cg.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskRepository extends JpaRepository<Desk, Long> {
}
