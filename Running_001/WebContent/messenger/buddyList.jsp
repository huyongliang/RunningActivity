<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="keywords" content="杰普软件(www.briup.com)" />
<meta name="description" content="杰普软件(www.briup.com)" />
<title>杰普――跑步社区</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style.css" />

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
			
		function addFriend(){
			document.forms.buddyListForm.action="messenger/friend-match-random.action?cmd=top10Mem";
			document.forms.buddyListForm.submit();		
		}
		function delBuddy(){
			cCount = getCheckedCount("nickName");
			if (cCount == 0){
				alert("请至少一条信息!");
				return;
			}
			if (confirm("确定删除吗？")==false){
				return false;
			}
			document.forms.buddyListForm.action="<%=basePath%>messenger/deleteBuddy.action";
		document.forms.buddyListForm.submit();
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
<jsp:include page="../member/logout.jsp" flush="true"></jsp:include>
				<jsp:include page="../member/member-header-menu.jsp" flush="true"></jsp:include>
			</div>
			<jsp:include page="friend-header-menu.jsp" flush="true"></jsp:include>
			<br /> <br />

			<div id="content" align="center">
				<div id="center">
					<br></br> <br></br>
					<form method="post" name="buddyListForm">
						<table width="600" align="center" cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<td><h4>系统讯息 - 好友列表</h4></td>
									<td valign="bottom"><span
										onclick="javascript:addFriend();"> <img
											src="images/icon06.gif" height="14" />&nbsp;添加
									</span> <span onclick="javascript:delBuddy();"> <img
											src="images/delete.gif" height="14" />&nbsp;删除
									</span></td>
								</tr>

							</thead>

							<tr>
								<td colspan="2" width="100%">
									<table width="100%">
										<thead>
											<tr>
												<th width="10%" align="center" class="line1" scope="col">
													<b>选择</b>
												</th>
												<th width="20%" align="center" scope="col"><b>姓名</b></th>
												<th width="20%" align="center" scope="col"><b>性别</b></th>
												<th width="20%" align="center" scope="col"><b>年龄</b></th>
												<th width="20%" align="center" scope="col"><b>来自</b></th>
												<th width="10%" align="center"><b>操作</b></th>
											</tr>
										</thead>

										<tbody>

											<s:iterator value="friendList" var="f">
												<tr>
													<th width="10%" align="center"><input type="checkbox"
														name="nickName" value="${f.nickName}" /></th>
													<td width="20%" align="center">${f.nickName}</td>
													<td width="20%" align="center"><s:if
															test="f.age==0">男</s:if> <s:else>女</s:else></td>
													<td width="20%" align="center">${f.age}</td>
													<td width="20%" align="center">${f.getCityString()}</td>
													<td width="10%" align="center"><img
														src="images/button_delete.gif" alt="移动到黑名单"
														onclick="window.location='messenger/moveToBlackList.action?blackName=${f.nickName}'"
														style="cursor: hand" /></td>
												</tr>
											</s:iterator>

										</tbody>

										<tfoot>
											<tr>
												<td align="right" width="30%" colspan="6">选择：<a
													href="#" onclick="javascript:selAllCheckbox('nickName');">全部</a>-
													<a href="#"
													onclick="javascript:unselAllCheckbox('nickName');">不选</a>-
													<a href="#" onclick="javascript:reAllCheckbox('nickName');">反选</a>
												</td>
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

				<script type="text/javascript">
					if ("${deleteMsg}" != null && "${deleteMsg}" != "")
						alert("${deleteMsg}");
				</script>
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
