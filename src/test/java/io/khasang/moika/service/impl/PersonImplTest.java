package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.PersonDao;
import io.khasang.moika.entity.Person;
import io.khasang.moika.entity.Phone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class PersonImplTest {


    @Autowired
    PersonDao personDao;
    final String personName = "Пупкин Петр Владимирович";
    final Date personBirthDay = Date.valueOf("1998-05-15");
    final String phoneNum = "325-555-55-5";

/*
    @Test
    @Transactional
    public void testUperoneList() {
        final String testString = "Мойка на Мойке";
        List<WashFacility> fcltList = null;
        try {
            fcltList = fcltService.getWashFacilitiesOnNet(1);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Facility  list is null", fcltList);
        Assert.assertFalse("Facility  list is empty", fcltList.isEmpty());
        boolean isWashFacility = false;
        boolean isBox = false;
        int dur = 0;
        for (WashFacility item : fcltList) {
            if (item.getName().equalsIgnoreCase("Мойка на Мойке")) {
                isWashFacility = true;
                List<WashBox> boxList = item.getWashBoxes();
                for (WashBox box : boxList) {
                    if (box.getBoxName().equalsIgnoreCase("Бокс 1")) {
                        isBox = true;
                        break;
                    }
                }
            }
        }
        Assert.assertTrue("Facility  list not contain "+testString, isWashFacility);
        Assert.assertTrue("Facility  list not contain box", isBox);
    }
*/
    @Test
    @Transactional
    @Rollback
    public void testAddPerson() {
        Person person = new Person(personName); // подготовили класс для тестирования
        person.setBirthDate(personBirthDay);

        List<Phone> phoneList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneNum+i);
            phoneList.add(phone);
        }

        person.setPhones(phoneList);

        Person resPerson = new Person();
        try {
            resPerson = personDao.create(person);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Person is null", resPerson);
        boolean isTel = false;
            if (resPerson.getFullName().equalsIgnoreCase(personName)) {
                Assert.assertEquals("tel list not ", 4, resPerson.getPhones().size());
                List<Phone>  resPhoneList = resPerson.getPhones();
                for (Phone phone : resPhoneList) {
                    if (phone.getPhoneNumber().equalsIgnoreCase(phoneNum+"1")) {
                        isTel= true;
                        break;
                    }
                }
        }
        Assert.assertTrue("Person does not exist"+ personName + " instead " +resPerson.getFullName(),
                resPerson.getFullName().equalsIgnoreCase(personName));
        Assert.assertTrue("Phone  list not contain number", isTel);
    }
}
