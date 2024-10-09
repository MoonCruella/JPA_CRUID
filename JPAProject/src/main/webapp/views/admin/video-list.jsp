<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri ="jakarta.tags.core" %>
<%@taglib prefix = "fn" uri ="jakarta.tags.functions" %>
<%@taglib prefix = "fmt" uri = "jakarta.tags.fmt" %>

<a href ="${pageContext.request.contextPath}/admin/categories">Watch Categories</a><br>
<a href ="<c:url value='/admin/category/video/add?id=${cateid}'/>">Add Video</a>
<table border = "1" width = "100%">
  <tr>
    <th>STT</th>
    <th>Title</th>
    <th>Poster</th>
    <th>Category</th>
    <th>Description</th>
    <th>Views</th>
    <th>Active</th>
    <th>Hoat Dong</th>
  </tr>
	<c:forEach items= "${listvid}" var="vid" varStatus="STT" >
		<tr>
			<td>${STT.index+1}</td> 
			
			<td>${vid.title}</td>
			
			<td>
				<c:if test= "${fn:substring(vid.poster, 0, 5) != 'https'}">
					<c:url value="/image?fname=${vid.poster}" var= "imgUrl"></c:url>
				</c:if>
				<c:if test="${fn:substring(vid.poster, 0, 5) == 'https'}">
					<c:url value= "${vid.poster}" var= "imgUrl"></c:url>
				</c:if>
				<img height="150" width="200" src="${imgUrl}" />
			</td>
			
		    <td>${vid.category.categoryname}</td>
		    
			<td>${vid.description}</td>
			
			<td>${vid.views}</td>
			
			<td>
				<c:if test="${vid.active == 1 }">
					<span>Hoat Dong</span>
				</c:if>
				<c:if test="${vid.active != 1 }">
					<span>Khoa</span>
				</c:if>
			</td>
			
			<td>
			    <a href= "<c:url value='/admin/category/video/edit?id=${vid.videoId}'/>">Sửa</a>
		        <a href= "<c:url value='/admin/category/video/delete?id=${vid.videoId}'/>">Xóa</a>
		    </td>
		</tr>
	</c:forEach>
</table>