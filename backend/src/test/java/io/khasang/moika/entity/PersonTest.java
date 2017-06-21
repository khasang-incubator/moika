package io.khasang.moika.entity;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {
    Person person =new Person();

    @Test
    public void testGetId() throws Exception {
        person.getId();

        assertEquals(null,person.getId());
    }
    @Test
    public void testSetName() throws Exception {
        person.setFullName("Вальтер Скот");
        assertEquals("Вальтер Скот", person.getFullName());
    }
    @Test
    public void testSetBirthDate() throws Exception {
        Date date = new GregorianCalendar(1955,0,7).getTime();
        person.setBirthDate(date);
        System.out.println(date);
        assertEquals(date, person.getBirthDate());
    }

    @Test
    public void testSetPhones() throws Exception {
        Phone phone = new Phone("0123456789");
        person.getPhones().add(phone);
        assertTrue( "Not contain " + phone.getPhoneNumber(), person.getPhones().contains(phone));
    }

}