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

		$(function(){
		
			//$.messager.alert("title","content","waring");
			/* $.messager.confirm("title","content",function(r){
				alert(r);
			}); */
		$.messager.show({
			title:'title',
			msg:'message',
			timeout:'5000',
			showType:'slide'
		});
		});

</script>

<body >
	

</body>
</html>