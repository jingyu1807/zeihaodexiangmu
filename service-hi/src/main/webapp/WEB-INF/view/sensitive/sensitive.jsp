<%--
  Created by IntelliJ IDEA.
  User: zbq
  Date: 2019/3/20
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/view/common/base.jsp" %>
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
        <input style="display:none" name="id" >
        <table>
            <tr>
                <td>敏感词</td>
                <td>
                    <input class="easyui-textbox" name="code">
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <input type="radio" name="state" value="1">敏感词
                    <input type="radio" name="state" value="2">非敏感词
                </td>
            </tr>

        </table>
    </form>
</div>
<!-- 对话框按钮 -->
<div id="buttons">
    <a href="javascript:saveSensitive()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
</div>
</body>
<script>
    //初始化
    $(function(){
        initmyTable();
    })


    //单个删除
    function deleteSensitive(id){
        $.messager.confirm("提示","是否确认删除！",function(r){
            if(r){
                $.ajax({
                    url:"${ctx}/sensitive/deleteSensitive",
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
            url:"${ctx}/sensitive/queryById",
            type:"post",
            data:{"id":id},
            success:function(data){

                //回显数据
                $("#myForm").form("load",data);
                $("#myDialog").dialog({
                    title:"修改敏感词",
                    closed:false
                })
            }
        })
    }


    //新增修改
    function saveSensitive(){
        $("#myForm").form("submit",{
            url:"${ctx}/sensitive/saveSensitive",
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
            title:"新增敏感词",
            closed:false
        })
    }

    //初始化用户表格
    function initmyTable(){
        $("#myTable").datagrid({
            url:"${ctx}/sensitive/querySensitive",
            title:"敏感词",
            fit:true,
            pagination:true,
            toolbar:"#toolbar",
            columns:[[
                {field:"check",checkbox:true},
                {field:"id",title:"id"},
                {field:"code",title:"敏感词"},
                {field:"state",title:"状态",formatter:function(value,row,index){
                    if(value == 1){
                        return "敏感词";
                    }else if (value == 2){
                        return "非敏感词";
                    }else if(value == ""){
                        return "";
                    }

                }},
                {field:"tools",title:"操作",formatter:function(value,row,index){
                    return "<a href='javascript:openUpdate("+row.id+")'>修改</a>"+"||<a href='javascript:deleteSensitive("+row.id+")'>删除</a>";
                }}
            ]]
        })
    }

</script>
</html>
