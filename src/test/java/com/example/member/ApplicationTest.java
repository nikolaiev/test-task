package com.example.member;

import com.example.member.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    IUserService service;

    @Test
    public void init() {
        /*exception about mongo connection in logs while context is bringing up can be ignored,
         since mongo are not going to start*/
        Assert.assertNotNull(service);
        System.out.println("Context is up");
    }
}