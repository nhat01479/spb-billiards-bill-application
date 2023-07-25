package com.cg.api;

import com.cg.model.Type;
import com.cg.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/types")
public class TypeAPI {
    @Autowired
    private ITypeService typeService;
    @GetMapping
    public ResponseEntity<?> getAllTypeDesks() {

        List<Type> types = typeService.findAll();

        return new ResponseEntity<>(types, HttpStatus.OK);
    }
}
