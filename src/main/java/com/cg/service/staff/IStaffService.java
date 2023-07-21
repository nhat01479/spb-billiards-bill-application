package com.cg.service.staff;

import com.cg.model.Staff;
import com.cg.model.dto.desk.DeskDTO;
import com.cg.model.dto.staff.StaffDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IStaffService extends IGeneralService<Staff,Long> {
    List<StaffDTO> findAllStaffDTOS();

}
