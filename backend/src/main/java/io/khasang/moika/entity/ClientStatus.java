package io.khasang.moika.entity;

import javax.persistence.Entity;

/**
 * Сущность, описывающая статус клиента.
 * Нужна для управления взаимоотношениями с ним
 */
@Entity (name = "client_status")
public class ClientStatus extends ABaseMoikaStatusReference{

    public ClientStatus() {
    }

    public ClientStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public ClientStatus(String code) {
        this.code = code;
    }

}
