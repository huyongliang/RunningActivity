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
<link rel="stylesheet" type="text/css" id="css"
	href="style/style.css" />
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
	height: 50px;
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
	background: #FFFFFF;
	border: 1px solid white;
	vertical-align: middle;
}
-->
</style>
<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
			function delMessage(){
				cCount = getCheckedCount("ID");
				if (cCount == 0){
					alert("请至少一条信息!");
					return;
				}
				if (confirm("确定删除吗？")==false){
					return false;
				}
				document.forms.inboxForm.action="<%=basePath%>messenger/delReceiveMessage.action";
				document.forms.inboxForm.submit();	
			}	
			function detailMessage(){
				cCount = getCheckedCount("ID");
				if (cCount == 0){
					alert("请选择一条信息!");
					return;
				}
				if (cCount > 1){
					alert("对不起，一次只能查看一条信息!");
					return;
				}
				document.forms.inboxForm.action="<%=basePath%>messenger/detailMessage.action";
		document.forms.inboxForm.submit();
	}
</script>
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
					<li><a href="sendInfo.html" title="写纸条"><span>写纸条</span></a></li>
					<li><a href="inbox.html" title="收件箱"><span><b>收件箱</b></span></a></li>
					<li><a href="outbox.html" title="发件箱"><span>发件箱</span></a></li>
				</ul>
			</div>
			<br /> <br />

			<div id="content" align="center">
				<div id="center">
					<br /> <br />
					<form method="post" name="inboxForm">
						<table width="600" align="center" cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<td width="70%"><h4>收件箱</h4></td>
									<td><span onclick="javascript:detailMessage();"> <img
											src="images/icon06.gif" height="14" />&nbsp;查看
									</span> <span onclick="javascript:delMessage();"> <img
											src="images/delete.gif" height="14" />&nbsp;删除
									</span></td>
								</tr>
							</thead>

							<tr>

								<td width="100%" colspan="2">
									<table width="100%">
										<thead>
											<tr>
												<th width="10%"><b>选择</b></th>
												<th width="20%"><b>主题</b></th>
												<th width="20%"><b>发信人</b></th>
												<th width="30%"><b>发送时间</b></th>
												<th width="20%"><b>状态</b></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td width="10%"><input type="checkbox" name="ID"
													value="219" /></td>
												<td width="20%"><a href="view.html">你好</a></td>
												<td width="20%">briup1</td>
												<td width="30%">2013/07/30 13:34:25</td>
												<td><img src="images/icon10.gif" /></td>
											</tr>
											<tr>
												<td width="10%"><input type="checkbox" name="ID"
													value="161" /></td>
												<td width="20%"><a
													href="pages/messenger/detailMessage.do?ID=161">你好</a></td>
												<td width="20%">briup3</td>
												<td width="30%">2013/07/27 13:10:46</td>
												<td><img src="images/icon10.gif" /></td>
											</tr>
											<tr>
												<td width="10%"><input type="checkbox" name="ID"
													value="162" /></td>
												<td width="20%"><a
													href="pages/messenger/detailMessage.do?ID=162">你好</a></td>
												<td width="20%">briup3</td>
												<td width="30%">2013/07/27 13:11:00</td>
												<td><img src="images/icon10.gif" /></td>
											</tr>
											<tr>
												<td width="10%"><input type="checkbox" name="ID"
													value="163" /></td>
												<td width="20%"><a
													href="pages/messenger/detailMessage.do?ID=163">你好</a></td>
												<td width="20%">briup3</td>
												<td width="30%">2013/07/27 13:11:31</td>
												<td><img src="images/icon10.gif" /></td>
											</tr>

										</tbody>

										<tfoot>
											<tr>
												<td align="right" width="30%" colspan="5">选择：<a
													href="#" onclick="javascript:selAllCheckbox('ID');">全部</a>-
													<a href="#" onclick="javascript:unselAllCheckbox('ID');">不选</a>-
													<a href="#" onclick="javascript:reAllCheckbox('ID');">反选</a>
												</td>
											</tr>
											<tr>
												<td colspan="5"><img src="images/icon09.gif" />未读短信
													<img src="images/icon10.gif" />已读短信</td>

											</tr>
										</tfoot>
									</table>

								</td>

							</tr>

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
