package com.cg.service.staff;

import com.cg.model.Staff;
import com.cg.model.dto.desk.DeskDTO;
import com.cg.model.dto.staff.StaffDTO;
import com.cg.repository.StaffRepository;
import com.cg.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void delete(Staff staff) {

    }

    @Override
    public void deleteById(Long id) {

    }

    public List<StaffDTO> findAllStaffDTOS() {
        return staffRepository.findAllStaffDTOS();
    }
}
