<%--
  Created by IntelliJ IDEA.
  User: DINGJUN
  Date: 2019.08.18
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.String" %>
<html>
<head>
    <title>Read</title>
</head>
<body>
<%--通过JSP(JSP本身就是一个Servlet)模拟服务端的控制器,返回Ajax请求的数据--%>
<%--!!! WEB-INF中需要通过Controller进行访问,不能直接通过访问,所以还是这里的代码放在webapp下面就好了--%>
<%
    /*查询请求的JSON数据 */
    String result = "{\"data\": [{\"id\":\"1\",\"departmentId\":\"1\",\"dateHired\":\"20190818\",\"dateFired\":\"20190819\",\"dob\":\"20190816\",\"firstName\":\"f1\",\"lastName\":\"l1\",\"title\":\"abc1\",\"street\":\"sh1\",\"city\":\"sha1\",\"street\":\"cy1\",\"zip\":\"z1\"}," +
            "{\"id\":\"2\",\"departmentId\":\"2\",\"dateHired\":\"20190818\",\"dateFired\":\"20190819\",\"dob\":\"20190816\",\"firstName\":\"f2\",\"lastName\":\"l2\",\"title\":\"abc2\",\"street\":\"sh2\",\"city\":\"sha2\",\"street\":\"cy2\",\"zip\":\"z2\"}]," +
            "\"totalCount\":\"2\"}";
    out.print(result);
%>
</body>
</html>
