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
        <title>CRUD Product</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        
                        <div class="col-sm-6">
                            <h2><b>Manage User</b></h2>
                        </div>
                        <div class="col-sm-2">
                            <a href="HomePageController"  class="btn btn-success" >Home</a>
                        </div> 
                        <div class="col-sm-4">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add Admin</span></a>

                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            
                            <th>ID</th>
                            <th>Username</th>
                            <th>FullName</th>
                            <th>Age</th>
                            <th>Gender</th>
                            <th>Email</th>
                            <th>Phone</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userlist}" var="o">
                            <c:if test="${o.authority==1}">
                            <tr>                               
                                <td>${o.id}</td>
                                <td>${o.username}</td>
                                <td>${o.fullname} </td>
                                <td>${o.age} </td>
                                <td>${o.gender?"Male":"Female"} </td>
                                <td>${o.email} </td>
                                <td>${o.phone} </td>                                
                                <td>
                                    
                                    <a href="DeleteUser?type=${o.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                            </c:if>
                        </c:forEach>
                    </tbody> 
                </table>
                <div class="row justify-content-center align-items-center">
                    <nav aria-label="Page navigation example" class="text-center">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                                <c:forEach begin="1" end="${endP}" var="i">
                                <li class="page-item"><a class="page-link" href="ProductManagerController?page=${i}">${i}</a></li>                                
                                </c:forEach>

                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="AddAdmin" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Admin</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Username</label>
                                <input name="username" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input name="password" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Re-password</label>
                                <input name="repass" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Age</label>
                                <input name="age" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Gender:</label>
                                <input type="radio" name="gender" value="m" > Male                              
                                <input type="radio" name="gender" value="f" > Female   <br>
                            </div>
                            <div class="form-group">
                                <label>Email:</label>
                                <input name="email" type="text" class="form-control" required>
                            </div> 
                            <div class="form-group">
                                <label>Phone</label>
                                <input name="phone" type="text" class="form-control" required>
                            </div> 

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="js/manager.js" type="text/javascript"></script>
        <script>

        </script>
    </body>
</html>
