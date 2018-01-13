<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图库管理</title>
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
                location.href ="${ctx}/basic/gallery/deleteSelect?id="+poids;

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
		<li class="active"><a href="${ctx}/basic/gallery/">图库列表</a></li>
		<shiro:hasPermission name="basic:gallery:edit"><li><a href="${ctx}/basic/gallery/form">图库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gallery" action="${ctx}/basic/gallery/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>分类：</label>
				<form:input path="galleryCategory" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>加入日期：</label>
				<input name="beginInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${gallery.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${gallery.endInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>vip：</label>
				<form:select path="vipStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vip_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnDelete" class="btn btn-primary" type="button" value="批量删除"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序列</th>
				<th>封面</th>
				<th>标题</th>
				<th>分类</th>
				<th>vip</th>
				<th>浏览量</th>
				<th>点赞</th>
				<th>评论</th>
				<th>赞赏</th>
				<th>发布时间</th>
				<shiro:hasPermission name="basic:gallery:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gallery">
			<tr>
				<td>
					<input id="indexcheck" type="checkbox" name="indexcheck" value="${gallery.id}">
				</td>
				<td>
					<img src="${gallery.coverGallery}" style="width: 40px;height:40px;"/>
				</td>
				<td>
					${gallery.title}
				</td>
				<td>
					${gallery.galleryCategory}
				</td>
				<td>
					${fns:getDictLabel(gallery.vipStatus, 'vip_status', '')}
				</td>
				<td>
					${gallery.hits}
				</td>
				<td>
					${gallery.likes}
				</td>
				<td>
					${gallery.commentId}
				</td>
				<td>
					${gallery.praise}
				</td>
				<td>
					<fmt:formatDate value="${gallery.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="basic:gallery:edit"><td>
    				<a href="${ctx}/basic/gallery/form?id=${gallery.id}">修改</a>
					<a href="${ctx}/basic/gallery/delete?id=${gallery.id}" onclick="return confirmx('确认要删除该图库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>