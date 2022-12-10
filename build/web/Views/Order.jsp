<%-- 
    Document   : Login
    Created on : Jun 29, 2022, 9:37:59 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Order</title>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.css'>

        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <form action="FinishOrder" method="get">
                <div class="card">
                    <div class="card-header">
                        Invoice
                        <strong>${order.orderDate}</strong>


                    </div>
                    <div class="card-body">
                        <div class="row mb-4">
                            <div class="col-sm-6">
                                <h6 class="mb-3">From:</h6>
                                <div>
                                    <strong>Long Shop</strong>
                                </div>
                                <div>Somewhere</div>
                                <div>On Earth</div>
                                <div>Email: abc@gg.com</div>
                                <div>Phone: +91 0987654321</div>
                            </div>

                            <div class="col-sm-6">
                                <h6 class="mb-3">To:</h6>
                                <div>
                                    <strong>${sessionScope.userlogin.fullname}</strong>
                                </div>

                                <div>
                                    <c:if test="${hasAddress==1}">
                                    <a class="text-primary" href="Address?choice=1">Use added address</a><br>
                                    
                                        <select name="address">                                        
                                            <c:forEach items="${address}" var="a">
                                                <c:if test="${a.userId==sessionScope.userlogin.id}">
                                                    <option value="${a.address}">${a.address}</option>
                                                </c:if>

                                            </c:forEach>
                                        </select>
                                    </c:if>

                                </div>
                                <div>
                                    <form action="Address">
                                    <input type="text" name="newaddress">
                                    <input type="submit" value="Add">
                                    </form>

                                </div>
                                <div>Email: ${sessionScope.userlogin.email}</div>
                                <div>Phone: ${sessionScope.userlogin.phone}</div>
                            </div>
                        </div>

                        <div class="table-responsive-sm">
                            <table class="table table-striped">
                                <thead>
                                    <tr>

                                        <th>Product Name</th>


                                        <th class="right">Price</th>
                                        <th class="center">Quantity</th>
                                        <th class="right">Total</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${cart}" var="o">
                                    <tbody>
                                        <tr>
                                            <td class="left strong">${o.product.name}</td>


                                            <td class="right">${o.product.price}₫</td>
                                            <td class="center">${o.quantity}</td>
                                            <td class="right">${o.product.price*o.quantity}₫</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="wrapper">

                            <div class="card px-4" style="width: 400px;">
                                <div class=" my-3">
                                    <p class="h5">Choose Payment Method</p>
                                    <c:forEach items="${paymethod}" var="p">
                                        <input type="radio" id="${p.name}" name="pay" value="${p.name}">
                                          <label for="${p.name}">${p.name}</label><br>
                                    </c:forEach>





                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-4 col-sm-5">

                                </div>

                                <div class="col-lg-4 col-sm-5 ml-auto">
                                    <table class="table table-clear">
                                        <tbody>
                                            <tr>
                                                <td class="left">
                                                    <strong>Subtotal</strong>
                                                </td>
                                                <td class="right">${total}₫</td>
                                            </tr>
                                            <tr>
                                                <td class="left">
                                                    <strong>Discount (0%)</strong>
                                                </td>
                                                <td class="right">0₫</td>
                                            </tr>
                                            <tr>
                                                <td class="left">
                                                    <strong>VAT (10%)</strong>
                                                </td>
                                                <td class="right">${total*10/100}₫</td>
                                            </tr>
                                            <tr>
                                                <td class="left">
                                                    <strong>Total</strong>
                                                </td>
                                                <td class="right">
                                                    <strong>${total-(total*10/100)}₫</strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="center">                                           

                                                    <input type="submit" class="btn btn-md btn-success" value="Pay Now">
                                                </td>

                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>