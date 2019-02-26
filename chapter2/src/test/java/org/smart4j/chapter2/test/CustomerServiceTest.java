package org.smart4j.chapter2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter2.helper.DataBaseHelper;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

/**
 * CustomerService 单元测试
 * Created by DINGJUN on 2018/4/13.
 */
public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init() throws IOException {
//        String file = "sql/customer_init.sql";
//        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        String sql;
//        while ((sql = reader.readLine()) != null){
//            DataBaseHelper.executeUpdate(sql);
//        }
//        DataBaseHelper.executeSqlFile(file);
        // TODO 初始化失败，原因未知。
    }

    @Test
    public void getCustomerListTest() {
        List<Customer> customerList = customerService.getCustomerList("");
        Assert.assertEquals(2,customerList.size());
        System.out.println(customerList);
    }

    @Test
    public void getCustomerTest() {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest() throws Exception {
        HashMap<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name","customer100");
        fieldMap.put("contact","john");
        fieldMap.put("telephone","132465598713");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomerTest() {
        long id = 1;
        HashMap<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("contact","eric");
        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest() {
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }


}
