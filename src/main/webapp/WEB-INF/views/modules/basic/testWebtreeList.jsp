<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试批量删除管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		    $("#btnDelete").click(function (event) {

				var myArray = new Array();
				var poids = "";
                $("#contentTable input[type=checkbox]:checked").each(function () {
					poids = poids + $(this).val()+",";
                });
                alert("确定删除？")
                if (poids==null||poids==""){
                    alert("请选择至少一条数据");
                    return;
				}
                location.href ="${ctx}/basic/testWebtree/deleteSelect?id="+poids;

            });

			
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
		<li class="active"><a href="${ctx}/basic/testWebtree/">测试批量删除列表</a></li>
		<shiro:hasPermission name="basic:testWebtree:edit"><li><a href="${ctx}/basic/testWebtree/form">测试批量删除添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testWebtree" action="${ctx}/basic/testWebtree/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名字：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="num" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<div style="margin-bottom: 5px;padding-left: 10px;background: #F5F5F5">&nbsp;&nbsp;
		<input id="btnDelete" class="btn btn-primary" type="button" value="批量删除">&nbsp;&nbsp;
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序列</th>
				<th>名字</th>
				<th>手机号码</th>
				<shiro:hasPermission name="basic:testWebtree:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testWebtree">
			<tr>
				<td><input id="indexcheck" type="checkbox" name="indexcheck" value="${testWebtree.id}"></td>
				<td>
					<%--<a href="${ctx}/basic/testWebtree/form?id=${testWebtree.id}">--%>
					${testWebtree.name}
				<%--</a>--%>
				</td>
				<td>
					${testWebtree.num}
				</td>
				<shiro:hasPermission name="basic:testWebtree:edit"><td>
    				<a href="${ctx}/basic/testWebtree/form?id=${testWebtree.id}">修改</a>
					<a href="${ctx}/basic/testWebtree/delete?id=${testWebtree.id}" onclick="return confirmx('确认要删除该测试批量删除吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>