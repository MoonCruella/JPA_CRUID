<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form action="${pageContext.request.contextPath }/admin/category/insert" method = "post" enctype = "multipart/form-data">
  <label for="categoryname">Category name : </label><br>
  <input type="text" id="categoryname" name="categoryname"><br>
  <label for="images">Images : </label><br>
   <img id = "imagess" height = "150" width = "200" src = ""/>
  <input type="file" onchange = "chooseFile(this)" id="images" name="images"><br><br>
   <label for="status">Status : </label><br>
  <input type="radio" id="status" name="status" value = "1" checked>
  <label type = "html"> Dang hoat dong</label><br>
  <input type="radio" id="status" name="status" value = "0">
  <label type = "css">Khoa</label><br>
  <input type="submit" value="Submit">
</form>