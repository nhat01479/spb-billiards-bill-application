package com.cg.api;

import com.cg.model.Desk;
import com.cg.model.Type;
import com.cg.model.dto.desk.*;
import com.cg.service.desk.IDeskService;
import com.cg.service.type.ITypeService;
import com.cg.ultis.AppUtils;
import com.cg.ultis.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/desks")
public class DeskAPI {
    @Autowired
    private IDeskService deskService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ValidateUtils validateUtils;

    @GetMapping
    public ResponseEntity<?> getAllDesks() {

        List<DeskDTO> deskDTOS = deskService.findAllDeskDTOS();

        return new ResponseEntity<>(deskDTOS, HttpStatus.OK);
    }
    @GetMapping("/types/{typeId}")
    public ResponseEntity<?> getAllDesksByTypeId(@PathVariable String typeId) {

        List<DeskDTO> deskDTOS = deskService.findAllByDeletedFalseAndTypeId(Long.valueOf(typeId));

        return new ResponseEntity<>(deskDTOS, HttpStatus.OK);
    }

    @GetMapping("/{deskId}")
    public ResponseEntity<?> getById(@PathVariable Long deskId) {

        Optional<Desk> deskOptional = deskService.findById(deskId);

        if (deskOptional.isEmpty()) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Bàn không tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        Desk desk = deskOptional.get();
        DeskDTO deskDTO = desk.toDeskDTO();

        return new ResponseEntity<>(deskDTO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody DeskCreReqDTO deskCreReqDTO, BindingResult bindingResult) {
        new DeskCreReqDTO().validate(deskCreReqDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Map<String, String> data = new HashMap<>();

        String name = deskCreReqDTO.getName().trim();

        if (deskService.existsByName(name)) {
            data.put("message", "Tên bàn đã tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        DeskCreResDTO deskCreResDTO = deskService.create(deskCreReqDTO);

        return new ResponseEntity<>(deskCreResDTO, HttpStatus.CREATED);
    }


    @PatchMapping("/{deskId}")
    public ResponseEntity<?> edit(@PathVariable("deskId") Long deskId,
                                  @RequestBody DeskUpReqDTO deskUpReqDTO,
                                  BindingResult bindingResult) {

        new DeskUpReqDTO().validate(deskUpReqDTO, bindingResult);

        Optional<Desk> deskOptional = deskService.findById(deskId);

        if (deskOptional.isEmpty()) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã bàn hàng không tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Map<String, String> data = new HashMap<>();

        String name = deskUpReqDTO.getName().trim();

        if (deskService.existsByNameAndIdNot(name, deskId)) {
            data.put("message", "Tên bàn đã tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        DeskUpResDTO deskUpResDTO = deskService.update(deskId, deskUpReqDTO);

        return new ResponseEntity<>(deskUpResDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{deskId}")
    public ResponseEntity<?> delete(@PathVariable("deskId") Long deskId) {
        try {
            Optional<Desk> deskOptional = deskService.findById(deskId);

            if (deskOptional.isEmpty()) {
                Map<String, String> data = new HashMap<>();
                data.put("message", "Tên bàn không tồn tại");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }
            Desk desk = deskOptional.get();
            desk.setDeleted(true);
            deskService.save(desk);
            List<Desk> desks = deskService.findAllByDeletedIs(false);

            return new ResponseEntity<>(desks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> data = new HashMap<>();
            data.put("message", "Lỗi xóa bàn");
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
