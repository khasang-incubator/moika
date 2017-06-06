package io.khasang.moika.controller;

import io.khasang.moika.model.MadvDataAcces;
import io.khasang.moika.service.MadvDataAccesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
@RequestMapping("/madv")
public class MadvController {
    @Autowired
    MadvDataAcces madvDataAcces;
    @Autowired
    MadvDataAccesService madvDataAccesService;

    @RequestMapping("/index1")
    public String hello() {
        return "index1";
    }

    @RequestMapping("/truncate")
    public String create(Model model) {
        model.addAttribute("truncate", madvDataAcces.truncate("cars"));
        return "truncate";
    }

    @RequestMapping("/test")
    public String test(Model model) {
        System.out.println(madvDataAccesService.test());
        System.out.println(madvDataAccesService.createDogs());
        String[] arString = (String[]) madvDataAccesService.selectAllDogs().toArray();
        System.out.println("Все собаки");
        for (String str : arString) {
            System.out.println(str);
        }
        return "truncate";
    }
}
