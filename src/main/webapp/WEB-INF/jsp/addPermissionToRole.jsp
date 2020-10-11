<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/6/8
  Time: 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色权限分配也页面</title>
    <style>
        th{
            width:218px;
            height: 15px;
            text-align: left;
        }
        td{
            text-align: left;
            height: 15px;
        }
    </style>
</head>
<body>
<div style="overflow:scroll;height: 600px">
<form action="${pageContext.request.contextPath}/addPermissionByRole" method="post" >
    <table border="1"  style="min-width:800px;">
        <tr><input type="text" value="${rid}" name="rid" hidden/></tr>
        <tr>
            <th>全选<input type="checkbox"></th>
            <th>权限ID</th>
            <th>权限名称</th>
            <th>权限路径</th>
        </tr>
        <tbody>
        <c:forEach items="${perList}" var="l">
            <tr>
                <td>
                    <input type="checkbox" name="pid" value="${l.pid}"
                            <c:forEach items="${rpList}" var="rp">
                                <c:if test="${rp.p_id==l.pid}">
                                    checked="checked"
                                </c:if>
                            </c:forEach>
                    />
                </td>
                <td>${l.pid}</td>
                <td>${l.permission_name}</td>
                <td>${l.permissionurl}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="4" style="text-align: center"><input type="submit" value="确定"></td>
        </tr>
        </tbody>
    </table>
</form>
</div>
</body>
</html>
