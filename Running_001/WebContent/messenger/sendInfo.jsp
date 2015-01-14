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
<link rel="stylesheet" type="text/css" id="css"
	href="style/style1.css" />
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
	height: 35px;
	background: #FFFFFF;
	border: 1px solid white;
}

thead tr th.line1 {
	background: #D3E5FD;
}

thead tr th.line4 {
	background: #C6C6C6;
}

tbody tr td {
	height: 30px;
	background: #CBE2FB;
	border: 1px solid white;
	vertical-align: middle;
}

tbody tr td.line4 {
	background: #D5D6D8;
}

tbody tr th {
	height: 30px;
	background: #DFEDFF;
	border: 1px solid white;
	vertical-align: middle;
	text-align: right
}

tfoot.tr th {
	height: 30px;
	background: #FFFFFF;
	border: 1px solid white;
	vertical-align: middle;
	text-align: center
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
				<div id="logout">
					<a href="login.html">注 销</a> | 收 藏
				</div>
				<jsp:include page="../member/member-header-menu.jsp"></jsp:include>
			</div>
			<div id="tabs1">
				<ul>
					<li><a href="sendInfo.html" title="写纸条"><span><b>写纸条</b></span></a></li>
					<li><a href="inbox.html" title="收件箱"><span>收件箱</span></a></li>
					<li><a href="outbox.html" title="发件箱"><span>发件箱</span></a></li>
				</ul>

			</div>
			<div id="content" align="center">
				<div id="center">
					<br /> <br />
					<table width="600">
						<thead>
							<tr>
								<th colspan="2"><h4>发送讯息</h4></th>
							</tr>
						</thead>

						<tr>
							<td width="100%">
								<form name="sendInfoForm"
									action="pages/messenger/sendMessage.do" method="post"
									onsubmit="return sendInfo(this);">
									<table width="100%">
										<tbody>
											<tr>
												<th width="30%" class="line1" scope="col">收信人：</th>
												<td><input type="text" name="receiver"
													style="width: 250; height: 25" value="" /></td>
											</tr>
											<tr>
												<th>主题：</th>
												<td><input type="text" name="title"
													style="width: 350; height: 25" /></td>
											</tr>
											<tr>
												<th>消息内容：</th>
												<td><textarea name="content" rows="8" cols="55"></textarea>
												</td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<th colspan="2"><input type="submit" value="发送" /> <input
													type="reset" value="重置" /></th>
											</tr>
										</tfoot>

									</table>
								</form>
							</td>

						</tr>

					</table>
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
