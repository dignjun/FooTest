package org.smart4j.chapter2.controller;

import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 客户修改界面
 * Created by DINGJUN on 2018/4/24.
 */
@WebServlet("/customer_edit")
public class CustomerModifyServlet extends HttpServlet{

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        // 在这里进行初始化，保持在整个web应用中只有一个实例
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Customer customer = customerService.getCustomer(Long.valueOf(id));
        req.setAttribute("customer",customer);
        req.getRequestDispatcher("/WEB-INF/view/customer_edit.jsp").forward(req,resp);
    }
}
