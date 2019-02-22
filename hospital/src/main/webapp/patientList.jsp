<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>患者管理</title>
    <script type="text/javascript" src="js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min1.3.5.js"></script>

    <link href="themes1.3.5/icon.css" rel="stylesheet" type="text/css">
    <link href="themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function () {
            $("#patientTable").datagrid({
                url: "patient/getByPage",
                pagination: true,
                toolbar: [{
                    iconCls: 'icon-add',
                    handler: function () {
                        $("#addPatient").dialog("open");
                    }
                }, '-', {
                    iconCls: 'icon-remove',
                    handler: function () {
                        todelete();
                    }
                }],
                columns: [[
                    {checkbox: true,},
                    {title: "编号", field: "id", hidden: true},
                    {title: "姓名", field: "name"},
                    {
                        title: "性别", field: "sex", formatter: function (value, rowData, index) {
                            if (value == 1) {
                                return "男";
                            } else {
                                return "女";
                            }
                        }
                    },
                    {title: "生日", field: "birth"},
                    {title: "身份证", field: "idcard"},
                    {title: "住址", field: "address"},
                    {title: "电话", field: "mobile"},
                    {
                        title: "操作", field: "dawd", formatter: function (value, rowData, index) {
                            var data = JSON.stringify(rowData);
                            return "<a href='javascript:void(0)' onclick='registered(" + data + ")'>挂号</a>&nbsp;&nbsp;&nbsp;<a href='patient/download?id="+rowData.id+"'>下载诊断</a>"
                        }
                    }
                ]],
                onDblClickRow: function (rowIndex, rowData) {
                    toOpenUpdateDialog(rowIndex, rowData);
                }
            })
            //搜索
            $("#searchButton").click(function () {
                $("#patientTable").datagrid("load", {"name": $("#searchInput").val()})
            })
            //添加
            $("#addButton").linkbutton({})
            $("#addPatient").dialog({
                width: 400,
                height: 400,
                resizable: true,
                closed: true
            });
            $("#addButton").click(function () {
                $("#addForm").form("submit", {
                    url: "patient/insert",
                    success: function (data) {
                        alert("添加成功");
                        $("#addPatient").dialog("close");
                        $("#patientTable").datagrid("reload");
                    }
                })
            });
            //修改
            $("#updateButton").linkbutton({});
            $("#updatePatient").dialog({
                width: 400,
                height: 200,
                resizable: true,
                closed: true
            });
            $("#updateButton").click(function () {
                $("#updateForm").form("submit", {
                    url: "patient/update",
                    success: function (data) {
                        alert("修改成功");
                        $("#updatePatient").dialog("close");
                        $("#patientTable").datagrid("reload");
                    }
                })
            })
            //挂号
            $("#registeredButton").linkbutton({})
            $("#registeredDiv").dialog({
                width: 400,
                height: 200,
                resizable: true,
                closed: true
            })
            $("#registeredButton").click(function () {
                $("#registeredForm").form("submit", {
                    url: "triage/insert",
                    success: function (data) {
                        alert("挂号成功");
                        $("#registeredDiv").dialog("close");
                    }
                })
            })

        })

        function toOpenUpdateDialog(rowIndex, rowData) {
            $("#updatePatient").dialog("open");
            $("#id").val(rowData.id);
            $("#name").val(rowData.name);
            if (rowData.sex == 0) {
                $("#sex1").prop("checked", true);
            } else {
                $("#sex2").prop("checked", true);
            }
            $("#birth").val(rowData.birth);
            $("#idcard").val(rowData.idcard);
            $("#address").val(rowData.address);
            $("#mobile").val(rowData.mobile);

        }

        //删除
        function todelete() {
            var selectIds = $("#patientTable").datagrid("getSelections");
            // console.log(selectIds);
            if (selectIds == 0) {
                alert("请选择");
                return false;
            } else {
                var ids = new Array(selectIds.length);
                for (var i = 0; i < ids.length; i++) {
                    ids[i] = selectIds[i].id;
                }
                // console.log(ids);
                $.ajax({
                    url: "patient/deleteSome.do",
                    data: {"ids": ids},
                    traditional: true,
                    success: function (data) {
                        alert("删除成功")
                        $("#patientTable").datagrid("reload");
                    }
                })
            }
        }

        var flag = 0;

        //挂号
        function registered(data) {
            $("#id2").val(data.id);
            $("#name2").html(data.name);
            $.ajax({
                url: "department/getAll",
                success: function (data) {
                    if (flag == 0) {
                        for (var i = 0; i < data.length; i++) {
                            $("#did").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                        }
                    }
                    $("#registeredDiv").dialog("open");
                    flag = 1;
                }
            })
        }


    </script>
</head>
<body>
<input type="text" id="searchInput">
<button id="searchButton">搜索</button>
<table id="patientTable"></table>
<div id="updatePatient">
    <form method="post" id="updateForm">
        <input type="hidden" name="id" id="id">
        患者姓名：<input type="text" name="name" id="name"><br>
        性别：男<input type="radio" name="sex" id="sex1" value="0">女<input type="radio" name="sex" id="sex2" value="1"><br>
        <%--生日：<input type="text" name="birth" id="birth"><br>--%>
        身份证：<input type="text" name="idcard" id="idcard"><br>
        手机号：<input type="text" name="mobile" id="mobile"><br>
        住址：<input type="text" name="address" id="address"><br>
        <a href="javascript:void(0)" id="updateButton">确认修改</a>
    </form>
</div>
<div id="addPatient">
    <form method="post" id="addForm">
        <input type="hidden" name="id">
        患者姓名：<input type="text" name="name"><br>
        性别：男<input type="radio" name="sex" value="0">女<input type="radio" name="sex" value="1"><br>
        <%--生日：<input type="text" name="birth"><br>--%>
        身份证：<input type="text" name="idcard"><br>
        手机号：<input type="text" name="mobile"><br>
        住址：<input type="text" name="address"><br>
        <a href="javascript:void(0)" id="addButton">确认</a>
    </form>
</div>
<div id="registeredDiv">
    <form method="post" id="registeredForm">
        <input type="hidden" id="id2" name="pid">
        <p>患者姓名：<span id="name2"></span></p>
        <p>科室：<select name="did" id="did"></select></p>
        <a href="javascript:void(0)" id="registeredButton">挂号</a>
    </form>
</div>
</body>
</html>