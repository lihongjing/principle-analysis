package com.online.springioc.beans.factory.assembling;

import com.online.springioc.annotation.assemble.Autowired;
import com.online.springioc.beans.factory.BeanFactory;
import com.online.springioc.beans.factory.container.BeanContainer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by lihongjing on 2018/10/18.
 */
public class BeanAssembling {

    private String packagePath;

    private BeanFactory beanFactory;

    public BeanAssembling(String packagePath){
        if (packagePath == null) {
            throw new NullPointerException();
        }
        this.packagePath = packagePath;
    }

    public void assemling() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        beanFactory = new BeanObtainTagging(packagePath);
        this.loadAssemble(beanFactory);
    }

    private void loadAssemble(BeanFactory beanFactory) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        List<BeanContainer> beanContainers = beanFactory.listbeanContainer();
        Class<Autowired> autowiredClass = Autowired.class;
        for (BeanContainer bc : beanContainers) {
            Field[] fields = bc.getValue().getClass().getDeclaredFields();
            for (Field f : fields) {
                Autowired annotation = f.getAnnotation(autowiredClass);
                if (annotation == null) {
                    continue;
                }
                Method method = autowiredClass.getMethod("value");
                Object beanName = method.invoke(annotation);
                Object bean = null;
                if (beanName != null && !"".equals(beanName)) {
                    bean = beanFactory.getBean(beanName.toString());
                }else {
                    Class beanClass = f.getType();
                    bean = beanFactory.getBean(beanClass);
                }

                if (bean != null) {
                    f.setAccessible(true);// 属性可见性
                    f.set(bc.getValue(), bean);
                }
            }
        }
    }

    public BeanFactory getBeanFatory(){
        return this.beanFactory;
    }


}
