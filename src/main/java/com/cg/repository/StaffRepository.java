//package com.cg.repository;
//
//import com.cg.model.Staff;
//import com.cg.model.dto.desk.DeskDTO;
//import com.cg.model.dto.staff.StaffDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface StaffRepository extends JpaRepository<Staff, Long> {
//    @Query("SELECT new com.cg.model.dto.staff.StaffDTO(" +
//            "st.id, " +
//            "st.fullName, " +
//            "st.email, " +
//            "st.phone, " +
//            "st.address " +
//            ") " +
//            "FROM Staff st " +
//            "WHERE st.deleted = false")
//    List<StaffDTO> findAllStaffDTOS();
//
//
//    Boolean existsByEmail(String email);
//
//    Boolean existsByEmailAndIdNot(String email, Long id);
//
//    List<Staff> findAllByDeletedIs(Boolean boo);
//
//}
