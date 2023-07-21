package com.cg.repository;

import com.cg.model.Desk;
import com.cg.model.Role;
import com.cg.model.dto.desk.DeskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {
    Boolean existsByName(String name);

    List<Desk> findAllByDeletedIs(Boolean boo);


    Boolean existsByNameAndIdNot(String name, Long id);

    @Query("SELECT NEW com.cg.model.dto.desk.DeskDTO (" +
            "des.id, " +
            "des.name, " +
            "des.priceTime," +
            "des.type," +
            "des.status " +
            ") " +
            "FROM Desk AS des " +
            "where des.deleted=false "
    )
    List<DeskDTO> findAllDeskDTOS();
}
