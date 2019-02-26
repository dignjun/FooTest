<%--
  Created by IntelliJ IDEA.
  User: DINGJUN
  Date: 2018/4/24
  Time: 7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户编辑</title>
</head>
<body>
<h1>修改客户信息</h1>
<table>
    <tr>
        <th>客户名称</th>
        <th>联系人</th>
        <th>电话号码</th>
        <th>邮箱地址</th>
        <th>操作</th>
    </tr>
        <tr>
            <td>${customer.name}</td>
            <td>${customer.contact}</td>
            <td>${customer.telephone}</td>
            <td>${customer.email}</td>
            <td>
                <a href="${base}/customer_edit?{customer.id}">保存</a>
                <a href="${base}/customer_delete?{customer.id}">取消</a>
            </td>
        </tr>
</table>
</body>
</html>
