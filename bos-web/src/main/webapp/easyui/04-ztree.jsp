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
</head>
<body class="easyui-layout">
	<div title="头部标题" data-options="region:'north'" style="height: 200px">北</div>
	<div title="系统菜单" data-options="region:'west'" style="width: 200px">
		<!-- accordion 折叠面板 -->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 嵌套div 子折叠面板 -->
			<div title="子面板1" data-options="iconCls:'icon-save' ">
		<a class="easyui-linkbutton" id="bt1">动态添加选项卡</a>
		<script type="text/javascript">
			$(function(){
				$("#bt1").click(function(){
					//判断“系统管理”选项卡是否存在
					var e = $("#mytabs").tabs("exists","系统管理");
					if(e){
						//已经存在，选中就可以
						$("#mytabs").tabs("select","系统管理");
					}else{
						//调用tabs对象的add方法动态添加一个选项卡
						$("#mytabs").tabs("add",{
							title:'系统管理',
							iconCls:'icon-edit',
							closable:true,
							content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'
						});
					}
					
				});	 
			});
		</script>
</div>
		
			<div title="子面板2" data-options="iconCls:'icon-edit' ">
			<!-- ztree -->
			<ul id="ztree1" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						/* 动态创建ztree */
						var s = {};
						//构造节点数据
						var zNodes = [{"name":"node1" ,"children":[{"name":"node1_1"}]},{"name":"node2"},{"name":"node3"}];
						$.fn.zTree.init($("#ztree1"),s,zNodes);
					});
				</script>
				
			

</div>
			<div title="子面板3" data-options="iconCls:'icon-cut' ">
				<ul id="ztree2" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						/* 动态创建ztree */
						var s2 = {
								data:{
									simpleData:{
										enable:true
									}
								}
						};
						//构造节点数据
						var zNodes2 = [	{"id":"1","pId":"2","name":"node1" },
										{"id":"2","pId":"3","name":"node2"},
										{"id":"3","pId":"0","name":"node3"}
										];
						$.fn.zTree.init($("#ztree2"),s2,zNodes2);
					});
				</script>
</div>
			<div title="子面板4" data-options="iconCls:'icon-save' ">
			
			<ul id="ztree3" class="ztree"></ul>
				<script type="text/javascript">
				$(function(){
					//页面加载完成后，执行这段代码----动态创建ztree
					var setting3 = {
							data: {
								simpleData: {
									enable: true//使用简单json数据构造ztree节点
								}
							},
							callback: {
								//为ztree节点绑定单击事件
								onClick: function(event, treeId, treeNode){
									if(treeNode.page != undefined){
										//判断选项卡是否已经存在
										var e = $("#mytabs").tabs("exists",treeNode.name);
										if(e){
											//已经存在，选中
											$("#mytabs").tabs("select",treeNode.name);
										}else{
											//动态添加一个选项卡
											$("#mytabs").tabs("add",{
												title:treeNode.name,
												closable:true,
												content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>'
											});
										}
									}
								}
							}
					};
					
					//发送ajax请求，获取json数据
					//jQuery提供 的ajax方法：ajax、post、get、load、getJSON、getScript
					var url = "${pageContext.request.contextPath}/json/menu.json";
					$.post(url,{},function(data){
						//调用API初始化ztree
						$.fn.zTree.init($("#ztree3"), setting3, data);
					},'json');
				});
				</script>
			</div>

		</div>
		
	</div>
	<div data-options="region:'center'">
			<div id="mytabs" class="easyui-tabs" data-options="fit:true">
		<!-- 嵌套div 子折叠面板 -->
		<div  title="子面板1" data-options="iconCls:'icon-save',closable:true ">1</div>
		<div title="子面板2" data-options="iconCls:'icon-edit' ">2</div>
		<div title="子面板3" data-options="iconCls:'icon-cut' ">3</div>
	</div>
	
	</div>
	<div data-options="region:'east'" style="width: 100px">东</div>
	<div data-options="region:'south'" style="height: 50px">南</div>

</body>
</html>