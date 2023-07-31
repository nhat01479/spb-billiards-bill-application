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
            "des.unit," +
            "des.type," +
            "des.status " +
            ") " +
            "FROM Desk AS des " +
            "where des.deleted=false "
    )
    List<DeskDTO> findAllDeskDTOS();
    List<DeskDTO> findAllByDeletedFalseAndTypeId(Long typeId);

    @Query("select new com.cg.model.dto.desk.DeskDTO (" +
            "d.id, " +
            "d.name, " +
            "d.priceTime, " +
            "d.unit, " +
            "d.type, " +
            "d.status)" +
            " from Desk as d where (d.name like %:name%) and d.deleted=false")
    List<DeskDTO> findAllByDeletedFalseAndNameLike(String name);
}
