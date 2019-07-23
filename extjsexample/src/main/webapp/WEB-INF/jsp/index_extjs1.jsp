<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jun.ding
  Date: 2019.06.14
  Time: 07:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/commons/commons.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/commons/ajaxhook.mini.js"/>"></script>
    <title>index_extjs1</title>
</head>
<body>
<div style="text-align: center">
    <h3>index_extjs1</h3>
    <br/>
    <a href="javascript:ajaxrequest()">post</a> </br>
    <a href="javascript:newajaxrequest()">post2</a><br/>
    <a href="javascript:getxhrrequest()">post3</a><br/>
    <a href="javascript:prototypexhrrequest()">post4</a>
</div>
<script>

    function getxhrrequest() {
        var xhrhttprequest = getxhr();
        xhrhttprequest.open("post","save",true);
        xhrhttprequest.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded");
        // xhrhttprequest.onreadystatechange = callback;
        xhrhttprequest.send("a=1&b=2");
    }
    // 方法增强1，实例方法的修改
    function getxhr() {
        // console.log(this.constructor.toString());
        var oldxhr;
        try {
            oldxhr = new ActiveXObject("Msxml2.XMLHTTP");// IE高版本创建XMLHTTP
        } catch (E) {
            try {
                oldxhr = new ActiveXObject("Microsoft.XMLHTTP");// IE低版本创建XMLHTTP
            } catch (E) {
                oldxhr = new XMLHttpRequest();// 兼容非IE浏览器，直接创建XMLHTTP对象
            }
        }
        // console.log(this.constructor.toString());
        // 持有原方法，问题是变量指向同一个实例
        // var _xhr = this;
        var _xhr = new XMLHttpRequest();

        // oldxhr.prototype._xhr = function(){
        //     return oldxhr;
        // };
        // for (var key in _xhr) {
        //     if(_xhr[key] != "send"){
        //         _xhr[key] = oldxhr[key];
        //     }
        // }
        oldxhr.send = function(params) {
            // for(var key in arguments) {
            //     console.log(key + ":" + arguments[key]);
            // }
            //1. 重写send方法，发送一个HTTP请求获得token
            var token = http_get("csrf_token");

            //2. 放在请求参数的末端，判断有没有参数，添加&符号
            if(params.length > 0){
                params = params + "&csrf_token=" + token;
            } else {
                params = "csrf_token=" + token
            }
            this.setRequestHeader("csrf_token", token);

            //3.执行原方法
            // console.log(this.constructor.toString());
            // params = params.split("&");
            // alert(this.constructor.toString());
            var res = _xhr.send.call(this,params);
            return res;
        }


        return oldxhr;
    }


    // 方法增强2，window调用，原型修改
    function csrf_patch(){

        var _ts = XMLHttpRequest.prototype.send;
        XMLHttpRequest.prototype['send']=function(params) {
            // 重写send方法，先获取到token
            var csrf_token = http_get("csrf_token", _ts);
            // 将token带在请求的参数上面
            if(params.length > 0) {
                params = params + "&";
            }
            params = params + "csrf_token=" + csrf_token;
            this.setRequestHeader("csrf_token",csrf_token);
            return _ts.call(this, params);
        }

    }

    // 执行
    csrf_patch();

    // 新的实例调用
    function prototypexhrrequest() {
        var req = new XMLHttpRequest();
        req.open("post","save",true);
        req.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded");
        // xhrhttprequest.onreadystatechange = callback;
        req.send("a=1&b=2");
        // protorequest.send("request param");
    }



    function http_get(url) {
        var xhr = get_xhr();
        url.indexOf('?') == -1 ? url += '?' : 0
        url += '&_ts=' + new Date().getTime()
        xhr.open('GET', url, false);
        // xhr.send();
        send.call(xhr);
        if (xhr.status != 200){
            if (window.console){
                console.log('http_get-error','status:'+xhr.status,xhr.responseText)
            }
            return ''
        }
        return xhr.responseText;
    }

    function get_xhr(){
        var xhr = false;
        try {
            xhr = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e2) {
                xhr = false;
            }
        }
        if (!xhr && window.XMLHttpRequest)
            xhr = new XMLHttpRequest();
    }








    var xhrhttprequest;

    function ajaxrequest() {
        xhrhttprequest = new XMLHttpRequest();
        xhrhttprequest.open("post","save",true);
        xhrhttprequest.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded");
        xhrhttprequest.onreadystatechange = callback;
        xhrhttprequest.send("a=1&b=2");
    }
    var newxhr;

    function newajaxrequest() {
        newxhr = new XMLHttpRequest();
        newxhr.open('GET', '/save?a=1&b=2', true);
        newxhr.onreadystatechange = callback;
        newxhr.send();
    }

    function callback() {
        if (xhrhttprequest.readyState == 2 || newxhr.readyState == 4) {
            if(xhrhttprequest.status == 200 || newxhr.readyState == 200) {
                alert("ajax call back success");
            }
        }
    }

    // new XHR({
    //     send: function () {
    //         console.log(this);
    //         alert("before callback");
    //     }
    // });

    // hookAjax({
    //     send: function (arg, xhr) {
    //         alert("abc");
    //
    //     }
    // })
/**
// 最终方案
 // csrf interceptor，对所有的xhr的http请求进行拦截，如果是符合要求的进行拦截，不是则执行原有的方法
 function csrf_patch(){
	if(!!window.ActiveXObject || "ActiveXObject" in window) { // ie 浏览器判断
        ActiveXObject = function() {
            return new XMLHttpRequest();
        };
	}
	var _prototype = XMLHttpRequest.prototype;
    var _ts = _prototype.send;
    var _tp = _prototype.open;
    _prototype['open']=function(method, url, async){
        var reg = ".*\\!.*(delete|del|save|remove|update).*\\.action$";
        if(url.match(reg)) {
            this.uncsrf_req = false;
        } else {
        	this.uncsrf_req = true;
		}
        return _tp.call(this, method, url, async);
	}
    _prototype['send']=function(params) {
		// 是否开启标识, uncsrf_req 非 true 均为开启状态
		var flag = this.uncsrf_req;
        if(flag == true) {
            return _ts.call(this, params);
        } else {
            var csrf_token = http_get2("getCSRFToken.action");
            this.setRequestHeader("csrf_token", csrf_token);
            return _ts.call(this, params);
        }
    }
}
// 页面加载完成后执行方法
window.onload = function(){
    if(window.sys_csrftoken == null || window.sys_csrftoken == undefined) {
        window.sys_csrftoken = http_get("getCSRFStatus.action");
    }
    if(window.sys_csrftoken == true || window.sys_csrftoken == 'true') {
        csrf_patch();
    }
}
// 异步的http请求
function http_get2(url) {
    var xhr = getxhr();
    xhr.uncsrf_req = true;
    url.indexOf('?') == -1 ? url += '?' : 0
    url += '&_ts=' + new Date().getTime()
    xhr.open('GET', sgcc_url_correct(url), false);
    xhr.send();
    if (xhr.status != 200){
        if (window.console){
            console.log('http_get-error','status:'+xhr.status,xhr.responseText)
        }
        return ''
    }
    return xhr.responseText;
}

























 */


</script>
</body>
</html>
