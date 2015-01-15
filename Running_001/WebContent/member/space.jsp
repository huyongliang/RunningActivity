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
<title>杰普——跑步社区</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<style type="text/css">
<!--
table {
	border-spacing: 1px;
	border: 1px solid #A2C0DA;
}

td, th {
	padding: 2px 5px;
	border-collapse: collapse;
	text-align: center;
	font-weight: normal;
}

thead tr th {
	background: #FFFFFF;
	border: 1px solid white;
	height: 40px
}

thead tr th.line1 {
	background: #D3E5FD;
}

thead tr th.line4 {
	background: #C6C6C6;
}

tbody tr td {
	height: 35px;
	background: #CBE2FB;
	border: 1px solid white;
	vertical-align: middle;
}

tbody tr td.line4 {
	background: #D5D6D8;
}

tbody tr th {
	height: 35px;
	background: #DFEDFF;
	border: 1px solid white;
	vertical-align: middle;
}

tfoot tr td {
	height: 35px;
	background: #F5F5F5;
	border: 1px solid white;
	vertical-align: middle;
	text-align: center
}
-->
</style>
<script src="js/main.js" type="text/javascript"></script>
</head>
<body>
	<div id="btm">
		<div id="main">

			<div id="header">
				<div id="top"></div>
				<div id="logo">
					<h1>跑步社区</h1>
				</div>
				<jsp:include page="logout.jsp" flush="true"></jsp:include>
				<jsp:include page="member-header-menu.jsp" flush="true"></jsp:include>
			</div>

			<div id="content" align="center">

				<div id="center">

					<form action="result_Flow.htm" method="post" name="form1">
						<table width="600">
							<thead>
								<tr>
									<th><h4>个性化空间</h4></th>
								</tr>
							</thead>
							<tr>
								<td width="100%">
									<table width="100%">
										<tbody>
											<tr>
												<td rowspan="5" width="30%" class="line4"><img
													src="GetImgServlet?imgPath=${sessionScope.current_user.memberSpace.icon}"
													alt=Face width="84" height="84"></td>
												<th width="25%" class="line1">昵称</th>
												<td width="45%">${sessionScope.current_user.nickName}</td>
											</tr>

											<tr>
												<th class="line1">性别</th>
												<td>
													<s:if test="#session.current_user.gender==0">
														男
													</s:if>
													<s:else>
														女
													</s:else>
												</td>
											</tr>
											<tr>
												<th class="line1">积分</th>
												<td>${sessionScope.current_user.point}</td>
											</tr>
											<tr>
												<th class="line1">跑步健将</th>
												<td>${sessionScope.current_user.graderecord.gradename}</td>
											</tr>
											<tr>
												<th class="line1">来自</th>
												<td>${sessionScope.current_user.provinceCity}</td>
											</tr>
											<tr>
												<th class="line1">跑步习惯与主张</th>
												<td colspan="2" height="90"><span>&nbsp;&nbsp;&nbsp;
														${sessionScope.current_user.memberSpace.opinion} </span></td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="3"><input type="button" value="修改"
													onclick="location.href='member/createSpace.jsp?cmd=update';"
													style="width: 60; height: 25" /> <input type="button"
													value="返回" onclick="location.href='member/after-login.action' ;"
													style="width: 60; height: 25" /></td>
											</tr>
										</tfoot>
									</table>
								</td>

							</tr>

						</table>
					</form>

					<jsp:include page="member-footer-menu.jsp" flush="true"></jsp:include>

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
