package com.online.springioc.beans.factory.assembling;

import com.online.springioc.beans.factory.BeanFactory;
import com.online.springioc.beans.factory.container.BeanContainer;
import com.online.springioc.beans.factory.resources.BeanClassLoad;
import com.online.springioc.logger.LoggerUtil;
import org.slf4j.Logger;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by lihongjing on 2018/8/6.
 */
public class BeanObtainTagging implements BeanFactory {
    private final static Logger logger = LoggerUtil.getLogger(BeanObtainTagging.class);

    private final static String ANNOTATION_TYPE_PATH = "com.online.springioc.annotation.lable";

    private BeanClassLoad beanClassLoad;

    private List<Class> aoonotationClass;

    private List<BeanContainer> beanContainers;

    private String SCANNING_PATH;

    @Override
    public Object getBean(String beanName) throws ClassNotFoundException {
        if (beanName == null) {
            throw new NullPointerException();
        }
       return this.getBeanContainer(beanName);
    }

    @Override
    public <T> T getBean(Class<T> beanType) throws ClassNotFoundException {
        return (T) this.getBeanContainer(beanType);
    }

    @Override
    public List<BeanContainer> listbeanContainer() {
        return this.beanContainers;
    }

    public BeanObtainTagging(String path) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        injection(path);
    }

    private void injection(String path) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        SCANNING_PATH = path;
        List<Class> classes = scanningClass();
        loadBean(classes);
    }

    private void loadBean(List<Class> zz) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        beanClassLoad = new BeanClassLoad();
        beanClassLoad.init(SCANNING_PATH);
        beanClassLoad.setClassNames();

        beanContainers = new ArrayList<>();
        for (Class c : zz) {
            BeanContainer beanContainer = beanClassLoad
                    .loadResources(c);
            if (beanContainer == null) {
                continue;
            }
            beanContainers.add(beanContainer);
        }
    }

    private String getPackageDirectory(String packagePath){
        String root = ClassLoader.getSystemResource("").getPath();
        return new StringBuilder(root)
                .append(packagePath.replace(".", "/"))
                .toString();
    }

    private List<Class> scanningClass() throws ClassNotFoundException {
        String packageDirectory = getPackageDirectory(ANNOTATION_TYPE_PATH);
        System.out.println("lable path:" + packageDirectory);
        aoonotationClass(packageDirectory);
        return aoonotationClass;
    }

    private List<String> getClassName(File file,List<String> classNames){
        File[] files = file.listFiles();

        for (File f : files) {
            if (! f.exists()) {
                continue;
            }

            if (f.isDirectory()){
                getClassName(f, classNames);
            }

            if (f.isFile()) {
                String path = f.getPath();
                int index = path.lastIndexOf(".");
                if ("class".equals(path.substring(index + 1))) {
                    path = path.substring(path.indexOf("classes") + 8, index);
                    classNames.add(path.replace("\\", "."));
                }
            }
        }
        return classNames;
    }

    private void aoonotationClass(String packageDirectory) throws ClassNotFoundException {
        List<String> classNames = new ArrayList<>();
        classNames = getClassName(new File(packageDirectory), classNames);

        aoonotationClass = new ArrayList<>();
        for (String name : classNames) {
            System.out.println("Class name:" + name);
            Class<?> aClass = Class.forName(name);
            aoonotationClass.add(aClass);
        }
    }

    private Object getBeanContainer(Object obj) throws ClassNotFoundException {
        Iterator<BeanContainer> iterator = this.beanContainers.iterator();
        while (iterator.hasNext()) {
            BeanContainer bc = iterator.next();
            Object bean = null;
            if (obj instanceof String) {
                bean = bc.getBeanByName(obj.toString());
            }
            else if (obj instanceof Class) {
                bean = bc.getBeanByType((Class) obj);
            }

            if (bean != null) {
                return bean;
            }
        }

        throw new ClassNotFoundException();
    }







}
