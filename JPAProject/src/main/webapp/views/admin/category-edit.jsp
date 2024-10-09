<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri ="jakarta.tags.core" %>
<%@taglib prefix = "fn" uri ="jakarta.tags.functions" %>
<%@taglib prefix = "fmt" uri = "jakarta.tags.fmt" %>

    

<form action="${pageContext.request.contextPath }/admin/category/update" method = "post" enctype = "multipart/form-data">
  
  <input type="text" id="categoryid" name="categoryid" value = "${cate.categoryid}" hidden = "true"><br>
  
  <label for="categoryname">Category name : </label><br>
  <input type="text" id="categoryname" name="categoryname" value = "${cate.categoryname}"><br>
  <label for="images">Images : </label><br>
  
	<c:if test= "${fn:substring(cate.images, 0, 5) != 'https'}">
		<c:url value="/image?fname=${cate.images}" var= "imgUrl"></c:url>
	</c:if>
	<c:if test="${fn:substring(cate.images, 0, 5) == 'https'}">
		<c:url value= "${cate.images}" var= "imgUrl"></c:url>
	</c:if>
	<img id = "imagess" height="150" width="200" src="${imgUrl}" />

  <input type="file" onchange = "chooseFile(this)" id="images" name="images" value = "${cate.images}"><br><br>
  <label for="status">Status : </label><br>
  <c:if test="${cate.status == 1}">
  <input type="radio" id="status" name="status" value="1" checked>
</c:if>
<c:if test="${cate.status != 1}">
  <input type="radio" id="status" name="status" value="1">
</c:if>
<label for="status">Dang hoat dong</label><br>

<input type="radio" id="status" name="status" value="0" <c:if test="${cate.status == 0}">checked</c:if>>
<label for="status">Khoa</label><br>
  <input type="submit" value="Submit">
</form>