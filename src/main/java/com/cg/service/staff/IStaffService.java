//package com.cg.service.staff;
//
//import com.cg.model.Desk;
//import com.cg.model.Staff;
//import com.cg.model.dto.desk.*;
//import com.cg.model.dto.staff.*;
//import com.cg.service.IGeneralService;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface IStaffService extends IGeneralService<Staff,Long> {
//    List<StaffDTO> findAllStaffDTOS();
//    Boolean existsByEmail(String email);
//
//    List<Staff> findAllByDeletedIs(Boolean boo);
//
//
//    StaffCreResDTO create(StaffCreReqDTO staffCreReqDTO);
//
//    Boolean existsByEmailAndIdNot(String email, Long id);
//
//    StaffUpResDTO update(long staffId, StaffUpReqDTO staffUpReqDTO);
//
//}
