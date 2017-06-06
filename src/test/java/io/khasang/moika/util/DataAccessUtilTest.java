package io.khasang.moika.util;

import io.khasang.moika.config_for_test_purposes.TestAppConfig;
import io.khasang.moika.entity.User;
import io.khasang.moika.entity.User_;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

/**
 * Тесты для библиотеки утилит для работы с данными
 * @author Rostislav Dublin
 * @since 16.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
@EnableTransactionManagement
public class DataAccessUtilTest extends Assert{

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    DataAccessUtil dataAccessUtil;

    @Test
    @Transactional
    public void getQueryOfEntityWithComplexEqualConditionTest(){
        TypedQuery<User> userQuery =
                dataAccessUtil.getQueryOfEntityWithComplexEqualCondition(
                        User.class,
                        Collections.singletonMap(User_.login.getName(), "root"));

        List<User> userFound = userQuery.getResultList();

        assertTrue(userFound.size() > 0);
        assertEquals("root", userFound.get(0).getLogin());
    }

}
