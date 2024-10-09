<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri ="jakarta.tags.core" %>
<%@taglib prefix = "fn" uri ="jakarta.tags.functions" %>
<%@taglib prefix = "fmt" uri = "jakarta.tags.fmt" %>
<form action="${pageContext.request.contextPath }/admin/category/video/update" method = "post" enctype = "multipart/form-data">
   
  <label for="videoid">Video Id: </label><br>
  <input type="text" id="videoid" value = "${vid.videoId}" name="videoid"><br>
  
  <label for="title">Video Title: </label><br>
  <input type="text" id="title"  value = "${vid.title}" name="title"><br>
  
  <label for="images">Poster : </label><br>
  	<c:if test= "${fn:substring(vid.poster, 0, 5) != 'https'}">
		<c:url value="/image?fname=${vid.poster}" var= "imgUrl"></c:url>
	</c:if>
	<c:if test="${fn:substring(vid.poster, 0, 5) == 'https'}">
		<c:url value= "${vid.poster}" var= "imgUrl"></c:url>
	</c:if>
  <img id = "imagess" height = "150" width = "200" src = "${imgUrl}"/>
  <input type="file" onchange = "chooseFile(this)" id="images"  value = "${vid.poster}" name="poster"><br><br>
  
  <label for="active">Active : </label><br>
	<c:if test="${vid.active == 1}">
	  <input type="radio" id="status" name="active" value="1" checked>
	</c:if>
	<c:if test="${vid.active != 1}">
	  <input type="radio" id="status" name="active" value="1">
	</c:if>
	<label for="status">Dang hoat dong</label><br>
	
	<input type="radio" id="status" name="active" value="0" <c:if test="${vid.active == 0}">checked</c:if>>
	<label for="status">Khoa</label><br>
  
  <label for="description">Description: </label><br>
  <input type="text" id="description"  value = "${vid.description}" name="description"><br>
  
   <label for="view">View: </label><br>
  <input type="text" id="view"  value = "${vid.views}" name="view"><br>
  
   <label for="category">Category: </label><br>
   <input type="text" id="category" name="category" hidden = "true" value = "${vid.category.categoryid}"><br>
   <input type="text" id="category" name="categoryname" value = "${vid.category.categoryname}"><br>
  <input type="submit" value="Submit">
</form>