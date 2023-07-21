package com.cg.api;

import com.cg.model.Desk;
import com.cg.model.Staff;
import com.cg.model.dto.desk.DeskDTO;
import com.cg.model.dto.staff.StaffDTO;
import com.cg.service.staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/staffs")
public class StaffAPI {
    @Autowired
    private IStaffService staffService;
    @GetMapping
    public ResponseEntity<?> getAllStaffs() {

        List<StaffDTO> staffDTOS = staffService.findAllStaffDTOS();

        return new ResponseEntity<>(staffDTOS, HttpStatus.OK);
    }

    @GetMapping("/{deskId}")
    public ResponseEntity<?> getById(@PathVariable Long staffId) {

        Optional<Staff> staffOptional = staffService.findById(staffId);

        if (staffOptional.isEmpty()) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Nhân viên không tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        Staff staff = staffOptional.get();
        StaffDTO staffDTO = staff.tostaffDTO();

        return new ResponseEntity<>(staffDTO, HttpStatus.OK);
    }
}
