<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/6/3
  Time: 9:07
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
</head>
<body>




<table id="mytable">
</table>
<!--//修改产品的模态框-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">$times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改角色权限</h4>
            </div>
            <div class="modal-body">
                <form id="myform">
                    用户名称：<input type="text" class="form-control" name="empid" id="p_empid" readonly="readonly"/>
                    用户名称：<input type="text" class="form-control" name="username" id="p_username"/>
                    <%--密码：<input type="password" class="form-control" name="password" id="p_password"/>--%>
                    联系电话：<input type="text" class="form-control" name="tel" id="p_tel"/>
                    真实姓名：<input type="text" class="form-control" name="name" id="p_name"/>
                    电子邮箱：<input type="text" class="form-control" name="email" id="p_email"/>

                </form>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
            <button type="button" class="btn btn-primary" onclick="doEidt()">确认修改</button>
        </div>
    </div>
</div>
</div>
<script>

    $(function() {
        var t = new Tableinit();
        t.init();
    })
    var Tableinit = function() {
        var oTableinit=new Object();
        oTableinit.init = function() {
            $("#mytable").bootstrapTable({
                //请求后台的URL
                url: '${pageContext.request.contextPath}/searchAllEmployeesByPage',
                method : 'post',//请求方式
                contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                //隔行变色
                striped : true,
                height: $(window).height() - 200,
                //工具按钮用那个容器
                //toolbar:'toolbar'
                cache : false,//是都使用缓存，默认为true，所有一般情况下需要设置一下这个属性
                pagination : true,//是否显示分页
                //传递参数
                queryParams :oTableinit.queryParams,
                sidePagination : "server",//分页方式
                /*  sortble:true,//是否启用排序
                sortOrder:"asc",//排序方式  */
                pageNumber : 1,//初始化加载第一页
                pageSize : 5,
                pageList : [ 10, 25, 50, 100 ],//选择分页的行数
                //search:true,是否显示表格搜索，
                showColumns : true,//是否显示所有的列
                showRefresh : true,//是否显示刷新按钮
                minimumCountColumns :2,//最少允许的列数
                clickToSelect : true,//是否启用点击选中行
                uniqueId : "ID",
                //showToggle:true,
                //carView:false,
                //detailView:false,
                columns : [{
                    checkbox : true
                }, {
                    field : 'empid',
                    title : '员工ID'
                }, {
                    field : 'username',
                    title : '用户名称'
                },
                    /*{
                    field : 'password',
                    title : '密码'
                }, */
                    {
                        field : 'tel',
                        title : '联系电话'
                    }, {
                        field : 'name',
                        title : '姓名'
                    }, {
                        field : 'email',
                        title : '电子邮箱'
                    },{
                        field : 'empid',
                        title : '操作',
                        formatter:actionFormatter
                    }]
            });
        };
        //得到查询的参数
        oTableinit.queryParams = function (params) {
            var temp = {
                limit : params.limit,//页大小
                offset :params.offset,//页码
            };
            return temp;
        };
        return oTableinit;
    };

    function actionFormatter(value,row,index){
        return "<a href='addRoleByEmployee?empid="+value+"' >"
            +"<span class='glyphicon glyphicon-pencil'></span>"+
            "</a>";
    }

    function searchProduct(){
        $("#mytable").bootstrapTable('refresh');
    }







</script>
</body>
</html>