package io.khasang.moika.controller;

import io.khasang.moika.annotation.AddMenuPath;
import io.khasang.moika.entity.User;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private CreateTable createTable;
    @Autowired
    private UserService userService;

    private User getCurrentUser() {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByLogin(currentLogin);

    }

    @GetMapping("/")
    public String ng2(){
        return "index.html";
    }


    @GetMapping("/off")
    @AddMenuPath(name = "hello")
    public String hello(Model model) {
        User user = getCurrentUser();
        String headLine = "---------Security-----------";
        String footLine = "----------------------------";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.debug(String.format("%n%s%nIs authenticated: %s%nWho: %s%nRole: %s%n%s",
                headLine,
                auth.isAuthenticated(),
                auth.getName(),
                auth.getAuthorities().stream().map(a -> a.toString()).collect(Collectors.joining(", ")),
                footLine
        ));
        if (user == null) {
            model.addAttribute("isAuth", false);
        } else {
            model.addAttribute("isAuth", true);
            model.addAttribute("userFirstName", user.getPerson().getFullName());
        }
        return "index";
    }

    @RequestMapping("/db/create")
    public String create(Model model) {
        //model.addAttribute("create", createTable.createTableStatus());
        return "under construction";
    }

    /**
     * DRS Контроллер страницы 403-ошибки. См. настройку SpringSecurity.
     * Пользователь будет перемещён на эту страницу в случае, если он аутентифицирован,
     * но не авторизован для посещения некоторой страницы, на которую он пытается попасть.
     * @param model модель
     * @return адрес представления
     */
    @GetMapping(value = "/accessDenied")
    public String accessDenied(Model model) {
        model.addAttribute("msg", "Отсутствуют полномочия для доступа к данному ресурсу.");
        return "accessDenied";
    }

    /**
     * Возвращает текущий CSRF-токен для данной HTTP-сессии.
     * @param token
     * @return JSON-строка с токеном, а также названием для параметра и заголовка.
     */
    @GetMapping(value="/csrf_token")
    @ResponseBody
    public Map<String, String> getCsrfToken(CsrfToken token){
        Map<String, String> map = new HashMap<>();
        if(token != null){
            map.put("headerName", token.getHeaderName());
            map.put("parameterName", token.getParameterName());
            map.put("token", token.getToken());
        }
        return map;
    }

    @GetMapping(value = "/angular")
    public ModelAndView angular(){
        return new ModelAndView("angular/index.html");
    }

}
