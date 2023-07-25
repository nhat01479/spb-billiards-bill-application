package com.cg.controller;

import com.cg.model.Type;
import com.cg.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/desks")
public class DeskController {
    @GetMapping
    public String showDeskPage(Model model) {
        return "desk/listDesk";
    }
}
