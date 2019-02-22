<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>科室管理</title>
    <script type="text/javascript" src="js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min1.3.5.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>

    <link href="themes1.3.5/icon.css" rel="stylesheet" type="text/css">
    <link href="themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function () {
            var did = getUrlParam("did");
            $("#triageTable").datagrid({
                url: "triage/getByDid",
                queryParams: {"did": did},
                method: "get",
                pagination: true,
                columns: [[
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
                    {title: "出生年月日", field: "birth"},
                    {
                        title: "状态", field: "status", formatter: function (value, rowData, index) {
                            if (value == 0) {
                                return "等待诊断";
                            } else if (value == 1) {
                                return "诊断中";
                            } else {
                                return "结束诊断";
                            }
                        }
                    },
                    {
                        title: "操作", field: "Dwad", formatter: function (value, rowData, index) {
                            var data = JSON.stringify(rowData);
                            return "<a href='javascript:void(0)' onclick='call(" + data + ")'>叫号</a>&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='diagnosis(" + data + ")'>诊断</a>"
                        }
                    }

                ]]
            })

            $("#diagnosisDiv").dialog({
                width: 400,
                height: 400,
                resizable: true,
                closed: true
            })

            $("#diagnosisButton").click(function () {
                $("#diagnosisForm").form("submit", {
                    url: "triage/update",
                    success: function (data) {
                        alert("诊断完成");
                        $("#diagnosisDiv").dialog("close");
                        $("#triageTable").datagrid("reload");
                    }
                })
            })

        })

        //叫号
        function call(data) {
            $.ajax({
                url: "triage/call",
                data: {"id": data.id},
                success: function (data) {
                    alert("已叫号");
                    $("#triageTable").datagrid("load");
                }
            })
        }

        //诊断查询
        function diagnosis(rowData) {
            $.ajax({
                url: "triage/getById",
                data: {"id": rowData.id},
                success: function (data) {
                    $("#tid").val(rowData.id);
                    $("#pid").html(data.pid);
                    $("#pname").html(data.pname);
                    $("#did").html(data.did);
                    $("#dname").html(data.dname);
                    $("#uid").html(data.uid);
                    $("#uname").html(data.uname);
                    $("#diagnosisDiv").dialog("open")
                }
            })
        }

        //获取超链接参数
        function getUrlParam(name) {
            // alert(name);
            var url = window.location.href;
            // alert(url);
            // alert(url.indexOf("?"));
            if (url != null && url.indexOf("?") != -1) {
                var url_param = url.split("?")[1];
                var url_param_arr = url_param.split("&");
                for (var i = 0; i < url_param_arr.length; i++) {
                    var tempParam = url_param_arr[i];
                    var paramName = tempParam.split("=")[0];
                    // alert(paramName);
                    if (paramName == name) {
                        return tempParam.split("=")[1]
                    }
                }
            }
            return null;
        }

        var goEasy = new GoEasy({
            appkey: "BS-125c5b9532aa42ecafc4572eac7eb412"
        });

        goEasy.subscribe({
            channel: "cmfzChannel",
            onMessage: function (message) {
                var data = JSON.parse(message.content);
                // alert(data);
            }
        })

    </script>
</head>
<body>
<table id="triageTable"></table>

<div id="diagnosisDiv">
    <form method="post" id="diagnosisForm">
        <input type="hidden" name="id" id="tid">
        <p>患者编号：<span id="pid"></span></p>
        <p>患者姓名：<span id="pname"></span></p>
        <p>科室编号：<span id="did"></span></p>
        <p>科室名称：<span id="dname"></span></p>
        <p>医生编号：<span id="uid"></span></p>
        <p>以声姓名：<span id="uname"></span></p>
        <p>症状描述：<textarea name="illnessInfo"></textarea></p>
        <p>诊断建议：<textarea name="advice"></textarea></p>
        <a href="javascript:void(0)" id="diagnosisButton">确认诊断</a>
    </form>
</div>
</body>
</html>