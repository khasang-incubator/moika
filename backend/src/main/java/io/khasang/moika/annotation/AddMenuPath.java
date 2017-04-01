package io.khasang.moika.annotation;


import java.lang.annotation.*;

/**
 * Данная аннотация используется для добавления статических путей в MapMenuPath с помощью которой
 * можно автоматически формировать меню сайта
 * !!!Важно!!! данный функционал не полон и еще в разработке
 *
 * @since 10.03.2017
 * @author Kovalev Aleksandr
 * */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//@Documented
public @interface AddMenuPath {
    String name() default "";
}
