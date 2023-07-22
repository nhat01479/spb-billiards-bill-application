package com.cg.service.desk;

import com.cg.model.Desk;
import com.cg.model.dto.desk.*;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IDeskService extends IGeneralService<Desk, Long> {
    public void softDelete(Desk desk);

    List<Desk> findAllByDeletedIs(Boolean boo);
    List<DeskDTO> findAllByDeletedFalseAndTypeId(Long typeId);

    Boolean existsByName(String name);

    List<DeskDTO> findAllDeskDTOS();

    DeskCreResDTO create(DeskCreReqDTO deskCreReqDTO);

    Boolean existsByNameAndIdNot(String name, Long id);

    DeskUpResDTO update(long deskId, DeskUpReqDTO deskUpReqDTO);




}
