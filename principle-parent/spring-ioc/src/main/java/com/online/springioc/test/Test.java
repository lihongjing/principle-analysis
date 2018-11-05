package com.online.springioc.test;

import com.online.springioc.beans.factory.BeanFactory;
import com.online.springioc.beans.factory.assembling.BeanAssembling;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by lihongjing on 2018/11/2.
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        BeanAssembling beanAssembling = new BeanAssembling("com.online.springioc.test");
        try {
            beanAssembling.assemling();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        BeanFactory beanFatory = beanAssembling.getBeanFatory();
        TestControl bean = beanFatory.getBean(TestControl.class);
        bean.sysA();

    }
}
