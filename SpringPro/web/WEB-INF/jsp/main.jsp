<%@ page import="com.hzb.domain.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User user = (User) request.getAttribute("user");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>网站主页</title>
    <script type="text/javascript" src="/js/AjaxRequest.js"></script>
</head>
<body onload="loadDemo()">

<br>

<div style="text-align :center;"><%=user.getUserName()%>
    欢迎您进入网站主页！
    你的登陆IP是<%=user.getLastIp()%>。
    纬度:<span id="latitude">正在获取中...</span>&nbsp;&nbsp;
    经度:<span id="longitude">正在获取中...</span> &nbsp;&nbsp;
    城市:<span id="cityName">正在获取中...</span>
</div>

<%--<div style="display:inline"> 纬度:--%>
    <%--<div id="latitude" style="float:left">正在获取中...</div>--%>
    <%--经度：--%>
    <%--<div id="longitude" style="float:left">正在获取中...</div>--%>
<%--</div>--%>
<!--纬度:<span id="latitude">正在获取中...</span>-->
<!--经度:<span id="longitude">正在获取中...</span>-->
<br>
<hr>
<p id="status">该浏览器不支持HTML5 Geolocation。</p>


</body>
</html>
<script type="text/javascript">
    /**
     * 获取地理坐标
     */
    function loadDemo() {
        if (navigator.geolocation) {
            updateStatus("浏览器支持HTML5 Geolocation。");
            navigator.geolocation.watchPosition(updateLocation, handleLocationError, {maximumAge:20000});
        }
    }
    /**
     * 显示是否支持 Geolocation 功能
     */
    function updateStatus(message) {
        document.getElementById("status").innerHTML = message;
    }
    /**
     * 设置坐标值
     */
    function updateLocation(position) {
        var latitude = position.coords.latitude;
        var longitude = position.coords.longitude;

        document.getElementById("latitude").innerHTML = latitude;
        document.getElementById("longitude").innerHTML = longitude;
        
         getInfo(latitude,longitude);
    }
    /**
     * 封装错误信息
     *
     * @param error
     */
    function handleLocationError(error) {
        switch (error.code)
                {
            case 0:
                updateStatus("尝试获取您的位置信息时发生错误：" + error.message);
                break;
            case 1:
                updateStatus("用户拒绝了获取位置信息请求。");
                break;
            case 2:
                updateStatus("浏览器无法获取您的位置信息：" + error.message);
                break;
            case 3:
                updateStatus("获取您位置信息超时。");
                break;
        }
    }
    
    function oChangeLongItude() {
        alert(1);
    }

    /******************错误处理的方法*******************************/
    function onerror() {
        alert("您的操作有误！");
    }

    /******************实例化Ajax对象的方法*******************************/
    function getInfo(latitude,longitude) {
       // alert("../admin/getCityName.html?&latitude="+latitude+"&longitude="+longitude);
        var loader = new ajaxUtil.AjaxRequest("../admin/getCityName.html?&latitude="+latitude+"&longitude="+longitude
                + new Date().getTime(), deal_getInfo, onerror, "POST");
    }

    /************************回调函数**************************************/
    function deal_getInfo() {
        //alert(this.req.responseText);
        document.getElementById("cityName").innerHTML = this.req.responseText;
    }
</script>