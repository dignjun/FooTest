package org.smart4j.chapter2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DataBaseHelper;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 提供客户数据服务
 *
 * Created by DINGJUN on 2018/4/13.
 */
public class CustomerService {

//    private static final String DRIVER;
//    private static final String URL;
//    private static final String USERNAME;
//    private static final String PASSWORD;
//    private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);
//    static {
//        Properties conf = PropsUtil.loadProps("config.properties");
//        DRIVER = conf.getProperty("jdbc.driver");
//        URL = conf.getProperty("jdbc.url");
//        USERNAME = conf.getProperty("jdbc.username");
//        PASSWORD = conf.getProperty("jdbc.password");
//
//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException e) {
//            LOGGER.error("can not load jdbc driver",e);
//        }
//    }
//    /**
//     * 获取客户列表
//     * @param keyword
//     * @return
//     */
//    public List<Customer> getCustomerList(String keyword) {
//        Connection conn = null;
//        ArrayList<Customer> customerList = new ArrayList<Customer>();
//        try {
//            String sql = "select * from customer";
//            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Customer customer = new Customer();
//                customer.setId(rs.getLong("id"));
//                customer.setName(rs.getString("name"));
//                customer.setContact(rs.getString("contact"));
//                customer.setTelephone(rs.getString("telephone"));
//                customer.setEmail(rs.getString("email"));
//                customer.setRemark(rs.getString("remark"));
//                customerList.add(customer);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("execute sql failure",e);
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    LOGGER.error("close connection failure",e);
//                }
//            }
//        }
//        return customerList;
//    }

//    /**
//     * 获取客户列表（优化service中的静态变量和代码块）
//     * @param keyWord
//     * @return
//     */
//    public List<Customer> getCustomerList2(String keyWord) {
//        Connection conn = null;
//        ArrayList<Customer> customerList = new ArrayList<Customer>();
//        String sql = "select * from customer";
//        conn = DataBaseHelper.getConnection();
//        try {
//            PreparedStatement prop = conn.prepareStatement(sql);
//            ResultSet rs = prop.executeQuery();
//            while (rs.next()) {
//                Customer customer = new Customer();
//                customer.setId(rs.getLong("id"));
//                customer.setName(rs.getString("name"));
//                customer.setContact(rs.getString("contact"));
//                customer.setTelephone(rs.getString("telephone"));
//                customer.setEmail(rs.getString("email"));
//                customer.setRemark(rs.getString("remark"));
//                customerList.add(customer);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("execute sql failure",e);
//        } finally {
//            DataBaseHelper.closeConnection(conn);
//        }
//        return null;
//    }

    /**
     * 获取客户列表（使用dbutil简化实体到数据字段的映射）
     * @param keyWord
     * @return
     */
    public List<Customer> getCustomerList(String keyWord) {
        List<Customer> customerList = new ArrayList<Customer>();
        String sql = "select * from customer";
        try {
            customerList = DataBaseHelper.queryEntityList(Customer.class, sql);
        } finally {
            DataBaseHelper.closeConnection();
        }
        return customerList;
    }


    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(long id) {
        String sql = "select * from customer where id = ?";
        Customer customer = DataBaseHelper.queryEntity(Customer.class, sql, id);
        return customer;
    }

    /**
     * 创建客户
     * @param fieldMap
     * @return
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        boolean boo = DataBaseHelper.insertEntity(Customer.class, fieldMap);
        return boo;
    }

    /**
     * 更新客户
     * @param id
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DataBaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    public boolean deleteCustomer(long id) {
        return DataBaseHelper.deleteEntity(Customer.class,id);
    }

}

