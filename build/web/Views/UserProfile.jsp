<%-- 
    Document   : UserProfile
    Created on : Jul 11, 2022, 10:27:30 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"/>

    </head>
    <body>
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                
                <div class="col-md-5 border-right">
                    <a class="btn btn-info" href="HomePageController">Home</a>
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                        <span class="font-weight-bold">${userlogin.username}</span>
                        <span class="text-black-50">${userlogin.email}</span>
                        <span> </span>
                        
                    </div>
                </div>
                <div class="col-md-7 border-right">
                    <form action="UserProfile" method="post">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Profile Settings</h4>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-12"><label class="labels">FullName</label>
                                <input type="text" class="form-control" placeholder="Full name" name="fullname" value="${userlogin.fullname}"></div>

                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12"><label class="labels">Age:</label>
                                <input type="text" class="form-control" placeholder="Enter age" name="age" value="${userlogin.age}"></div>
                            <div class="col-md-12"><label class="labels">Gender:</label><br>
                                <input type="radio" name="gender" value="m" class="form-check-input" ${userlogin.gender?"checked":""}> Male
                                <input type="radio" name="gender" value="f"class="form-check-input" ${userlogin.gender?"":"checked"}> Female
                            </div>

                            <div class="col-md-12"><label class="labels">Phone Number:</label>
                                <input type="text" class="form-control" placeholder="Enter phone number" name="phone" value="${userlogin.phone}"></div>


                            <div class="col-md-12"><label class="labels">Email ID:</label>
                                <input type="text" class="form-control" placeholder="Enter email id" name="email" value="${userlogin.email}"></div>

                        </div>

                                <div class="mt-5 text-center"><input class="btn btn-primary profile-button" type="submit" value="Save Profile"></div>
                    </div>
                                </form>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
