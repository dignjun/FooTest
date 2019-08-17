<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <%--jsp中引入文件和html的不同，静态和动态，相对和绝对的区别--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ext6/resources/Admin-all.css">
    <script src="${pageContext.request.contextPath}/ext/adapter/ext/ext-base.js"></script>
    <script src="${pageContext.request.contextPath}/ext6/ext-all.js"></script>
</head>
<body>
<div id="login_div"></div>
</body>
<script type="text/javascript">
    Ext.onReady(function () {
        Ext.create('form-login').renderTo(Ext.get('login_div'));
    })

    Ext.define('Ext.form.LoginPanel', {
        extend: 'Ext.form.Panel',
        xtype: 'form-login',
        // controller: 'form-login',
        title: 'Login',

        bodyPadding: 20,
        width: 320,
        autoSize: true,
        height: 100,
        width: 100,
        items: [{
            xtype: 'textfield',
            allowBlank: false,
            required: true,
            label: 'User ID',
            name: 'username'
        }, {
            xtype: 'passwordfield',
            allowBlank: false,
            required: true,
            label: 'Password',
            name: 'password'
        }, {
            xtype: 'checkbox',
            boxLable: 'Remember me',
            name: 'remember'
        }],
        buttons: [{
            text: 'Login',
            handler: 'onLogin'
        }],
    })

    Ext.define('Example.view.forms.LoginController', {
        extend: 'Ext.app.ViewController',
        alias: 'controller.form-login',

        onLogin: function () {
            var form = this.getView();

            if (form.validate()) {
                Ext.Msg.alert('Login Success', 'You have been logged in!');
            }
            else {
                Ext.Msg.alert('Login Failure', 'The username/password provided is invalid.');
            }
        }
    });
</script>
</html>