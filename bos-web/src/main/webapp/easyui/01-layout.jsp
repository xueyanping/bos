<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>

</head>
<body class="easyui-layout">
	<div title="头部标题" data-options= "region:'north'" style="height: 200px">北</div>
	<div title="系统菜单" data-options= "region:'west'" style="width: 200px">西</div>
	<div  data-options= "region:'center'" >中</div>
	<div data-options= "region:'east'" style="width: 100px">东</div>
	<div data-options= "region:'south'" style="height: 50px">南</div>
	
</body>
</html>