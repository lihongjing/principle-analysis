package com.online.springioc.beans.factory.container;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;



/**
 * Created by lihongjing on 2018/8/6.
 */
public class BeanContainer implements Serializable {

    private Object key;

    private Object value;

    public BeanContainer(Object key, Object value){
        this.key = key;
        this.value = value;
    }

    public Object getKey(){
        return this.key;
    }

    public Object getValue(){
        return this.value;
    }

    public Object getBeanByName(String name){
        if (name.equals(key)) {
            return value;
        }
        return null;
    }

    public Object getBeanByType(Class aClass){
        System.out.println("key hashCode:" + key.hashCode() + key.getClass());

        System.out.println("Type hashCode:" + aClass.hashCode() + aClass.getClass());
        if (aClass.hashCode() == key.hashCode()) {
            return value;
        }
        return null;
    }

}
