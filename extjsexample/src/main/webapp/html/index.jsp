<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Ext</title>
    <link rel="stylesheet" href="../ext6/resources/Admin-all.css">
    <script src="../ext/adapter/ext/ext-base.js"></script>
    <script src="../ext6/ext-all.js"></script>
</head>
<body>
<div style="text-align: center">Hello Ext</div>
</body>
<script type="text/javascript">
    Ext.onReady(function() {
        Ext.Msg.confirm('提示','确认吗？');
    })
</script>
</html>