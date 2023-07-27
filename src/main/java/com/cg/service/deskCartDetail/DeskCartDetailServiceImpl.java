package com.cg.service.deskCartDetail;

import com.cg.model.Cart;
import com.cg.model.DeskCartDetail;
import com.cg.model.dto.cart.desk.DeskCartDetailResDTO;
import com.cg.repository.DeskCartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DeskCartDetailServiceImpl implements IDeskCartDetailService{

    @Autowired
    private DeskCartDetailRepository deskCartDetailRepository;
    @Override
    public List<DeskCartDetail> findAll() {
        return null;
    }
    @Override
    public List<DeskCartDetail> findAllByCart(Cart cart) {
        return deskCartDetailRepository.findAllByCart(cart);
    }

    @Override
    public List<DeskCartDetailResDTO> getAllDeskCartDetailResDTO(Cart cart) {
        return deskCartDetailRepository.getAllCartDetailItemResDTO(cart);
    }

    @Override
    public Optional<DeskCartDetail> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DeskCartDetail save(DeskCartDetail deskCartDetail) {
        return deskCartDetailRepository.save(deskCartDetail);
    }

    @Override
    public void delete(DeskCartDetail deskCartDetail) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
