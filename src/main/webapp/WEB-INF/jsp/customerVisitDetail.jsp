<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/6/3
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<shiro:hasRole name="user">
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">添加客户拜访记录</button>
<!--添加的模态-->
</shiro:hasRole>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">请输入客户拜访记录</h4>
            </div>
            <div class="modal-body">
                <form id="aform">
                    <div class="form-group">
                        员工名称：<select class="form-control" id="a_empid" name="empid">
                                      <option value="0"></option>
                                  </select>
                        客户名称：<select class="form-control" id="a_cid" name="cid">
                                      <option value="0">请选择客户</option>
                                  </select>

                        拜访内容：<input type="text" class="form-control" name="content" id="a_content"/>
                        时间日期：<input type="text" class="form-control" name="date" id="a_date" onclick="WdatePicker()"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="add()" >确认添加</button>
            </div>
        </div>
    </div>
</div>


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
                <h4 class="modal-title" id="myModalLabel">修改客户拜访记录</h4>
            </div>
            <div class="modal-body">
                <form id="myform">
                    拜访客户ID：<input type="text" class="form-control" name="id" id="id" readonly="readonly"/>
                    员工名称：   <select class="form-control" id="empid" name="empid">
                                    <option value="0">请选择员工</option>
                                </select>
                    客户名称：<select class="form-control" id="cid" name="cid">
                                 <option value="0">请选择客户</option>
                              </select>

                    拜访内容：<input type="text" class="form-control" name="content" id="content"/>
                    时间日期：<input type="text" class="form-control" name="date" id="date" onclick="WdatePicker()"/>

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
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    $(function() {
        var t = new Tableinit();
        t.init();
        getBorth();
        selected();
    })
    var Tableinit = function() {
        var oTableinit=new Object();
        oTableinit.init = function() {
            $("#mytable").bootstrapTable({
                //请求后台的URL
                url: '${pageContext.request.contextPath}/searchAllCustomersVisitByPage',
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
                    field : 'id',
                    title : '拜访客户ID'
                }, {
                    field : 'employees.username',
                    title : '姓名'
                }, {
                    field : 'customer.cusName',
                    title : '客户名称'
                }, {
                    field : 'content',
                    title : '内容'
                }, {
                    field : 'date',
                    title : '日期'
                },{
                    field : 'cid',
                    title : '客户编号'
                },{
                    field : 'empid',
                    title : '员工编号'
                }
                    <shiro:hasRole name="user">
                    ,{
                    field : 'id',
                    title : '操作',
                    formatter:actionFormatter
                     }
                    </shiro:hasRole>
                ]
            });
        };
        //得到查询的参数
        oTableinit.queryParams = function (params) {
            var temp = {
                limit : params.limit,//页大小
                offset :params.offset,//页码
                empid:  ${employees.empid}
            };
            return temp;
        };
        return oTableinit;
    };

    function actionFormatter(value,row,index){
        return "<a href='#' onclick='edit("+value+")' data-toggle='modal' data-target='#myModal'>"
            +"<span class='glyphicon glyphicon-pencil'></span>"+
            "</a>"
            +"&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<a href='#' onclick='del("+value+")'>"
            +"<span class='glyphicon glyphicon-remove'></span></a>";
    }
    function selected(){
        customerAndEmployees(${employees.empid},$("#a_empid")[0]);
    }

    function searchProduct(){
        $("#mytable").bootstrapTable('refresh');
    }

    function edit(id) {
        $.ajax({
            url : "${pageContext.request.contextPath}/preUpdateCustomerVisit",
            data : {
                "id"    : id
            },
            async :false,
            dataType:"json",
            success : function(p) {
                $("input[id=id]").val(p.id);
                $("input[id=content]").val(p.content);
                $("input[id=date]").val(p.date);
                customerAndEmployees(p.empid,$("#empid")[0]);
                //选中目录
                customerAndEmployees(p.cid,$("#cid")[0]);
            }
        })
    }

    //确认修改
    function doEidt(){
        $.ajax({
            url : "${pageContext.request.contextPath}/updateCustomerVisit",
            data : $("#myform").serialize(),
            success : function(msg) {
                if(msg=="success"){
                    $('#myModal').modal('hide');
                    $('#mytable').bootstrapTable('refresh');
                }
            }
        })
    }
    function add(){
        $.ajax({
            url : "${pageContext.request.contextPath}/insertCustomerVisit",
            data : $("#aform").serialize(),
            success : function(msg) {
                if(msg=="success"){
                    $('#myModal2').modal('hide');
                    $('#myModal2').bootstrapTable('refresh');
                    $('#mytable').bootstrapTable('refresh');
                }
            }
        })
    }


    function del(id) {
        $.ajax({
            url : "${pageContext.request.contextPath}/deleteCustomerVisitById",
            data : {
                "id" : id
            },
            method : 'post',//请求方式
            success :function(msg) {
                if(msg=="success"){
                    $('#mytable').bootstrapTable('refresh');
                }else{
                    alert("该拜访记录不能删除不能删除")
                }
            }
        })
    }
    /*选中角色和权限*/
    //选中供应商和目录
    function customerAndEmployees(id,obj){
        var arr=obj.options;
        for(var i=0; i<arr.length;i++){
            if(arr[i].value == id){
                arr[i].selected=true;
            }
        }
    }

    function getBorth(){
        xmlhttp.open("post","${pageContext.request.contextPath}/selectCustomerAndEmployee?empid=${employees.empid}",false);
        xmlhttp.onreadystatechange = function() {
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                var info = xmlhttp.responseText;
                var arr = JSON.parse(info);
                var cusList=arr[0];
                var empList=arr[1];
                var aempList=arr[2];
                var b_pro = document.getElementById("a_cid");
                var b_cate = document.getElementById("a_empid");
                for (var i = 0; i < cusList.length; i++) {
                    b_pro.add(new Option(cusList[i].cusName,cusList[i].cid));
                }
                    b_cate.add(new Option(aempList.username,aempList.empid));
                //模态框中的数据
                b_pro = document.getElementById("cid");
                b_cate = document.getElementById("empid");
                for (var i = 0; i < cusList.length; i++) {
                    b_pro.add(new Option(cusList[i].cusName,cusList[i].cid));
                }
                for (var i = 0; i < empList.length; i++) {
                    b_cate.add(new Option(empList[i].username,empList[i].empid));
                }

        }
        xmlhttp.send();
    }
</script>
</body>
</html>