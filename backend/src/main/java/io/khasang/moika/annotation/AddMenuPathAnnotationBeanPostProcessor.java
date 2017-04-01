package io.khasang.moika.annotation;

import io.khasang.moika.util.MenuMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

public class AddMenuPathAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        String root = null;
        if (bean.getClass().isAnnotationPresent(Controller.class)) {
            if (bean.getClass().isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = bean.getClass().getAnnotation(RequestMapping.class);
                root = requestMapping.path().length > 0 ? requestMapping.path()[0] : null;
            }
            for (Method method : bean.getClass().getMethods()) {
                AddMenuPath menuPath = method.getAnnotation(AddMenuPath.class);
                String path = getPath(method.getAnnotation(RequestMapping.class));
                if (menuPath != null && path != null) {
                    menuMapper.getMenuMap().put(StringUtils.isEmpty(menuPath.name()) ? method.getName() : menuPath.name(),
                            (root != null ? root : "") + path);
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private String getPath(RequestMapping requestMapping) {
        String result = null;
        if(requestMapping!=null){
            if(requestMapping.path().length > 0&&requestMapping.path()[0]!=null){
                result = requestMapping.path()[0];
            }else if(requestMapping.value().length > 0&&requestMapping.value()[0]!=null){
                result = requestMapping.value()[0];
            }
        }
        return  result;
    }
}
