<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图库分类管理</title>
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
                location.href ="${ctx}/basic/galleryCategory/deleteSelect?id="+poids;

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
		<li class="active"><a href="${ctx}/basic/galleryCategory/">图库分类列表</a></li>
		<shiro:hasPermission name="basic:galleryCategory:edit"><li><a href="${ctx}/basic/galleryCategory/form">图库分类添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<div style="margin-bottom: 5px;padding-left: 10px;background: #F5F5F5">&nbsp;&nbsp;
		<input id="btnDelete" class="btn btn-primary" type="button" value="批量删除">&nbsp;&nbsp;
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序列</th>
				<th>封面图</th>
				<th>名称</th>
				<th>描述</th>
				<th>排序</th>
				<shiro:hasPermission name="basic:galleryCategory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="galleryCategory">
			<tr>
				<td>
					<input id="indexcheck" type="checkbox" name="indexcheck" value="${galleryCategory.id}">
				</td>
				<td>
					<img src="${galleryCategory.coverImg}" style="width: 40px;height:40px;"/>
				<td>
					${galleryCategory.categoryName}
				</td>
				<td>
					${galleryCategory.description}
				</td>
				<td>
					${galleryCategory.sort}
				</td>
				<shiro:hasPermission name="basic:galleryCategory:edit"><td>
    				<a href="${ctx}/basic/galleryCategory/form?id=${galleryCategory.id}">修改</a>
					<a href="${ctx}/basic/galleryCategory/delete?id=${galleryCategory.id}" onclick="return confirmx('确认要删除该图库分类吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>