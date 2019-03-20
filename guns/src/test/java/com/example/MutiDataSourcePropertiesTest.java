package com.example;

import com.example.core.config.properties.MutiDataSourceProperties;
import com.example.guns.GunsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 没有@Component注解或者@Bean注解不能被注入
 *
 * @author DINGJUN
 * @date 2019.03.14
 */
@SpringBootTest(classes = GunsApplication.class)
@RunWith(SpringRunner.class)
public class MutiDataSourcePropertiesTest {
    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;

    @Test
    public void propTest(){
        System.out.println(mutiDataSourceProperties);
    }

    @Bean
    public MutiDataSourceProperties mutiDataSourceProperties(){
        return new MutiDataSourceProperties();
    }
}
