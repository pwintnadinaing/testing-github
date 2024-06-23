<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<jsp:include page = "index.jsp"></jsp:include>
			<div class="container">
				<h4>New Book Info</h4>
				<div class="text-danger">${error_msg}</div>
				<form:form action="/bookmanagement/addbook" method="post"  modelAttribute = "book">				
					<div class="mb-4">
						<form:label path ='code' class="form-lable">Code</form:label>
						<div class="col-sm-3">
						<form:input type="text" class="form-control"  path='code'></form:input>
						</div>
						<form:errors path='code' cssClass='text-danger'></form:errors>
						</div>
					<div class="mb-4">
						<form:label path ='name' class="form-lable">Name</form:label>
						<div class="col-sm-3">
						<form:input type="text"  class="form-control"   path='name'></form:input>
						</div>
						<form:errors path='name' cssClass='text-danger'></form:errors>
						</div>
						
					<div class="mb-4">
						<form:label path ='price' class="form-lable">Price</form:label>
						<div class="col-sm-3">
						<form:input type="text" class="form-control" path='price'></form:input>
						</div>
						<form:errors path='price' cssClass='text-danger'></form:errors>
						</div>
		
					<div class="mb-4">
						<form:label path ='authors' class="form-lable">Author</form:label>
						<div class="col-sm-3">
								<form:select path='authors' multiple="true" class="form-control">
										<form:options items="${selected_authors}" itemValue="id" itemLabel="name"></form:options>
								</form:select>
						</div>
						<form:errors path='authors' cssClass='text-danger'></form:errors>
						</div>
						
					<div class="mb-4">
						<input type="submit" value="AddBook" class="btn btn-primary"/>
					</div>
			</form:form>
			</div>
			
</body>
</html>