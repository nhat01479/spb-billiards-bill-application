package com.cg.service.type;

import com.cg.model.Type;
import com.cg.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TypeServiceImpl implements ITypeService{
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> findAll() {
        return null;
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Type save(Type type) {
        return null;
    }

    @Override
    public void delete(Type type) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
