package com.online.springioc.test;

import com.online.springioc.annotation.assemble.Autowired;
import com.online.springioc.annotation.lable.Service;

/**
 * Created by lihongjing on 2018/11/2.
 */
@Service("testService")
public class TestService {
    @Autowired
    private TestDao testDao;

    public void sysB(){
        System.out.println("TestService");
        testDao.sysC();
    }
}
