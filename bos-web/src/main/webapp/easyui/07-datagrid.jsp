<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tabs</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
	
<script type="text/javascript"
	 src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">

		

</script>

<body >
	<table class="easyui-datagrid">
		<thead>
			<tr>  
					<!--方式一：为每一列添加d属性  -->      
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>       
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>小明</td>
				<td>9</td>
			</tr>
			<tr>
				<td>002</td>
				<td>小张</td>
				<td>34</td>
			</tr>
		</tbody>
	</table>
	
	<hr/>
	
	<!--方式二：为每一列添加d属性  -->      
	<table class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/json/datagrid_data'">
		<thead>
			<tr>  
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>       
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>小明</td>
				<td>9</td>
			</tr>
			<tr>
				<td>002</td>
				<td>小张</td>
				<td>34</td>
			</tr>
		</tbody>
	</table>
	
	<hr/>
	<!-- 方式三使用easyui提供的API创建数据表格 -->
	<table id="myTable">
	
	</table>
	<script type="text/javascript">
	$(function(){
		$("#myTable").datagrid({
			columns:[[
				{title:'编号',field:'id',checkbox:true},
				{title:'姓名',field:'name'},
				{title:'年龄',field:'age'},
				{title:'地址',field:'address'}
				
			]],
		url:'${pageContext.request.contextPath}/json/datagrid_data.json',
		rownumbers:true,
		singleSelect:true,
		toolbar:[
			{text:'添加',iconCls:'icon-add',
				handler:function(){
					alert(1);
				}},
			{text:'删除',iconCls:'icon-remove'},
			{text:'修改',iconCls:'icon-edit'},
			{text:'查询',iconCls:'icon-search'}
		],
		pagination:true
		});
	});
	</script>
	
</body>
</html>