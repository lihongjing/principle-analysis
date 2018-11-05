package com.online.springioc.demo.loadclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lihongjing on 2018/11/1. 类加载器相关知识点
 *1. 双亲委托机制
 *2. 自定义LoadClass
 *3. 重写classLoad与findClass区别
 *4. defineClass  byte加载Class
 *5. 不同类加载器加载相同的字节码文件不相等
 */
public class LoadClassDemo extends ClassLoader{


//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        return findClass(name);
//    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String path = ClassLoader.getSystemResource("").getPath();
        StringBuilder sb = new StringBuilder(path).append(name.replace(".", "/")).append(".class");
        System.out.println(sb);
        File file = new File(sb.toString());
        byte[] by = new byte[(int)file.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(by);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, by, 0, by.length);
    }


    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
//        LoadClassDemo test01 = new LoadClassDemo();
//        Class<?> aClass = test01.findClass("com.online.springioc.beans.factory.container.LoadClassDemo");
//        System.out.println(aClass.hashCode());
//        System.out.println(LoadClassDemo.class.hashCode())

    }
}
