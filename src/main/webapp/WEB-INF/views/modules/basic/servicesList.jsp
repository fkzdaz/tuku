<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务管理</title>
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
		<li class="active"><a href="${ctx}/basic/services/">服务列表</a></li>
		<shiro:hasPermission name="basic:services:edit"><li><a href="${ctx}/basic/services/form">服务添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>排序</th>
				<th>服务内容</th>
				<th>价格（元）</th>
				<th>时长</th>
				<th>特价</th>
				<shiro:hasPermission name="basic:services:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="services">
			<tr>
				<td>
					${services.sort}
				</a></td>
				<td>
					${services.serviceContent}
				</td>
				<td>
					${services.price}
				</td>
				<td>
					${services.serviceTime}
				</td>
				<td>
					${fns:getDictLabel(services.special, 'special_status', '')}
				</td>
				<shiro:hasPermission name="basic:services:edit"><td>
    				<a href="${ctx}/basic/services/form?id=${services.id}">修改</a>
					<a href="${ctx}/basic/services/delete?id=${services.id}" onclick="return confirmx('确认要删除该服务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>