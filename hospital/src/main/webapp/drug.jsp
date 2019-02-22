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
            console.log(111);
            $("#getUsers").datagrid({
                title: "Message of Drug",
                toolbar: "#myDiv",// 作为datagrid的属性，包在该组件内部
                pagination: true,// 分页
                rownumbers:true,// 显示行序号（索引）
                url: "${pageContext.request.contextPath}/drug/getAllDrug",
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
                    {title: "NAME", field: "name"},
                    {title: "SPEC", field: "spec"},
                    {title: "UNIT", field: "unit"},
                    {title: "PRODUCT_COMPANY", field: "productCompany"},
                    {title: "PRICE", field: "price"},
                    {title: "SALE_STATUS", field: "saleStatus"},
                    {
                        title: "操作", field: "aaa",
                        // 该属性绑定一个函数，返回值是该title下的列数据
                        formatter: function (value, rowdata, index) {
                            // 把对象转换成json串
                            var row = JSON.stringify(rowdata);
                            var s;
                            // alert(rowdata.saleStatus);
                            if (rowdata.saleStatus == 1) {
                                s="上架";
                            }else {
                                s="停售";
                            }
                            return "<a href='#' onclick='updateStatus(" + row + ",this)'>"+s+"</a>  <a href='#' onclick='submit("+rowdata.id+")'>提交审核</a>";
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

            $("#updateUser").dialog({
                title: 'update Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });
        });


        function updateStatus(row,aa) {
            alert(row.id);
            $.ajax({
                url: "${pageContext.request.contextPath}/drug/updateStatus",
                data:{"id":row.id,"saleStatus":row.saleStatus},
                success:function (data) {
                    $("#getUsers").datagrid("reload");
                }
            })
           /* id = row.id;
            alert(id);
            status = row.saleStatus;
            alert(aa);
            if (status == 1) {
                status = 0;
                aa.href = "/drug/updateStatus?id="+id+"&saleStatus="+0;
                aa.innerHtml="上架";
            }else {
                aa.href = "/drug/updateStatus?id="+id+"&saleStatus="+1;
                aa.innerHtml="上架";
            }*/
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
                url: "${pageContext.request.contextPath}/drug/add",

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
                url: "${pageContext.request.contextPath}/drug/update",
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
            }else {
                $.messager.confirm("tip","Delete?Are you sure?",function (result) {
                    if (result) {
                        var selectIds = new Array();
                        for (var i = 0; i < selectRows.length; i++) {
                            selectIds[i]=selectRows[i].id;
                        }
                        // 发送ajax请求
                        $.ajax({
                            url: "${pageContext.request.contextPath}/drug/mutilRemove",
                            data: "ids=" + selectIds,
                            success:function (data) {
                                if (data) {
                                    $.messager.alert("tips", "delete success!", "info");
                                    $("#getUsers").datagrid("reload");
                                }else {
                                    $.messager.alert("tips", "delete failure!", "warning");
                                }
                            }
                        })
                    }
                })
            }
        }
        //提交审核
        function submit(id) {
            $.ajax({
                url:"exam/submit",
                data:{"id":id},
                success:function (data) {
                    alert("提交成功");
                }
            })
        }

    </script>
</head>
<body>
    <%--展示全部的div--%>
    <div id="getUsers">
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
