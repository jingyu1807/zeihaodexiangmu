<%--
  Created by IntelliJ IDEA.
  User: zbq
  Date: 2019/3/14
  Time: 13:39
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
        <input style="display:none" name="mid" >
        <table>
            <tr>
                <td>目录名称</td>
                <td>
                    <input class="easyui-textbox" name="mname">
                </td>
            </tr>
            <tr>
                <td>所在课程</td>
                <td>
                    <input class="easyui-combobox" name="kname" id="kname">
                </td>
            </tr>

        </table>
    </form>
</div>
<!-- 对话框按钮 -->
<div id="buttons">
    <a href="javascript:saveMu()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
</div>
</body>
<script>
    //初始化
    $(function(){
        initmyTable();
        initKe();
    })

    //初始课程
    function initKe(){
        $("#kname").combobox({
            url:"${ctx}/mu/queryKe",
            valueField:"kid",
            textField:"kname"
        })
    }

    //单个删除
    function deleteMu(id){
        $.messager.confirm("提示","是否确认删除！",function(r){
            if(r){
                $.ajax({
                    url:"${ctx}/mu/deleteMu",
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
            url:"${ctx}/mu/queryById",
            type:"post",
            data:{"id":id},
            success:function(data){

                //回显数据
                $("#myForm").form("load",data);
                $("#myDialog").dialog({
                    title:"修改目录",
                    closed:false
                })
            }
        })
    }


    //新增修改
    function saveMu(){
        $("#myForm").form("submit",{
            url:"${ctx}/mu/saveMu",
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
            title:"新增目录",
            closed:false
        })
    }

    //初始化表格
    function initmyTable(){
        $("#myTable").datagrid({
            url:"${ctx}/mu/queryMu",
            title:"目录",
            fit:true,
            pagination:true,
            toolbar:"#toolbar",
            columns:[[
                {field:"check",checkbox:true},
                {field:"mid",title:"id"},
                {field:"mname",title:"目录名称"},
                {field:"kname",title:"所在课程"},
                {field:"tools",title:"操作",formatter:function(value,row,index){
                    return "<a href='javascript:openUpdate("+row.mid+")'>修改</a>"+"||<a href='javascript:deleteMu("+row.mid+")'>删除</a>";
                }}
            ]]
        })
    }

</script>
</html>
