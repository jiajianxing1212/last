<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/6 0006
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min1.3.5.js"></script>
    <link href="${pageContext.request.contextPath}/themes1.3.5/icon.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css">

    <script>
        $(function () {
            // console.log(111);
            $("#getUsers").datagrid({
                title: "Message of Drug",
                toolbar: "#myDiv",// 作为datagrid的属性，包在该组件内部
                pagination: true,// 分页
                rownumbers: true,// 显示行序号（索引）
                url: "${pageContext.request.contextPath}/exam/getAllExam",
                // 双击一行数据时（datagrid双击事件），弹出该行的数据，并显示每列的原有内容
                onDblClickRow: function (rowIndex, rowData) {
                    $("#updateUser").dialog("open");
                    $("#updateUserId").val(rowData.id);
                    $("#updateUsername").val(rowData.name);
                    $("#updateSpec").val(rowData.spec);
                    $("#updateUnit").val(rowData.unit);
                    $("#updateProductCompany").val(rowData.productCompany);
                    $("#updatePrice").val(rowData.price);
                    $("#updateSaleStatus").val(rowData.saleStatus);
                },
                pageSize: 5,// 设置首页初始显示条数
                pageList: [5, 10, 15, 20],// 可选择的每页显示的条数
                columns: [[
                    {checkbox: true},// 显示复选框
                    {title: "ID", field: "id"},
                    {
                        title: "NAME", field: "drug1",
                        formatter: function (value, row, index) {
                            return row.drug.name;
                        }
                    },
                    {
                        title: "PRODUCT_COMPANY", field: "drug2",
                        formatter: function (value, row, index) {
                            return row.drug.productCompany;
                        }
                    },
                    {title: "START_TIME", field: "examStartDate"},
                    {title: "END_TIME", field: "examEndDate"},
                    {
                        title: "EXAM_STATUS", field: "examStatus",
                        formatter: function (value, row, index) {
                            // alert(row);
                            if (value == 0) {
                                return "审核未通过";
                            } else if (value == 1) {
                                return "审核中";
                            } else {
                                return "审核通过";
                            }
                        }
                    },
                    {
                        title: "操作", field: "123",
                        // 该属性绑定一个函数，返回值是该title下的列数据
                        formatter: function (value, row, index) {
                            var rr = JSON.stringify(row);
                            return "<a href='#' id='detail' onclick='detail1(" + rr + ",this)'>查看详情</a> <a href='#' onclick='exam(" + rr + ",this)'>审核</a>";
                        }
                    }
                ]]
            });

            $("#addUsers").dialog({
                title: 'add Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });

            $("#getUser").dialog({
                title: 'get Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });
            $("#exam").dialog({
                title: 'exam Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });

            $("#updateUser").dialog({
                title: 'update Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });

        });

        // 查看详情
        function detail1(rr,aa) {
            $("#getUser").dialog({
                closed: false
            });
            var id1 = rr.id;
            console.log(id1);
            $.ajax({
                url: "${pageContext.request.contextPath}/exam/exam",
                data:{"id": id1},
                success:function (data) {
                    alert(data);
                    // alert(data.spec);
                    $("#getUser").dialog("open");
                    $("#getUserId").val(data.drug.id);
                    $("#getUsername").val(data.drug.name);
                    $("#getSpec").val(data.drug.spec);
                    $("#getUnit").val(data.drug.unit);
                    $("#getProductCompany").val(data.drug.productCompany);
                    $("#getPrice").val(data.drug.price);
                    $("#getSaleStatus").val(data.drug.saleStatus);
                }
            });
        }
        // 审核
        function exam(rr,aa) {
            $("#exam").dialog({
                closed: false
            });
            var id1 = rr.id;
            // alert(id1);
            $.ajax({
                url: "${pageContext.request.contextPath}/exam/exam",
                data:{"id": id1},
                success:function (data) {
                    //alert(data);
                    // alert(data.drug.name);
                    // alert(data.drug.spec);
                    $("#exam").dialog("open");
                    $("#getExamId").val(data.id);
                    $("#getDrugname").val(data.drug.name);
                    $("#getCompany").val(data.drug.productCompany);
                }
            })
        }
        function examSubmit() {
            // 点击审核提交按钮以form的形式发送ajax请求
            $("#examForm").form("submit", {
                // 请求添加的controller方法
                url: "${pageContext.request.contextPath}/exam/updateStatus",
                success: function (data) {
                    alert(data);
                    // 进行判断
                    if (data == "true") {
                        // 该方法有四个参数，标题，内容，图标，函数
                        $.messager.alert("tips", "updateStatus success!", "info");
                        $("#exam").dialog("close");
                        $("#getUsers").datagrid("reload");
                    } else {
                        $.messager.alert("tips", "updateStatus failure!", "warning");
                    }
                }
            });
        }

        // 点击搜索按钮进行搜索,重新加载全部数据
        function search() {
            var name = $("#username").val();
            $("#getUsers").datagrid("load", {"name": name})
        }

        // 点击添加按钮，弹出dialog对话框
        function addButton() {
            $("#addUsers").dialog({
                closed: false
            });
        }

        // 点击添加提交按钮，发送添加请求
        function addUser() {
            $("#addUserForm").form("submit", {
                // 请求添加的controller方法
                url: "${pageContext.request.contextPath}/department/add",

                success: function (data) {
                    // 进行判断
                    if (data == "true") {
                        // 该方法有四个参数，标题，内容，图标，函数
                        $.messager.alert("tips", "add success!", "info");
                        $("#addUsers").dialog("close");
                        $("#getUsers").datagrid("reload");
                    } else {
                        $.messager.alert("tips", "add failure!", "warning");
                    }
                }
            })
        }

        // 点击修改提交按钮，发送修改请求
        function updateUser() {
            $("#updateUserForm").form("submit", {
                // 请求update的controller方法
                url: "${pageContext.request.contextPath}/department/update",
                success: function (data) {
                    // 进行判断
                    //alert(0000)
                    if (data == "true") {
                        $.messager.alert("tips", "update success", "info");
                        $("#updateUser").dialog("close");
                        $("#getUsers").datagrid("reload");
                    } else {
                        $.messager.alert("tips", "update failure", "warning");
                    }
                }
            })
        }

        // 删除
        function deleteUser() {
            var selectRows = $("#getUsers").datagrid("getSelections");
            if (selectRows.length == 0) {
                $.messager.alert("tips", "not found message!!!", "warning");
            } else {
                $.messager.confirm("tip", "Delete?Are you sure?", function (result) {
                    if (result) {
                        var selectIds = new Array();
                        for (var i = 0; i < selectRows.length; i++) {
                            selectIds[i] = selectRows[i].id;
                        }
                        // 发送ajax请求
                        $.ajax({
                            url: "${pageContext.request.contextPath}/department/falseRemove",
                            data: "ids=" + selectIds,
                            success: function (data) {
                                if (data) {
                                    $.messager.alert("tips", "delete success!", "info");
                                    $("#getUsers").datagrid("reload");
                                } else {
                                    $.messager.alert("tips", "delete failure!", "warning");
                                }
                            }
                        })
                    }
                })
            }
        }

    </script>
</head>
<body>
<%--展示全部的div--%>
<div id="getUsers">
</div>

<%--展示详情的div--%>
<div id="getUser">
    <input type="hidden" id="getUserId" name="id"><br/>
    name:<input type="text" id="getUsername" name="name"><br/>
    spec:<input type="text" id="getSpec" name="spec"><br/>
    unit:<input type="text" id="getUnit" name="unit"><br/>
    product_company:<input type="text" id="getProductCompany" name="productCompany"><br/>
    price:<input type="text" id="getPrice" name="price"><br/>
    sale_status:<input type="text" id="getSaleStatus" name="saleStatus"><br/>
</div>
<%--审核页面的div--%>
<div id="exam">
    <form id="examForm" method="post">
        <input type="hidden" id="getExamId" name="id"/>
        name:<input type="text" id="getDrugname" name="drug.name"><br/>
        product_company:<input type="text" id="getCompany" name="drug.productCompany"><br/>
        advice:通过<input type="radio" name="examStatus" value="0"/>
        未通过<input type="radio" name="examStatus" value="2"/><br/>
        意见：<textarea name="examAdvice" id="advice"></textarea>
        <input type="button" value="提交审核" onclick="examSubmit()"/>
    </form>
</div>

<div id="myDiv">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addButton()"></a>
    <%--删除数据--%>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteUser()"></a><br/>
    <input type="text" id="username" name="name">
    <input type="button" id="button" value="search" onclick="search()">
</div>


<%--添加数据--%>
<div id="addUsers">
    <form method="post" id="addUserForm">
        name:<input type="text" id="addUsername" name="name"><br/>
        spec:<input type="text" id="" name="spec"><br/>
        unit:<input type="text" id="" name="unit"><br/>
        product_company:<input type="text" id="" name="productCompany"><br/>
        price:<input type="text" id="" name="price"><br/>
        sale_status:<input type="text" id="" name="saleStatus"><br/>
        <input type="button" value="button" id="addButton" onclick="addUser()">
    </form>
</div>

<%--数据修改--%>
<div id="updateUser">
    <form method="post" id="updateUserForm">
        <input type="hidden" id="updateUserId" name="id"><br/>
        name:<input type="text" id="updateUsername" name="name"><br/>
        spec:<input type="text" id="updateSpec" name="spec"><br/>
        unit:<input type="text" id="updateUnit" name="unit"><br/>
        product_company:<input type="text" id="updateProductCompany" name="productCompany"><br/>
        price:<input type="text" id="updatePrice" name="price"><br/>
        sale_status:<input type="text" id="updateSaleStatus" name="saleStatus"><br/>
        <input type="button" value="button" id="updateButton" onclick="updateUser()">
    </form>
</div>
<%--条件查询--%>


</body>
</html>
