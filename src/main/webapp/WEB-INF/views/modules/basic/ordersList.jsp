<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/basic/orders/">订单列表</a></li>
		<shiro:hasPermission name="basic:orders:edit"><li><a href="${ctx}/basic/orders/form">订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orders" action="${ctx}/basic/orders/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户ID：</label>
				<form:input path="usersId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>服务：</label>
				<form:input path="serviceContent" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>时间：</label>
				<input name="beginInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${users.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${users.endInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:select path="paymentStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('payment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>付款人</th>
				<th>订单编号</th>
				<th>服务名称</th>
				<th>价格</th>
				<th>付款时间</th>
				<th>状态</th>
				<shiro:hasPermission name="basic:orders:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orders">
			<tr>

				<td>
					${orders.usersId}
				</td>
				<td>
					${orders.userName}
				</td>
				<td>
					${orders.orderNum}
				</td>
				<td>
					${orders.serviceContent}
				</td>
				<td>
					${orders.price}
				</td>
				<td>
					<fmt:formatDate value="${orders.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fns:getDictLabel(orders.paymentStatus, 'payment_status', '')}
				</td>
				<shiro:hasPermission name="basic:orders:edit"><td>
    				<a href="${ctx}/basic/orders/form?id=${orders.id}">修改</a>
					<a href="${ctx}/basic/orders/delete?id=${orders.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>