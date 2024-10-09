<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form action="${pageContext.request.contextPath }/admin/category/video/insert" method = "post" enctype = "multipart/form-data">
  
  <label for="videoid">Video Id: </label><br>
  <input type="text" id="videoid" name="videoid"><br>
  
  <label for="title">Video Title: </label><br>
  <input type="text" id="title" name="title"><br>
  
  <label for="images">Poster : </label><br>
  <img id = "imagess" height = "150" width = "200" src = ""/>
  <input type="file" onchange = "chooseFile(this)" id="images" name="poster"><br><br>
  
  <label for="active">Active : </label><br>
  <input type="radio" id="status" name="active" value = "1" checked>
  <label type = "html"> Dang hoat dong</label><br>
  <input type="radio" id="status" name="active" value = "0">
  <label type = "css">Khoa</label><br>
  
   <label for="description">Description: </label><br>
  <input type="text" id="description" name="description"><br>
  
   <label for="view">View: </label><br>
  <input type="text" id="view" name="view"><br>
  
   <label for="category">Category: </label><br>
   <input type="text" id="category" name="category" hidden = "true" value = "${category.categoryid}"><br>
   <input type="text" id="category" name="categoryname" value = "${category.categoryname}"><br>
  <input type="submit" value="Submit">
</form>