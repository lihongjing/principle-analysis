package com.online.springioc.beans.factory;

import com.online.springioc.beans.factory.container.BeanContainer;

import java.util.List;

/**
 * Created by lihongjing on 2018/8/6.
 */
public interface BeanFactory {
    Object getBean(String beanName) throws ClassNotFoundException;

    <T> T getBean(Class<T> beanType) throws ClassNotFoundException;

    List<BeanContainer> listbeanContainer();


}
