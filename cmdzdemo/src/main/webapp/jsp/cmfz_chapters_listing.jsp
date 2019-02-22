<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/6 0006
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min1.3.5.js"></script>
<link href="${pageContext.request.contextPath}/themes1.3.5/icon.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css">

<script>
    $(function () {

        $('#tt').treegrid({
            onDblClickRow:function (row) {
                // 1.使用双击方法  能够获取被双击行的信息 例如拿到链接
                // console.log(row);
                if (row.audioUrl != null) {
                    $("#audio_dd").dialog("open");
                    // alert("进来了");
                    // 2.打开一个对话框 对话框中只有audio标签  更改标签的src属性为音频url
                    $("#audio_id").prop("src","${pageContext.request.contextPath}/"+row.audioUrl);
                }else {
                    getAlbum();
                }
            },

            url:'${pageContext.request.contextPath}/audio/getAll',
            idField:'id',
            treeField:'name',
            method:"get",
            columns:[[
                {field:'name',title:'音频名称'},
                {field:'audioSize',title:'大小'}
            ]],
            toolbar: [{
                text:"专辑详情",
                iconCls: 'icon-edit',
                handler: function(){
                    getAlbum();
                }
            },'-',{
                text:"添加专辑",
                iconCls: 'icon-help',
                handler: function(){
                    openAddDialog();
                }
            },'-',{
                text:"添加音频",
                iconCls: 'icon-help',
                handler: function(){
                    openADdAudioDialog();
                }
            }
            ],

        });
    });

    function getAlbum() {
        var root=$("#tt").treegrid("getSelected");
        console.log(root);
        $("#dd").dialog("open");

        $("#name").html(root.name);
        $("#star").html(root.albumStar);
        $("#author").html(root.albumAuthor);
        $("#albumTeller").html(root.albumTeller);
        $("#episodes").html(root.albumEpisodes);
        $("#albumDate").html(root.albumDate);
        $("#albumContent").html(root.albumContent);
        $("#img").prop("src", "http://localhost:8081/shouye"+root.albumImage);
    }

    function openAddDialog() {
        var row =$("#tt").treegrid("getSelected");
        // console.log(row.children==0);
        $("#addDiv").dialog("open");

    }
    function addForm() {
        $("#addForm").form("submit",{
            url:"${pageContext.request.contextPath}/audio/add",
            success:function (data) {
                var jsObj = JSON.parse(data);
                if (jsObj) {
                    $.messager.alert("提示", "添加成功", "info");
                    $("#addDiv").dialog("close");
                    $("#tt").treegrid("reload");
                }else {
                    $.messager.alert("提示", "添加失败", "warning");
                }
            }
        })
    }

    function openADdAudioDialog() {
        $("#addAudioDiv").dialog("open");
        $.ajax({
            url:"${pageContext.request.contextPath}/audio/getAll",
            dataType:"json",
            success:function (data) {
                // console.log(data);
                var row =$("#tt").treegrid("getSelected");
                $("#albumName").empty();
                $.each(data,function (index, first) {
                    // console.log(first);
                    if (row==null|| row.id != first.id) {
                        $("#albumName").append("<option value='"+first.id+"'>"+first.name+"</option>");
                    }else {
                        $("#albumName").append("<option value='"+first.id+"' selected>"+first.name+"</option>");
                    }

                });
            }
        });
    }

    function addAudioForm() {
        $("#addAudioForm").form("submit",{
            url:"${pageContext.request.contextPath}/audio/addAudio",
            success:function (data) {
                var jsObj = JSON.parse(data);
                // alert(data);
                if (jsObj) {
                    $("#addAudioDiv").dialog("close");
                    $.messager.alert("提示", "添加成功", "info");
                    $("#tt").treegrid("reload");
                }else {
                    $.messager.alert("提示", "添加失败", "warning");
                }

            }
        })


    }

</script>


<table id="tt" style="width:auto;height:auto"></table>
<div id="dd" class="easyui-dialog" title="专辑详情" style="width:600px;height:450px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <table>
        <tr>
            <td rowspan="6"><img id="img" src="" width="200"/> </td>
            <td>专辑名称:<span id="name"></span> </td>
        </tr>
        <tr>
            <td>星级：<span id="star"></span></td>
        </tr>
        <tr>

            <td>作者：<span id="author"></span></td>
        </tr>
        <tr>

            <td>播音：<span id="albumTeller"></span></td>
        </tr>
        <tr>
            <td>播音员：<span id="episodes"></span></td>
        </tr>
        <tr>
            <td>创建时间：<span id="albumDate"></span></td>
        </tr>
        <hr/>
        <tr>
            <td colspan="2">内容简介：<span id="albumContent"></span></td>
        </tr>

    </table>
</div>

<div id="audio_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <audio id="audio_id" src="" autoplay="autoplay" controls="controls"></audio>
</div>

<div id="addDiv"   class="easyui-dialog" title="添加专辑" style="width:350px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="addForm" method="post" enctype="multipart/form-data">
        专辑名称：<input type="text" name="albumName"/><br/>
        作者：<input name="albumAuthor" type="text"/><br/>
        发布人：<input type="text" name="albumTeller"><br/>
        集数：<input type="text" name="albumEpisodes"><br/>
        评价等级：
        <select name="albumStart" >
            <option value="1">一星</option>
            <option value="2">二星</option>
            <option value="3">三星</option>
            <option value="4">四星</option>
            <option value="5" selected="selected">五星</option>
        </select><br/>
        专辑封面: <input type="file" name="file"><br/>
        <textarea name="albumContent" rows="5" cols="25">在w3school，你可以找到你所需要的所有的网站建设教程。</textarea><br/>
        <input type="button" onclick="addForm()" value="添加专辑">

    </form>
</div>

<div id="addAudioDiv"   class="easyui-dialog" title="添加专辑" style="width:350px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="addAudioForm" method="post" enctype="multipart/form-data">
        音频名字：
        <input type="text" name="audioName"><br/>
        所属专辑:
        <select name="albumId" id="albumName"></select><br>
        音频：
        <input type="file" name="file"/><br/>
        顺序：
        <input type="text" placeholder="由杜亲自测试，数字越小，排名越靠前" name="audioOrder"><br/>
        <input type="button" onclick="addAudioForm()" value="添加">
    </form>
</div>
