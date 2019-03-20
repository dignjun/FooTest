package com.example;

import com.example.core.util.SpringContextHolder;
import com.example.guns.GunsApplication;
import com.example.guns.config.properties.BeetlProperties;
import com.example.guns.config.properties.GunsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author DINGJUN
 * @date 2019.03.14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GunsApplication.class)
public class PropertiesTest {
    @Autowired
    GunsProperties gunsProperties;
    @Autowired
    SpringContextHolder springContextHolder;
    @Autowired
    BeetlProperties beetlProperties;
    @Test
    public void propTest(){
        System.out.println(gunsProperties);
        System.out.println("---------");
        System.out.println(springContextHolder.getBean(GunsProperties.class));
        System.out.println("---------");
        System.out.println(gunsProperties.getSwaggerOpen());
        System.out.println(beetlProperties.getResourceAutoCheck());
    }
}
