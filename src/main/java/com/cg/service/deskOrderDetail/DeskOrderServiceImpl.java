package com.cg.service.deskOrderDetail;

import com.cg.model.DeskOrderDetail;
import com.cg.repository.DeskOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DeskOrderServiceImpl implements IDeskOrderDetailService{
    @Autowired
    private DeskOrderDetailRepository deskOrderDetailRepository;
    @Override
    public List<DeskOrderDetail> findAll() {
        return null;
    }

    @Override
    public Optional<DeskOrderDetail> findById(Long id) {
        return deskOrderDetailRepository.findById(id);
    }

    @Override
    public DeskOrderDetail save(DeskOrderDetail deskOrderDetail) {
        return deskOrderDetailRepository.save(deskOrderDetail);
    }

    @Override
    public void delete(DeskOrderDetail deskOrderDetail) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
