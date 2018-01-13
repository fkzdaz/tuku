<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

            $("#btnDelete").click(function (event) {

                var myArray = new Array();
                var poids = "";
                $("#contentTable input[type=checkbox]:checked").each(function () {
                    poids = poids + $(this).val()+",";
                });
                if (poids==null||poids==""){
                    alert("请选择至少一条数据");
                    return;
                }
                location.href ="${ctx}/basic/comments/deleteSelect?id="+poids;

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
		<li class="active"><a href="${ctx}/basic/comments/">评论列表</a></li>
		<%--<shiro:hasPermission name="basic:comments:edit"><li><a href="${ctx}/basic/comments/form">评论添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="comments" action="${ctx}/basic/comments/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户ID：</label>
				<form:input path="userId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>加入日期：</label>
				<input name="beginInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${comments.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${comments.endInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>用户ID</th>
				<th>用户昵称</th>
				<th>评论内容</th>
				<th>评论时间</th>
				<shiro:hasPermission name="basic:comments:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comments">
			<tr>
				<td>
					<input id="indexcheck" type="checkbox" name="indexcheck" value="${comments.id}">
				</td>
				<td>
					${comments.userId}
				</td>
				<td>
					${comments.userName}
				</td>
				<td>
					${comments.commentsContent}
				</td>
				<td>
					<fmt:formatDate value="${comments.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="basic:comments:edit"><td>
    				<%--<a href="${ctx}/basic/comments/form?id=${comments.id}">修改</a>--%>
					<a href="${ctx}/basic/comments/delete?id=${comments.id}" onclick="return confirmx('确认要删除该评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>