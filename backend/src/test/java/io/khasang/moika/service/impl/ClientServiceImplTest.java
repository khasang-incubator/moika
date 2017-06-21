package io.khasang.moika.service.impl;

import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.PersonDao;
import io.khasang.moika.entity.Client;
import io.khasang.moika.entity.ClientStatus;
import io.khasang.moika.entity.Person;
import io.khasang.moika.entity.Phone;
import io.khasang.moika.service.ClientDataAccessService;
import io.khasang.moika.service.ClientStatusDataAccessService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class ClientServiceImplTest {

    @Autowired
    ClientDataAccessService clientService;
    @Autowired
    ClientStatusDataAccessService clientStatussService;

    final long existingClientId = 1L;
    final String personName = "Сидоров Иван Петрович";
    final Date personBirthDay = new Date();
    final String phoneNum = "902-535-55-5";
    final String clientStatusCode = "ORD";
    
    /**
     * Add client, take it from session, and check it's clientType
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback
    public void addClient() throws Exception {
        Person person = new Person(personName); // подготовили класс для тестирования
        person.setBirthDate(personBirthDay);

        Set<Phone> phoneSet = new HashSet<>();
        for (int i = 1; i < 2; i++) {
            phoneSet.add(new Phone(phoneNum+i));
        }

        person.setPhones(phoneSet);

        Client client = new Client();
        client.setPerson(person);
        ClientStatus clientStatus = (ClientStatus) clientStatussService.getStatusByCode(clientStatusCode);
        client.setStatus(clientStatus);

        Client retClient = clientService.addClient(client);

        Assert.assertNotNull("Return client is null", retClient);
        Assert.assertTrue("Client status code not " + clientStatusCode, retClient.getStatus().getStatusCode().equalsIgnoreCase(clientStatusCode));
        Assert.assertTrue("Client name not " + personName, retClient.getPerson().getFullName().equalsIgnoreCase(personName));
        Assert.assertTrue("Client birthday not " + personBirthDay.toString(), retClient.getPerson().getBirthDate().equals(personBirthDay));
    }

    /**
     * Get last added client by it's id
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback
    public void getClientById() throws Exception {
        Client client = clientService.getClientById(existingClientId);
        Assert.assertNotNull("Return client is null", client);
        Assert.assertTrue("Client ID not the same "+client.getId(),client.getId() == existingClientId);
    }

    @Test
    @Transactional
    @Rollback
    public void getClientList() throws Exception {
        List<Client> clientList = clientService.getAllClients();
        Assert.assertNotNull("Client  list is null", clientList);
        Assert.assertFalse("Client list in empty",clientList.isEmpty());
        Assert.assertNotNull("Client person in empty",clientList.get(0).getPerson().getId());
    }

    @Test
    @Transactional
    @Rollback
    public void updateClient() throws Exception {
        Client client = clientService.getClientById(existingClientId);
        client.setDateLastWash(personBirthDay);
        Client retClient = clientService.updateClient(client);
        Assert.assertNotNull("Return client is null", retClient);
        Assert.assertTrue("Client not updated " , retClient.getDateLastWash().equals(personBirthDay));
        Assert.assertTrue("Client not the same " , retClient.equals(client));

    }

}