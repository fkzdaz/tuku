<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>复选管理</title>
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
		<li class="active"><a href="${ctx}/basic/testFuxuan/">复选列表</a></li>
		<shiro:hasPermission name="basic:testFuxuan:edit"><li><a href="${ctx}/basic/testFuxuan/form">复选添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testFuxuan" action="${ctx}/basic/testFuxuan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名字：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>年龄：</label>
				<form:input path="age" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名字</th>
				<th>年龄</th>
				<shiro:hasPermission name="basic:testFuxuan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testFuxuan">
			<tr>
				<td><a href="${ctx}/basic/testFuxuan/form?id=${testFuxuan.id}">
					${fns:getDictLabel(testFuxuan.name, '', '')}
				</a></td>
				<td>
					${fns:getDictLabel(testFuxuan.age, '', '')}
				</td>
				<shiro:hasPermission name="basic:testFuxuan:edit"><td>
    				<a href="${ctx}/basic/testFuxuan/form?id=${testFuxuan.id}">修改</a>
					<a href="${ctx}/basic/testFuxuan/delete?id=${testFuxuan.id}" onclick="return confirmx('确认要删除该复选吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>