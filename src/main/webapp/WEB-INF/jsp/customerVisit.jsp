<%@ page import="com.whvcse.pojo.Employees" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/6/3
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link  href="${pageContext.request.contextPath}/css/bootstrap.min.css"  rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!--bootstrap分页所需的插件-->
    <link href="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.css"  rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/My97DatePicker/WdatePicker.js"></script>
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
<%
    Employees employees=(Employees) session.getAttribute("employees");
%>
<input type="text" width="100px" height="300px" style="display:none">
<table border="1" >
    <tr>
        <th >员工id</th>
        <th >用户名称</th>
        <th >真实姓名</th>
        <th >邮件</th>
        <th >联系电话</th>
        <th >操作</th>
    </tr>
    <tr>
        <td><%=employees.getEmpid()%></td>
        <td><%=employees.getUsername()%></td>
        <td><%=employees.getName()%></td>
        <td><%=employees.getEmail()%></td>
        <td><%=employees.getTel()%></td>
        <td><a href="customerVisitDetail?empid=<%=employees.getEmpid()%>">拜访记录管理</a></td>
    </tr>
</table>
</body>
</html>
