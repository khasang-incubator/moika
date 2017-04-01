package io.khasang.moika.entity;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    Person person =new Person();

    @Test
    public void getId() throws Exception {
        person.getId();

        assertEquals(null,person.getId());
    }
    @Test
    public void setGetName() throws Exception {
        person.setName("Вальтер Скот");
        assertEquals("Вальтер Скот", person.getName());
        person.setName(null);
        assertEquals(null, person.getName());
    }
    @Test
    public void setGetBirthDate() throws Exception {
        Date date = new GregorianCalendar(1955,0,7).getTime();
        person.setBirthDate(date);
        System.out.println(date);
        assertEquals(date, person.getBirthDate());
    }
    @Test
    public void setGetPhones() throws Exception {
        Phone phone = new Phone("0123456789");
        person.getPhones().add(phone);
        System.out.println(person.getPhones().get(0).getNumber());
    }

}