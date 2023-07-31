package com.cg.service.desk;

import com.cg.model.Desk;
import com.cg.model.Product;
import com.cg.model.Type;
import com.cg.model.dto.desk.*;
import com.cg.repository.DeskRepository;
import com.cg.service.type.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeskServiceImpl implements IDeskService {
    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private TypeServiceImpl typeService;

    @Override
    public Boolean existsByName(String name) {
        return deskRepository.existsByName(name);
    }

    @Override
    public List<Desk> findAll() {
        return deskRepository.findAll();
    }

    @Override
    public Optional<Desk> findById(Long id) {
        return deskRepository.findById(id);
    }

    @Override
    public Desk save(Desk desk) {
        return deskRepository.save(desk);
    }

    @Override
    public void delete(Desk desk) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void softDelete(Desk desk) {
        desk.setDeleted(true);
        deskRepository.save(desk);
    }

    public List<DeskDTO> findAllDeskDTOS() {
        return deskRepository.findAllDeskDTOS();
    }

    @Override
    public List<DeskDTO> findAllByDeletedFalseAndTypeId(Long typeId) {
        return deskRepository.findAllByDeletedFalseAndTypeId(typeId);
    }

    public List<Desk> findAllByDeletedIs(Boolean boo) {
        return deskRepository.findAllByDeletedIs(boo);
    }
    @Override
    public DeskCreResDTO create(DeskCreReqDTO deskCreReqDTO) {
        Long typeId = (Long) deskCreReqDTO.getTypeId();
        Optional<Type> type = typeService.findById(typeId);
        Desk desk = deskCreReqDTO.toDesk(type.get());
        desk.setStatus(false);
        desk.setId(null);
        deskRepository.save(desk);
        DeskCreResDTO deskCreResDTO = desk.todeskCreResDTO();
        return deskCreResDTO;
    }

    @Override
    public Boolean existsByNameAndIdNot(String name, Long id) {
        return deskRepository.existsByNameAndIdNot(name, id);
    }

    @Override
    public DeskUpResDTO update(Desk desk, DeskUpReqDTO deskUpReqDTO) {

        desk.setName(deskUpReqDTO.getName());
        desk.setPriceTime(BigDecimal.valueOf(Long.parseLong(deskUpReqDTO.getPriceTime())));
        desk.setType(typeService.findById(deskUpReqDTO.getTypeId()).get());

        deskRepository.save(desk);

        DeskUpResDTO deskUpResDTO = desk.todeskUpResDTO();

        return deskUpResDTO;
    }
    @Override
    public List<DeskDTO> findAllByDeletedFalseAndNameLike(String name){
        return deskRepository.findAllByDeletedFalseAndNameLike(name);
    }
}
