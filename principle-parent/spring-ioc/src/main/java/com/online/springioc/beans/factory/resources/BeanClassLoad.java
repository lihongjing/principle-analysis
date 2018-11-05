package com.online.springioc.beans.factory.resources;

import com.online.springioc.beans.factory.container.BeanContainer;
import com.online.springioc.logger.LoggerUtil;
import org.slf4j.Logger;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by lihongjing on 2018/8/6.
 */
public class BeanClassLoad<T extends Annotation> {
    private final static Logger logger = LoggerUtil.getLogger(BeanClassLoad.class);

    private BeanContainer beanContent;

    private String directory;

    private List<String> classNames;

    public BeanClassLoad() {

    }

    public BeanClassLoad(String packageName){
        this.init(packageName);
    }

    public void init(String packageName){
        this.initFilePath(packageName);
    }

    private void initFilePath(String packageName){
        StringBuilder sb;

        sb = new StringBuilder();
        String path = ClassLoader.getSystemResource("").getPath();
        sb.append(path)
            .append(packageName.replace(".","/"));
        this.directory = sb.toString();
        logger.info("directory-"+this.directory);
    }

    public void setClassNames(){
        File file = new File(directory);
        readClassResources(file);
    }

    public BeanContainer loadResources(Class<T> zz)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        loadClassContent(zz);
        return beanContent;
    }

    private void readClassResources(File file){
        File[] files = file.listFiles();
        if (classNames == null){
            classNames = new ArrayList<String>();
        }

        for (File f: files) {
            if (!f.exists()){
                continue;
            }

            if (f.isDirectory()){
                this.readClassResources(f);
                continue;
            }

            if (f.isFile()){
                String path = f.getPath();
                logger.info("File path-"+path);
                if (".class".equals(path.substring(path.lastIndexOf(".")).trim())){
                    String str = path.substring(path.indexOf("classes")+8,
                            path.lastIndexOf(".")).replace("\\",".");
                    classNames.add(str);
                }
                continue;
            }
        }
        logger.info("classNames:"+classNames.toString());
    }

    private void loadClassContent(Class<T> zz) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        for (String className : classNames) {
            beanContent = null;
            Class<?> aClass = Class.forName(className);
            T annotation = aClass.getAnnotation(zz);
            if(annotation == null) {
                continue;
            }
            Method method = zz.getMethod("value");
            Object value = method.invoke(annotation);
            if(value.toString().length() != 0){
                beanContent = new BeanContainer(value.toString(), aClass.newInstance());
            }else{
                beanContent = new BeanContainer(aClass, aClass.newInstance());
            }

            if (beanContent != null){
                return;
            }
        }
    }
}
