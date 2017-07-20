package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.EServiceType;
import io.khasang.moika.entity.PriceList;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import io.khasang.moika.service.MoikaServiceTypesService;
import io.khasang.moika.service.PriceListDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;

/**
 * Контроллер для управления боксами автомоек
 *
 * @author Pauls
 */
@RestController
@RequestMapping(value = "/api/priceList",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
public class PriceListController {

    @Autowired
    PriceListDataAccessService priceListDAS;
    @Autowired
    MoikaServiceTypesService serviceTypesDAS;


    /**
     * Вывод информации о всех боксах
     *
     * @return
     */
    @RequestMapping(value = "/{idFclt}", method = RequestMethod.GET )
    @ResponseStatus(HttpStatus.OK)
    public Object getFullPriceList(@PathVariable(value = "idFclt") int idFclt) {
        List<PriceList> priceList = priceListDAS.getFullPriceList(idFclt);
        if ((priceList == null) || (priceList.isEmpty()))
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return priceList;
    }

    /**
     * Услуги по видам помывки
     */
    @RequestMapping(value = "/byType/{idFclt}/{type}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getPriceListByServiceType(@PathVariable(value = "idFclt") int idFclt, @PathVariable(value = "type") String type) {
        int idType = serviceTypesDAS.getTypeByCode(type).getId();
        PriceList priceList = null;
        if (idType != 0 ) {
            priceList = priceListDAS.getPriceListByServiceType(idFclt, idType);
        }
        if (priceList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return priceList;
    }
}
