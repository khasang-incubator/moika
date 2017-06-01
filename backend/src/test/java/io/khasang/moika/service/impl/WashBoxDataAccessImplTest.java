package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashAddrDao;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import io.khasang.moika.service.BoxStatusDataAccessService;
import io.khasang.moika.service.BoxTypesDataAccessService;
import io.khasang.moika.service.PskvorWashBoxDataAccessService;
import io.khasang.moika.service.PskvorWashFacilityDaoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashBoxDataAccessImplTest {

    @Autowired
    PskvorWashBoxDataAccessService boxService;

    @Autowired
    @Qualifier("boxStatusDataAccessService")
    private BoxStatusDataAccessService boxStatusDao;
    @Autowired
    @Qualifier("boxTypesDataAccessService")
    private BoxTypesDataAccessService boxTypeDao;

    final String testExistingBoxName = "Бокс № 1";
    final String testNewBoxName = "Test box";
    final String stausCode = "WORKING";
    final String typeCode = "MEDIUM";


    @Test
    @Transactional
    public void testWashBoxServiceList() {

        List<WashBox> boxList = null;
        try {
            boxList = boxService.getWashBoxesOnFacility(8);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Box list is null", boxList);
        Assert.assertFalse("Box list is empty", boxList.isEmpty());
        boolean isBoxExists = false;
        for (WashBox item : boxList) {
            if (item.getBoxName().equalsIgnoreCase(testExistingBoxName)) {
                isBoxExists = true;
            }
        }
        Assert.assertTrue("Facility  list not contain " + testExistingBoxName, isBoxExists);
    }

        @Test
        @Transactional
        public void testAddWashBoxService () {

            WashBox box = new WashBox(); // подготовили класс для тестирования

            box.setBoxName(testNewBoxName);
            box.setIdFacility(8);
            box.setIdStatus((short)1);
            box.setIdType(2);
            box.setDescription("Some test box");


            WashBox resBox = new WashBox();
            try {
                resBox = boxService.addWashBox(box);
            } catch (MoikaDaoException e) {
                Assert.fail(e.getMessage());
            }

            Assert.assertNotNull("Box is null", resBox);
            Assert.assertTrue("Box not added", resBox.getBoxName().equalsIgnoreCase(testNewBoxName));
        }

    @Test
    @Transactional
    public void testGetWashBoxTypeList () {

        List<BoxType> boxTypeList = null;
        try {
            boxTypeList = boxTypeDao.getAllTypes();
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Box type list is null", boxTypeList);
        Assert.assertFalse("Box type list is empty", boxTypeList.isEmpty());
        boolean isBoxTypeExists = false;
        for (BoxType item : boxTypeList) {
            if (item.getTypeCode().equalsIgnoreCase(typeCode)) {
                isBoxTypeExists = true;
            }
        }
        Assert.assertTrue("List not contain type" + typeCode, isBoxTypeExists);
    }



    @Test
    @Transactional
    public void testGetWashBoxStatusList () {
        List<BoxStatus> boxStatusList = null;
        try {
            boxStatusList = boxStatusDao.getAllStatuses();
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Box type list is null", boxStatusList);
        Assert.assertFalse("Box type list is empty", boxStatusList.isEmpty());
        boolean isBoxStatusExists = false;
        for (BoxStatus item : boxStatusList) {
            if (item.getStatusCode().equalsIgnoreCase(stausCode)) {
                isBoxStatusExists = true;
            }
        }
        Assert.assertTrue("List not contain type" + stausCode, isBoxStatusExists);
    }

}
