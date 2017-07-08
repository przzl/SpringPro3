<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String error = (String)request.getAttribute("error");
    String errorMsg = (String)request.getAttribute("errorMsg");
%>
<html>
<head>
    <title>网站登录</title>
    <style type="text/css">
        a {
            text-decoration: none
        }
    </style>
</head>
<body>
<% if(StringUtils.isNotEmpty(error)) { %>
<font color="red"><%=errorMsg%></font>
<% } %>
<form action='/admin/loginCheck.html' method="post">
    用户名：
    <input type="text" name="userName"><br>
    密 码：
    <input type="password" name="password"><br>
    <input type="submit" value="登录"/>
    <input type="reset" value="重置"/>
</form>
<br>



</body>
</html>
