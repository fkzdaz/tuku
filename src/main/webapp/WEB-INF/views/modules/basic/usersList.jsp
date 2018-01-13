<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/basic/users/">用户列表</a></li>
		<shiro:hasPermission name="basic:users:edit"><li><a href="${ctx}/basic/users/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="users" action="${ctx}/basic/users/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户ID：</label>
				<form:input path="userId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>等级：</label>
				<form:select path="grade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('grade_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>禁言：</label>
				<form:select path="gossip" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('special_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>加入日期：</label>
				<input name="beginInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${users.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${users.endInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>头像</th>
				<th>用户名</th>
				<th>性别</th>
				<th>地址</th>
				<th>等级</th>
				<th>禁言</th>
				<th>加入时间</th>
				<shiro:hasPermission name="basic:users:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="users">
			<tr>
				<td><a href="${ctx}/basic/users/form?id=${users.id}">
					${users.userId}
				</a></td>
				<td>
					<img src="${users.headPicture}" style="width: 40px;height:40px;"/>
				</td>
				<td>
					${users.name}
				</td>
				<td>
					${fns:getDictLabel(users.sex, 'sex', '')}
				</td>
				<td>
					${users.address}
				</td>

				<td>
					${fns:getDictLabel(users.grade, 'grade_status', '')}
				</td>
				<td>
					${fns:getDictLabel(users.gossip, 'special_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${users.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="basic:users:edit"><td>
    				<a href="${ctx}/basic/users/form?id=${users.id}">修改</a>
					<a href="${ctx}/basic/users/delete?id=${users.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>