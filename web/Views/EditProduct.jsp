<%-- 
    Document   : ProductManager
    Created on : Jul 1, 2022, 11:13:32 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <jsp:include page="../css/manager.jsp"></jsp:include>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>             
        <!-- Edit Modal HTML -->
        <div>
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="EditProduct" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">	
                            <div class="form-group">
                                <label>ID</label>
                                <input name="id" type="text" class="form-control" value="${productUp.id}" readonly required>
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" value="${productUp.name}" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" value="${productUp.price}" required>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${clist}" var="o">
                                        <option ${o.id==productUp.categoryId?"selected":""} value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" class="form-control" value="${productUp.image}" required>
                            </div>   
                            <div class="form-group">
                                <label>Stocking</label>
                                <input type="radio" name="stocking" value="t" ${productUp.stocking?"checked":""}> Còn   
                                              
                                <input type="radio" name="stocking" value="f" ${productUp.stocking?"":"checked"}> Hết   <br>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <input name="description" type="text" class="form-control" value="${productUp.description}" required>
                            </div> 
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-default" href="ProductManagerController">Cancel</a>
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Delete Modal HTML -->       
        <script src="js/manager.js" type="text/javascript"></script>
        <script>

        </script>
    </body>
</html>
