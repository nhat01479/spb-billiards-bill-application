package com.cg.service.desk;

import com.cg.model.Desk;
import com.cg.model.Product;
import com.cg.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DeskServiceImpl implements IDeskService{
    @Autowired
    private DeskRepository deskRepository;

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
}
