<%--
  Created by IntelliJ IDEA.
  User: 黄日升
  Date: 2020/3/16
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script>
        window.onload = function () {
            document.getElementById("img").onclick = function () {
                this.src = "/Servlet/checkCodeServlet?="+new Date().getTime();
            }
            
        }
    </script>
    <style>
        div{
            color: #980101;
        }
    </style>
</head>
<body>
    <form action="/Servlet/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名:</td>
                <td><input type="text" placeholder="请输入用户名" name="username"></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" placeholder="请输入密码" name="password"></td>
            </tr>
            <tr>
                <td>验证码:</td>
                <td><input type="text" name="checkCode"></td>
            </tr>
            <tr>
                <td colspan="2"><img id="img" src="/Servlet/checkCodeServlet"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
    <div><%=request.getAttribute("cc_error") == null ? "" : request.getAttribute("cc_error")%></div>
    <div><%=request.getAttribute("login_error") == null ? "" : request.getAttribute("login_error")%></div>
</body>
</html>
