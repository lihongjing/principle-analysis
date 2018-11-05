package com.online.springioc.test;

import com.online.springioc.annotation.assemble.Autowired;
import com.online.springioc.annotation.lable.Component;

/**
 * Created by lihongjing on 2018/11/2.
 */
@Component
public class TestControl {

    @Autowired("testService")
    private TestService testService;

    public void sysA(){
        System.out.println("TestControl");
        testService.sysB();
    }

}
