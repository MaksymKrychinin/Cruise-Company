package com.example.cruiseonspring.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;

//todo
//todo
//todo
//todo
//todo
/*

public class DateLaterThanNowImpl implements BeanPostProcessor {
    private static boolean checkAnnotated(Field field) {
        return field.isAnnotationPresent(DateLaterThanNow.class);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        Arrays.stream(declaredFields)
                .filter(DateLaterThanNowImpl::checkAnnotated)
                .filter(field -> field.getClass().equals(Date.class));
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
*/
