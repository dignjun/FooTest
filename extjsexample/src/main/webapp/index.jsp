<%--
  Created by IntelliJ IDEA.
  User: jun.ding
  Date: 2019/5/23
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello world</title>
</head>
<script type="text/html" src="ext/js/ext-all.js"></script>
<body>
    <h4>welcome</h4>
</body>
<script>
    Ext.onReady(function () {
        alert("hello");
        Ext.MessageBox.confirm("hello", "world morning", function (btn) {
            alert("窗口" + btn), this
        })
    });
</script>
</html>
