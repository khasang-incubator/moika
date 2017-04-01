package io.khasang.moika.config;

import io.khasang.moika.dao.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/***
 * Инициализация базовых ролей в БД чере ApplicationListener
 */
@Component
public class InitializeBaseRoleApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private RoleDAO roleDao;

    @Autowired
    public void setRoleDao(RoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleDao.init();
    }
}
