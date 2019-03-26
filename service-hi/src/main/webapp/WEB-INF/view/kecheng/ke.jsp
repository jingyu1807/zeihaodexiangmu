<%--
  Created by IntelliJ IDEA.
  User: zbq
  Date: 2019/3/14
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/view/common/base.jsp"%>
</head>
<body>
<!-- 定义表格 -->
<table id="myTable"></table>
<!-- 工具栏 -->
<div id="toolbar">

    <a href="javascript:openDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>

</div>
<!--新增弹框-->
<div id="myDialog" class="easyui-dialog" data-options="iconCls:'icon-save',closed:true,width:300,height:400,buttons:'#buttons'">
    <form   method="post" id="myForm" >
        <input style="display:none" name="kid">
        <table>
            <tr>
                <td>课程名称</td>
                <td>
                    <input class="easyui-textbox" name="kname">
                </td>
            </tr>
            <tr>
                <td>课程图片</td>
                <td>
                    <input type="file" name="file">
                    <input class="easyui-textbox" name="kimg" >
                </td>
            </tr>
            <tr>
                <td>课程内容</td>
                <td>
                    <input type="easyui-textbox" name="kinfo">
                </td>
            </tr>
            <tr>
                <td>vip</td>
                <td>
                    <input type="radio" value="1" name="kvip">vip
                    <input type="radio" value="2" name="kvip">非vip
                </td>
            </tr>

        </table>
    </form>
</div>
<!-- 对话框按钮 -->
<div id="buttons">
    <a href="javascript:saveKe()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
</div>
</body>
<script>
    //初始化
    $(function(){
        initmyTable();
    })



    //单个删除
    function deleteKe(id){
        $.messager.confirm("提示","是否确认删除！",function(r){
            if(r){
                $.ajax({
                    url:"${ctx}/ke/deleteKe",
                    type:"post",
                    data:{"id":id},
                    success:function(){
                        //alert("删除成功");
                        $.messager.alert("提示消息","删除成功！","info");
                        //刷新页面
                        $("#myTable").datagrid("load");
                    },error:function(){
                        //alert("删除失败");
                        $.messager.alert("提示消息","删除失败！","error");
                    }
                })
            }
        })
    }

    //打开修改弹框--回显
    function openUpdate(id){

        $.ajax({
            url:"${ctx}/ke/queryById",
            type:"post",
            data:{"id":id},
            success:function(data){

                //回显数据
                $("#myForm").form("load",data);
                $("#myDialog").dialog({
                    title:"修改课程",
                    closed:false
                })
            }
        })
    }


    //新增修改
    function saveKe(){
        $("#myForm").form("submit",{
            url:"${ctx}/ke/saveKe",
            success:function(){
                $.messager.alert("提示","保存成功","info");
                closeDialog();
                $("#myTable").datagrid("load");
            }
        })
    }

    //关闭弹框
    function closeDialog(){
        $("#myDialog").dialog("close");
    }

    //打开新增弹框
    function openDialog(){
        $("#myForm").form("reset");
        $("#myDialog").dialog({
            title:"新增课程",
            closed:false
        })
    }

    //初始化表格
    function initmyTable(){
        $("#myTable").datagrid({
            url:"${ctx}/ke/queryKe",
            title:"课程",
            fit:true,
            pagination:true,
            toolbar:"#toolbar",
            columns:[[
                {field:"check",checkbox:true},
                {field:"kid",title:"id"},
                {field:"kname",title:"课程名称"},
                {field:"kinfo",title:"课程内容"},
                {field:"kvip",title:"是否vip",formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else if (value == 2){
                        return "不是";
                    }else if(value == ""){
                        return "";
                    }

                }},
                {field:"kimg",title:"图片",formatter:function(value,row,index){
                    return "<img src="+row.kimg+" width='60px' height='60px' >";
                }},
                {field:"tools",title:"操作",formatter:function(value,row,index){
                    return "<a href='javascript:openUpdate("+row.kid+")'>修改</a>"+"||<a href='javascript:deleteKe("+row.kid+")'>删除</a>";
                }}
            ]]
        })
    }

</script>
</html>