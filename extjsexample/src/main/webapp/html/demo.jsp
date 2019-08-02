<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Ext</title>
    <%--jsp中引入文件和html的不同，静态和动态，相对和绝对的区别--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ext6/resources/Admin-all.css">
    <script src="${pageContext.request.contextPath}/ext/adapter/ext/ext-base.js"></script>
    <script src="${pageContext.request.contextPath}/ext6/ext-all.js"></script>
</head>
<body>
<div style="text-align: center">Hello Ext</div>
</body>
<script type="text/javascript">
    Ext.onReady(function () {
        Ext.Msg.confirm('提示', '确认吗？',function(buttonId, value, opt) {
            Ext.Msg.alert("选择：" + s);
        });
    })
</script>
</html>