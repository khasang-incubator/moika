package io.khasang.moika.controller;

import io.khasang.moika.annotation.AddMenuPath;
import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.entity.Company;
import io.khasang.moika.entity.User;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.CompanyService;
import io.khasang.moika.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private CreateTable createTable;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDao companyDao;
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
        model.addAttribute("create", createTable.createTableStatus());
        return "create";
    }

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }

    @RequestMapping(value = "/company/add/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
        return company;
    }

    @RequestMapping(value = "/company/getAll/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Company> getCompanyList() {
//        model.addAttribute("companies", companyService.getCompanyGazpromList());
//        return "companies";?
        return companyService.getCompanyList();
    }

    @RequestMapping(value = "/company/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
        return company;
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCompany(@PathVariable(value = "id") String inputId) {
        companyService.deleteCompany(Integer.parseInt(inputId));
        return "redirect:/company";
    }

    @RequestMapping("/restHql")
    public String testHql() {
        List<Company> companyList = companyDao.getCompanyHqlList();
        return "redirect:yandex.ru";
    }

    @RequestMapping(value = "/company/all/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company company(@PathVariable(value = "id") String id) {
        return companyService.getCompanyById(Long.parseLong(id));
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
