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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>杰普――跑步社区</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<script src="js/main.js" type="text/javascript"></script>

<style type="text/css">
<!--
table {
	border-spacing: 1px;
	border: 1px solid #A2C0DA;
}

td, th {
	padding: 2px 5px;
	border-collapse: collapse;
	text-align: left;
	font-weight: normal;
}

thead tr th {
	background: #B0D1FC;
	border: 1px solid white;
}

thead tr th.line1 {
	background: #D3E5FD;
}

thead tr th.line4 {
	background: #C6C6C6;
}

tbody tr td {
	height: 50px;
	background: #CBE2FB;
	border: 1px solid white;
	vertical-align: top;
}

tbody tr td.line4 {
	background: #D5D6D8;
}

tbody tr th {
	height: 50px;
	background: #DFEDFF;
	border: 1px solid white;
	vertical-align: top;
}

caption, tfoot.th {
	height: 30px;
	border: 0px;
}
-->
</style>

</head>
<body>
	<div id="btm">
		<div id="main">

			<div id="header">
				<div id="top"></div>
				<div id="logo">
					<h1>跑步社区</h1>
				</div>
				<jsp:include page="../member/logout.jsp" flush="true"></jsp:include>
				<jsp:include page="../member/member-header-menu.jsp"></jsp:include>
			</div>

			<jsp:include page="msgBox-menu.jsp" flush="true"></jsp:include>
			<br /> <br />

			<div id="content" align="center">
				<div id="center">
					<form action="result_Flow.htm" method="post" name="form1">
						<table width="500" border="0" style="margin-top: 20px;"
							align="center" cellpadding="0" cellspacing="0">

							<thead>
								<tr>
									<td colspan="2" height="40">
										<h4>消息详细内容</h4>
									</td>
								</tr>

							</thead>
							<tbody>
								<tr>
									<th class="line1" scope="col" width="20%">发信人</th>
									<td>${messagerecord.sender}</td>
								</tr>
								<tr>
									<th>主题</th>
									<td>${messagerecord.title}</td>
								</tr>
								<tr>
									<th>消息内容</th>
									<td>${messagerecord.content}</td>
								</tr>

							</tbody>
							<tfoot>
								<tr>
									<th colspan="2" align="center" classo="line4"
										style="text-align: center"><input type="button"
										value="返回" onclick="self.location='msg/msg-list-all.action'" /></th>
								</tr>
							</tfoot>
						</table>
					</form>
					<br /> <br />
					<jsp:include page="../member/member-footer-menu.jsp"></jsp:include>

				</div>
				<div id="right">
					<h2>&nbsp;</h2>
					<ul>
						<li></li>
					</ul>
				</div>
				<div class="clear"></div>


			</div>

			<div id="footer">
				<div id="copyright">
					<div id="copy">
						<p>CopyRight&copy;2013</p>
						<p>跑步社区(www.irun.com)</p>
					</div>
				</div>
				<div id="bgbottom"></div>
			</div>

		</div>
	</div>
</body>
</html>
