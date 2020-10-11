<%@ page import="com.whvcse.pojo.Role" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/6/7
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%
    /*ArrayList<RoleEmp> roleList=(ArrayList<RoleEmp>) request.getAttribute("roleEmpList");*/
%>--%>
<html>
<head>
    <title>分配角色</title>
    <style>
        table{
            margin-top: 60px;
        }
        th{
            width:168px;
            height: 45px;
            text-align: center;
        }
        td{
            text-align: center;
            height: 45px;
        }
    </style>
</head>
<body>

    <form action="${pageContext.request.contextPath}/addRoleToEmployee" method="post">
        <table border="1">
            <tr><input type="text" value="${empid}" name="empid" hidden/></tr>
            <tr>
                <th>全选<input type="checkbox"></th>
                <th>ID</th>
                <th>角色名称</th>
            </tr>
            <tbody>
              <c:forEach items="${reList}" var="re">
                   <tr>
                        <td>
                            <input type="checkbox" name="rid" value="${re.rid}"
                       <c:forEach items="${roleEmpList}" var="relist">
                                <c:if test="${relist.r_id==re.rid}">
                                    checked="checked"
                                </c:if>
                            </c:forEach>
                            />
                        </td>
                        <td>${re.rid}</td>
                        <td>${re.rolename}</td>
                    </tr>
              </c:forEach>
            <tr>
                <td colspan="3"><input type="submit" value="确定"></td>
            </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
