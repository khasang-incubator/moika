package io.khasang.moika.controller;

import io.khasang.moika.dao.CatsDAO;
import io.khasang.moika.entity.Cats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cat")
public class CatsController {
    @Autowired
    private CatsDAO catsDAO;

    @RequestMapping("/list")
    public String getListClients(Model model){
        model.addAttribute("cats",catsDAO.getAllCats());
        return "cat";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addCat(@RequestBody Cats cat){
        catsDAO.addCat(cat);
        return cat;
    }
}
