<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="mainnav">
		<ul>
			<li class="current"><a href="member/after-login.action">首页</a></li>
			<li><a href="other/musicrun.html">音乐跑不停</a></li>
			<li><a href="other/equip.html">跑步装备库</a></li>
			<li><a href="other/guide.html">专业跑步指南</a></li>
			<li><a href="other/bbs.html">跑步论坛</a></li>

		</ul>
		<span></span>
	</div>
</body>
</html>