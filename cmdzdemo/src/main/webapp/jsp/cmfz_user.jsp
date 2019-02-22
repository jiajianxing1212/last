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
                title: "Message of Department",
                toolbar: "#myDiv",// 作为datagrid的属性，包在该组件内部
                pagination: true,// 分页
                rownumbers: true,// 显示行序号（索引）
                url: "${pageContext.request.contextPath}/user/getAll",
                // 双击一行数据时（datagrid双击事件），弹出该行的数据，并显示每列的原有内容
                onDblClickRow: function (rowIndex, rowData) {
                    $("#updateUser").dialog("open");
                    $("#updateUserId").val(rowData.userId);
                    $("#updateUsername").val(rowData.telephone);
                    $("#updateTelephone").val(rowData.pass);
                    $("#updateArea").val(rowData.area);
                },
                pageSize: 5,// 设置首页初始显示条数
                pageList: [5, 10, 15, 20],// 可选择的每页显示的条数
                columns: [[
                    {checkbox: true},// 显示复选框
                    {title: "userId", field: "userId"},
                    {title: "telphone", field: "telphone"},
                    {title: "password", field: "password"},
                    {
                        title: "userImage", field: "userImage",
                        formatter: function (value, row, index) {
                            return "<img style='width:30' src=${pageContext.request.contextPath}" + value + "/>";
                        }
                    },
                    {title: "nickname", field: "nickname"},
                    {title: "name", field: "name"},
                    {title: "sex", field: "sex"},
                    {title: "autograph", field: "autograph"},
                    {title: "userProvince", field: "userProvince"},
                    {title: "userCity", field: "userCity"},
                    {
                        title: "userName", field: "guru",
                        formatter: function (value, row, index) {
                            return row.guru.guruName;
                        }
                    },
                    {
                        title: "userImage", field: "guru2",
                        formatter: function (value, row, index) {
                            return "<img style='width:35' src=${pageContext.request.contextPath}" + row.guru.guruImage + "/>";
                        }
                    },
                    {
                        title: "userImage", field: "guru3",
                        formatter: function (value, row, index) {
                            return row.guru.guruNickname;
                        }
                    },
                    {
                        title: "状态", field: "userStatus",
                        formatter: function (value, row, index) {
                            // alert(111);
                            if (row.userStatus == 2) {
                                return "<a href='' style=\"color: #449d44\">正常显示</a>"
                            } else if (row.userStatus == 1) {
                                return "<a href='' style=\"color: #CC2222\">不展示</a>";
                            } else {
                                return "已删除";
                            }
                        }
                    }
                ]],
                /*修改状态 开始*/
                onClickCell:function (rowIndex, field, value) {
                    if (field == "userStatus") {
                        var userId = $(this).datagrid("getRows")[rowIndex].userId;
                        var userStatus = $(this).datagrid("getRows")[rowIndex].userStatus;
                        //console.log(bannerId)
                        //console.log(bannerstatus)
                        if(userStatus==2){
                            userStatus = 1
                        }else {
                            userStatus = 2
                        }
                        $.ajax({
                            url:"${pageContext.request.contextPath}/user/updateStatus",
                            type: "post",
                            data: {"userId":userId,"userStatus":userStatus},
                            success: function (data) {
                                //alert(data == true);
                                if (data) {
                                    $.messager.alert("提示框", "状态修改成功", "info");
                                    $("#getUsers").datagrid("reload");
                                } else {
                                    $.messager.alert("提示框", "状态修改失败", "info");
                                }
                            }
                        })
                    }
                },
                /*修改状态结束*/
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
                url: "${pageContext.request.contextPath}/user/add",

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
                            selectIds[i] = selectRows[i].userId;
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

<div id="myDiv">
    <%--添加按钮--%>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addButton()"></a>
    <%--删除按钮--%>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteUser()"></a><br/>
    <%--条件查询--%>
    <input type="text" id="username" name="name">
    <input type="button" id="button" value="search" onclick="search()">
</div>


<%--添加数据--%>
<div id="addUsers">
    <form method="post" id="addUserForm">
        telephone:<input type="text" id="" name="telephone"><br/>
        password:<input type="text" id="" name="password"><br/>
        user_image:<input type="text" id="" name="userImage"><br/>
        nickname:<input type="text" id="" name="nickname"><br/>
        name:<input type="text" id="" name="name"><br/>
        sex:<input type="text" id="" name="sex"><br/>
        autograph:<input type="text" id="" name="autograph"><br/>
        user_province:<input type="text" id="" name="userProvince"><br/>
        user_city:<input type="text" id="" name="userCity"><br/>
        user_id:<input type="text" id="" name="userId"><br/>
        user_status:<input type="text" id="" name="userStatus"><br/>

        <input type="button" value="button" id="addButton" onclick="addUser()">
    </form>
</div>

<%--数据修改--%>
<div id="updateUser">
    <form method="post" id="updateUserForm">
        <input type="hidden" id="updateUserId" name="id"><br/>
        name:<input type="text" id="updateUsername" name="name"><br/>
        telephone:<input type="text" id="updateTelephone" name="telephone"><br/>
        area:<input type="text" id="updateArea" name="area"><br/>
        <input type="button" value="button" id="updateButton" onclick="updateUser()">
    </form>
</div>


</body>
</html>
