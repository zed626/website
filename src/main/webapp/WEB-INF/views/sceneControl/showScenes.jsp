<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>场景管理</title>
</head>
<body>
	场景总数: ${size}
	<br>
	<br>(1代表当前可用)<br>
	<br>

	<table>
		<tr>
			<th>ID</th><th>名称</th><th>图片数</th><th>是否可用</th><th>&nbsp;</th>
		</tr>
		<c:forEach items="${scenes}" var="scene">
			<tr>
				<td>${scene.s_id}</td>
				<td>${scene.name}</td>
				<td>${scene.number}</td>
				<td>${scene.available}</td>
				<td>
					<a href="<%=request.getContextPath()%>/scene/delete?id=${scene.s_id}">删除</a>
					/
					<a href="<%=request.getContextPath()%>/scene/modify?id=${scene.s_id}">可用</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="<%=request.getContextPath()%>/scene/add">添加场景</a>
</body>
</html>