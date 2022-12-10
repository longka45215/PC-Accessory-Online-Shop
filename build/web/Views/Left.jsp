<%-- 
    Document   : Left
    Created on : Jun 29, 2022, 11:01:33 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <div class="col-sm-3">
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-info text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                            <ul class="list-group category_block ">
                            <c:forEach items="${clist}" var="o">
                                <li class="list-group-item   ${o.id==active?"active":""}"><a class="text-dark" href="CategoryController?cid=${o.id}">${o.name}</a></li>
                                </c:forEach>

                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">New product</div>
                       <div class="card-body">
                            <a href="DetailProductController?pid=${last.id}"><img class="img-fluid" src="${last.image}" /></a>
                            <a href="DetailProductController?pid=${last.id}"><h5 class="card-title">${last.name}</h5></a>
                            
                            <p class="bloc_left_price">${last.price} đ</p>
                        </div>
                    </div>
                </div>
