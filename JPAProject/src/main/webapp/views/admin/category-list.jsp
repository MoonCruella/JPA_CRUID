<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri ="jakarta.tags.core" %>
<%@taglib prefix = "fn" uri ="jakarta.tags.functions" %>
<%@taglib prefix = "fmt" uri = "jakarta.tags.fmt" %>


<a href = "${pageContext.request.contextPath }/admin/category/add">Add Category</a>
<table border = "1" width = "100%">
  <tr>
    <th>STT</th>
    <th>Images</th>
    <th>Category Name</th>
    <th>Status</th>
    <th>Action</th>
    <th>Video</th>
  </tr>
	<c:forEach items= "${listcate}" var="cate" varStatus="STT" >
		<tr>
			<td>${STT.index+1}</td>
			<td>
				
				<c:if test= "${fn:substring(cate.images, 0, 5) != 'https'}">
					<c:url value="/image?fname=${cate.images}" var= "imgUrl"></c:url>
				</c:if>
				<c:if test="${fn:substring(cate.images, 0, 5) == 'https'}">
					<c:url value= "${cate.images}" var= "imgUrl"></c:url>
				</c:if>
				<img height="150" width="200" src="${imgUrl}" />
			</td>
			<td>${cate.categoryname}</td>
			<td>
				<c:if test="${cate.status == 1 }">
					<span>Hoat Dong</span>
				</c:if>
				<c:if test="${cate.status != 1 }">
					<span>Khoa</span>
				</c:if>
			
			</td>
			<td><a href= "<c:url value='/admin/category/edit?id=${cate.categoryid}'/>">Edit</a>
				<a href= "<c:url value='/admin/category/delete?id=${cate.categoryid }'/>">Remove</a></td>
			<td><a href="<c:url value='/admin/category/video/add?id=${cate.categoryid}'/>">Add Video</a>
				<a href ="<c:url value='/admin/category/videos?id=${cate.categoryid}'/>">Show List</a></td>
		</tr>
	</c:forEach>
</table>