<%-- 
    Document   : Header
    Created on : Jun 29, 2022, 10:19:35 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="HomePageController">Long</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <c:if test="${sessionScope.userlogin!=null&&sessionScope.userlogin.authority==0}">
                <li class="nav-item">
                    <a class="nav-link" href="UserManager">Manager Users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ProductManagerController">Manager Product</a>
                </li></c:if>
                <c:if test="${sessionScope.userlogin!=null}">
                <li class="nav-item">
                    <a class="nav-link" href="UserProfile">Hello ${sessionScope.userlogin.fullname}</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="LogoutController">Logout</a>
                </li></c:if>
                <c:if test="${sessionScope.userlogin==null}">
                <li class="nav-item">
                    <a class="nav-link btn" href="LoginController">Login</a>
                </li></c:if>
            </ul>

            <form action="SearchController" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input type="hidden" value="${currentNum}" name="num">
                    <input name="search" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search..." value="${valueS}">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="ShowCart">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <span class="badge badge-light">${sessionScope.productNumber}</span>
                </a>
            </form>
        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">My Shop</h1>
        <p class="lead text-muted mb-0">Tất cả sản phẩm được thêm vào theo ý thích của chủ shop</p>
    </div>
</section>
