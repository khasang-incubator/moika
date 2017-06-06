package io.khasang.moika.dao;

import io.khasang.moika.entity.Role;
import org.springframework.security.core.GrantedAuthority;

/**
 * Интерфейс DAO ролей пользователей
 *
 * @since 2017-03-01
 * @author Rostislav Dublin
 */
public interface RoleDAO  extends IMoikaDaoCrud<Role>{

    /**
    * Инициализация базовых ролей в таблице БД
    * */
    void init();
    /**
     * Найти роль по названию
     * @param name название роли
     * @return Role
     */
    Role findByName(String name);


    /**
     * Полномочие, определяемое ролью
     * @param role роль
     * @return полномочие
     *
     */
    public GrantedAuthority getAuthority(Role role);

}
