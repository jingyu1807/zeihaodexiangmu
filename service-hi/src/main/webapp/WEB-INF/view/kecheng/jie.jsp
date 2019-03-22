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
        <input style="display:none" name="jid" >
        <table>
            <tr>
                <td>节名称</td>
                <td>
                    <input class="easyui-textbox" name="jname">
                </td>
            </tr>
            <tr>
                <td>所在章</td>
                <td>
                    <input class="easyui-combobox" name="zname" id="zname">
                </td>
            </tr>

        </table>
    </form>
</div>
<!-- 对话框按钮 -->
<div id="buttons">
    <a href="javascript:saveJie()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
</div>
</body>
<script>
    //初始化
    $(function(){
        initmyTable();
        initZhang();
    })

    //初始课程
    function initZhang(){
        $("#zname").combobox({
            url:"${ctx}/jie/queryZhang",
            valueField:"zid",
            textField:"zname"
        })
    }

    //单个删除
    function deleteJie(id){
        $.messager.confirm("提示","是否确认删除！",function(r){
            if(r){
                $.ajax({
                    url:"${ctx}/jie/deleteJie",
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
            url:"${ctx}/Jie/queryById",
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
    function saveJie(){
        $("#myForm").form("submit",{
            url:"${ctx}/jie/saveJie",
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
            title:"新增小节",
            closed:false
        })
    }

    //初始化用户表格
    function initmyTable(){
        $("#myTable").datagrid({
            url:"${ctx}/jie/queryJie",
            title:"小节",
            fit:true,
            pagination:true,
            toolbar:"#toolbar",
            columns:[[
                {field:"check",checkbox:true},
                {field:"jid",title:"id"},
                {field:"jname",title:"小节名称"},
                {field:"zname",title:"所在章"},   
                {field:"ynvip",title:"是否vip",formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else if (value == 2){
                        return "不是";
                    }else if(value == ""){
                        return "";
                    }

                }},
                {field:"tools",title:"操作",formatter:function(value,row,index){
                    return "<a href='javascript:openUpdate("+row.jid+")'>修改</a>"+"||<a href='javascript:deleteJie("+row.jid+")'>删除</a>";
                }}
            ]]
        })
    }

</script>
</html>
