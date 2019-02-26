package org.smart4j.chapter2.controller;

/**
 * 创建用户
 * Created by DINGJUN on 2018/4/13.
 */

import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerCreateServlet extends HttpServlet{

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
//      在servlet的init方法中初始化，避免创建多个customerService实例，整个web应用中只需要一个customerService实例（单例）
        customerService = new CustomerService();
    }

    /**
     * 进入创建客户界面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerService.getCustomerList("");
        req.setAttribute("customerList",customerList);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req,resp);
    }

    /**
     * 处理创建客户的请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }
}
