package io.khasang.moika.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;
/**
 *  * !!! На удаление ???
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Butterfly extends Company implements Serializable{
}
