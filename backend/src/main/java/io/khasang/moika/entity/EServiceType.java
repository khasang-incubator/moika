package io.khasang.moika.entity;

/**
 * Created by pauls on 19.07.2017.
 */
public enum EServiceType {
    WASH,
    CLEAN,
    CHEM_CLEAN,
    POLISH,
    COMPLEX,
    OTHER,
    TEST;


    public String getServiceEntityName(){
        String entityName;
        switch (this) {
            case WASH:  entityName = "wash_services";
                break;
            case CLEAN: entityName = "clean_services";
                break;
            case CHEM_CLEAN: entityName = "chem_clean_services";
                break;
            case POLISH: entityName = "complex_services";
                break;
            case COMPLEX: entityName = "polish_services";
                break;
            case OTHER: entityName = "other_services";
                break;
            default: entityName = "services";
                break;
        }
       return  entityName;
    }
}