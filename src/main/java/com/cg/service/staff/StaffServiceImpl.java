//package com.cg.service.staff;
//
//import com.cg.model.Desk;
//import com.cg.model.Role;
//import com.cg.model.Staff;
//import com.cg.model.Type;
//import com.cg.model.dto.desk.DeskCreReqDTO;
//import com.cg.model.dto.desk.DeskCreResDTO;
//import com.cg.model.dto.desk.DeskDTO;
//import com.cg.model.dto.desk.DeskUpResDTO;
//import com.cg.model.dto.staff.*;
//import com.cg.repository.StaffRepository;
//import com.cg.service.IGeneralService;
//import com.cg.service.role.IRoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class StaffServiceImpl implements IStaffService {
//    @Autowired
//    private StaffRepository staffRepository;
//    @Autowired
//    private IRoleService roleService;
//
//    @Override
//    public List<Staff> findAll() {
//        return staffRepository.findAll();
//    }
//
//    @Override
//    public Optional<Staff> findById(Long id) {
//        return staffRepository.findById(id);
//    }
//
//    @Override
//    public Staff save(Staff staff) {
//        return staffRepository.save(staff);
//    }
//
//    @Override
//    public void delete(Staff staff) {
//
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    public List<StaffDTO> findAllStaffDTOS() {
//        return staffRepository.findAllStaffDTOS();
//    }
//
//    @Override
//    public Boolean existsByEmail(String email) {
//        return staffRepository.existsByEmail(email);
//    }
//
//    @Override
//    public List<Staff> findAllByDeletedIs(Boolean boo) {
//        return staffRepository.findAllByDeletedIs(boo);
//    }
//
//    @Override
//    public StaffCreResDTO create(StaffCreReqDTO staffCreReqDTO) {
//        Staff staff = staffCreReqDTO.toStaff();
//        staff.setId(null);
//        staffRepository.save(staff);
//        StaffCreResDTO staffCreResDTO = staff.tostaffCreResDTO();
//        return staffCreResDTO;
//    }
//
//    @Override
//    public Boolean existsByEmailAndIdNot(String email, Long id) {
//        return staffRepository.existsByEmailAndIdNot(email, id);
//    }
//
//    @Override
//    public StaffUpResDTO update(long staffId, StaffUpReqDTO staffUpReqDTO) {
//        Staff staff = staffUpReqDTO.toStaff(staffId);
//        staff.setFullName(staffUpReqDTO.getFullName());
//        staff.setEmail(staffUpReqDTO.getEmail());
//        staff.setPhone(staffUpReqDTO.getPhone());
//        staff.setAddress(staffUpReqDTO.getAddress());
//
//        staffRepository.save(staff);
//
//        StaffUpResDTO staffUpResDTO = staff.tostaffUpResDTO();
//
//        return staffUpResDTO;
//    }
//
//}
