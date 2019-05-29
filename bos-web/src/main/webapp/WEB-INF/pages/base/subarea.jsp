<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理分区</title>
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
<script
	src="${pageContext.request.contextPath }/js/highcharts/highcharts.js"></script>
<script
	src="${pageContext.request.contextPath }/js/highcharts/modules/exporting.js"></script>
<script
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd() {
		$('#addSubareaWindow').window("open");
	}

	function doEdit() {

		var rows = $("#grid").datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert("提示信息", "请选择一条记录", "warning");
			return;
		}

		if (rows.length > 1) {
			$.messager.alert("提示信息", "只能选择一条记录", "warning");
			return;
		}

		//alert(rows[0].id);

		$.post('subareaAction_findSubareaById', {
			'subareaId' : rows[0].id
		}, function(data) {
			if (data != null) {
				$('#editSubareaWindow').window("open");
				$("#editSubareaWindow [name=id]").val(data.id);
				$("#editSubareaWindow [name=addresskey]").val(data.addresskey);
				$("#editSubareaWindow [name=startnum]").val(data.startnum);
				$("#editSubareaWindow [name=endnum]").val(data.endnum);
				var single = data.single;
				var area = data.region.province+""+data.region.city+""+data.region.district;
				
				$("#editSubareaWindow #regionName").combobox("setValue",area);
				
				$("#editSubareaWindow [name=position]").val(data.position);
				
					$("#single").combobox("setValue",data.single);
			}

		});

	}

	function doDelete() {
		var rows = $("#grid").datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert("提示信息", "请选择一条记录", "warning");
			return;
		}

		if (rows.length > 1) {
			$.messager.alert("提示信息", "只能选择一条记录", "warning");
			return;
		}
		
		window.location.href="subareaAction_delete?subareaId="+rows[0].id;
	}

	function doSearch() {
		$('#searchWindow').window("open");
	}

	function doShowHighchart() {
		$("#highchartWindow").window("open");
		$.post("subareaAction_showHighchart.action", function(data) {
			$("#showHighchart").highcharts({
				title : {
					text : '区域分区分布图'
				},
				series : [ {
					type : 'pie',
					name : '区域分区分布图',
					data : data
				} ]
			});
		});
	}

	//导出按钮事件
	function doExport() {
		//发送请求,请求action,下载文件
		//为了弹出保存对话框需要刷新页面，所以不可以用ajax请求
		window.location.href = "subareaAction_eportXls.action";
	}
	//导入按钮事件
	/*  function doImport(){
		
		$("#button-import").upload({			
			action:"subareaAction_importXls.action",
			name:'subareaFile'
		});
		
	} */

	//工具栏
	var toolbar = [ {
		id : 'button-search',
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo'
	//handler :doImport
	}, {
		id : 'button-export',
		text : '导出',
		iconCls : 'icon-undo',
		handler : doExport
	}, {
		id : 'button-highchart',
		text : '可视化数据',
		iconCls : 'icon-search',
		handler : doShowHighchart
	} ];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'showid',
		title : '分拣编号',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			return row.id;
		}
	}, {
		field : 'province',
		title : '省',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			return row.region.province;
		}
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			return row.region.city;
		}
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			return row.region.district;
		}
	}, {
		field : 'addresskey',
		title : '关键字',
		width : 120,
		align : 'center'
	}, {
		field : 'startnum',
		title : '起始号',
		width : 100,
		align : 'center'
	}, {
		field : 'endnum',
		title : '终止号',
		width : 100,
		align : 'center'
	}, {
		field : 'single',
		title : '单双号',
		width : 100,
		align : 'center'
	}, {
		field : 'position',
		title : '位置',
		width : 200,
		align : 'center'
	} ] ];

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 收派标准数据表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList : [ 30, 50, 100 ],
			pagination : true,
			toolbar : toolbar,
			url : "subareaAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		$("#button-import").upload({
			action : "subareaAction_importXls.action",
			name : 'subareaFile'
		});

		// 添加分区
		$('#addSubareaWindow').window({
			title : '添加分区',
			width : 600,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});
		//修改分区
		$('#editSubareaWindow').window({
			title : '修改分区',
			width : 600,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});

		// 查询分区
		$('#searchWindow').window({
			title : '查询分区',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});

		// 展示图表
		$('#highchartWindow').window({
			title : '区域分区分布图',
			width : 800,
			modal : true,
			shadow : true,
			closed : true,
			height : 500,
			resizable : false
		});

		//将表单序列化为json对象
		$.fn.serializeJson = function() {
			var serializeObj = {};
			var array = this.serializeArray();
			$(array).each(
					function() {
						if (serializeObj[this.name]) {
							if ($.isArray(serializeObj[this.name])) {
								serializeObj[this.name].push(this.value);
							} else {
								serializeObj[this.name] = [
										serializeObj[this.name], this.value ];
							}
						} else {
							serializeObj[this.name] = this.value;
						}
					});
			return serializeObj;
		};

		//按条件查询分区		
		$("#btn").click(function() {
			var p = $("#searchForm").serializeJson();
			console.info(p);
			$("#grid").datagrid("load", p);
			$("#searchWindow").window("close");
		});

	});

	function doDblClickRow(rowIndex, rowData) {
		//alert("双击表格数据...");
		$("#addSubareaWindow").window("open");
		$("#addSubareaForm").form("load", rowData);
	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<!-- 添加 分区 -->
	<div class="easyui-window" title="分区添加" id="addSubareaWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div style="height: 31px; overflow: hidden;" split="false"
			border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
				<script type="text/javascript">
					$(function() {
						$("#save").click(function() {
							var r = $("#addSubareaForm").form("validate");
							if (r) {
								$("#addSubareaForm").submit();
							}
						});
					});
				</script>
			</div>
		</div>

		<div style="overflow: auto; padding: 5px;" border="false">
			<form id="addSubareaForm" method="post"
				action="subareaAction_add.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					<!-- <tr>
						<td>分拣编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> -->
					<tr>
						<td>选择区域</td>
						<td><input class="easyui-combobox" name="region.id" id="regionInfo"
							data-options="valueField:'id',textField:'name',mode:'remote',url:'regionAction_listajax.action'" />
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td><select class="easyui-combobox" name="single"
							style="width: 150px;">
								<option value="0">单双号</option>
								<option value="1">单号</option>
								<option value="2">双号</option>
						</select></td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position"
							class="easyui-validatebox" required="true" style="width: 250px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="easyui-window" title="分区添加" id="editSubareaWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div style="height: 31px; overflow: hidden;" split="false"
			border="false">
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
				<script type="text/javascript">
					$(function() {
						$("#edit").click(function() {							
							 var r = $("#editSubareaForm").form("validate");
							//alert(r);
							if (r) {
								$("#editSubareaForm").submit();
							}
						});
						
					});
				</script>
			</div>
		</div>
<!-- 修改 -->
		<div style="overflow: auto; padding: 5px;" border="false">
			<form id="editSubareaForm" method="post"
				action="subareaAction_edit.action">
				<input type="hidden" name="id"> 
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					<!-- <tr>
						<td>分拣编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> -->
					<tr>
						<td>选择区域</td>
						<td><input class="easyui-combobox" name="region.id" id="regionName"
							data-options="valueField:'id',textField:'name',mode:'remote',url:'regionAction_listajax.action'" />
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td><select class="easyui-combobox" name="single" id="single"
							style="width: 150px;">
								<option value="0">单双号</option>
								<option value="1">单号</option>
								<option value="2">双号</option>
						</select></td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position"
							class="easyui-validatebox" required="true" style="width: 250px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 查询分区 -->
	<div class="easyui-window" title="分区查询" id="searchWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div style="overflow: auto; padding: 5px;" border="false">
			<form id="searchForm">			
				<table class="table-edit" width="80%" align="center">				
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="region.province" /></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="region.city" /></td>
					</tr>
					<tr>
						<td>区（县）</td>
						<td><input type="text" name="region.district" /></td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" /></td>
					</tr>
					<tr>
						<td colspan="2"><a id="btn" href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<!-- 图表展示 -->
	<div class="easyui-window" title="区域分区分布图" id="highchartWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div id="showHighchart" split="false" border="false"></div>
	</div>

</body>
</html>