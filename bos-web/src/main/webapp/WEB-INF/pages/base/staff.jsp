<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	
	
	function doView(){
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length == 0){
			//没有选中记录，弹出提示
			$.messager.alert("提示信息","请选择要编辑的记录","warning");
			return;
		}
			
		if(rows.length>1){
			$.messager.alert("提示信息","只能选中一条记录","warning");
			return;
		}
		
		//alert(rows[0].id)
		$.post('staffAction_view.action',{rowId:rows[0].id},function(data){
			if(data!=null){	
				//alert(data);
				$('#editStaffWindow').window("open");
				var haspda = data.haspda;
				
				if(haspda==1){
				//	alert(haspda);
					$("#editStaffWindow [name=haspda]").prop("checked",true);
				}
				if(haspda==0){
				//	alert(haspda);
					$("#editStaffWindow [name=haspda]").prop("checked",false);
				}
				$("#editStaffWindow [name=name]").val(data.name);
				$("#editStaffWindow [type=hidden]").val(data.id);
				$("#editStaffWindow [name=telephone]").val(data.telephone);
				$("#editStaffWindow [name=station]").val(data.station);
				$("#editStaffWindow [name=standard]").val(data.standard);			
			}
			
		});	
		
	}
	
	function doDelete(){
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length == 0){
			//没有选中记录，弹出提示
			$.messager.alert("提示信息","请选择要删除的记录","warning");
		}else{
			 $.messager.confirm("确认删除","确定要删除吗？",function(r){
				if(r){
					//确定，发送请求
					var array = new Array();
					for(var i = 0;i<rows.length;i++){
						var staff = rows[i];//staff为json对象
						var id = staff.id;
						array.push(id);								
			}
				var ids = array.join(",");
				location.href="staffAction_deleteBanch.action?ids="+ids;
			
				}
			});
		}
	}
	
	function doRestore(){
		//alert("将取派员还原...");
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length == 0){
			//没有选中记录，弹出提示
			$.messager.alert("提示信息","请选择要还原的的记录","warning");
		}else{
			 $.messager.confirm("确认删除","确定要还原吗？",function(r){
				if(r){
					//确定，发送请求
					var array = new Array();
					for(var i = 0;i<rows.length;i++){
						var staff = rows[i];//staff为json对象
						var id = staff.id;
						array.push(id);								
			}
				var ids = array.join(",");
				location.href="staffAction_doBack.action?ids="+ids;
			
				}
			});
		}
		
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '编辑',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},
	
	
	{
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},
	
	{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "staffAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			//为数据表格绑定双击事件
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 添加取派员窗口
		$('#editStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
	});

	function doDblClickRow(rowIndex, rowData){
		//打开修改取派员窗口
		$('#editStaffWindow').window("open");
		//使用form表单对象的load方法回显数据
		$("#editStaffForm").form("load",rowData);
		
		
		
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addStaffForm" action="staffAction_addStaff.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>
						<script type="text/javascript">
						
							$(function(){
								//为保存按钮绑定事件
								
								
								$("#save").click(function(){
									var v = $("#addStaffForm").form("validate");
									if(v){
										$("#addStaffForm").submit();
									}
								});
								
								
								var reg = /^1[3|4|5|7|8][0-9]{9}$/;
								$.extend($.fn.validatebox.defaults.rules,{
									telephone:{
										validator:function(value,param){
											return reg.test(value);
										},
										message:'手机号输入有误'
									}
								});
							});
						
						</script>
						<input type="text" name="telephone" class="easyui-validatebox" data-options="validType:'telephone'" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	<!-- 修改取派员 -->
	<div class="easyui-window" title="对收派员进行添加或者修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" action="staffAction_edit.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
										
					<input type="hidden" name="id" class="easyui-validatebox" required="true"/>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>
						<script type="text/javascript">
						
							$(function(){
								//为保存按钮绑定事件
								
								
								$("#edit").click(function(){
									var v = $("#editStaffForm").form("validate");
									if(v){
										$("#editStaffForm").submit();
									}
								});
								
								
								var reg = /^1[3|4|5|7|8][0-9]{9}$/;
								$.extend($.fn.validatebox.defaults.rules,{
									telephone:{
										validator:function(value,param){
											return reg.test(value);
										},
										message:'手机号输入有误'
									}
								});
							});
						
						</script>
						<input type="text" name="telephone" class="easyui-validatebox" data-options="validType:'telephone'" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	
</body>
</html>	