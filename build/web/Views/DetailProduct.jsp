<%-- 
    Document   : DetailProduct
    Created on : Jun 29, 2022, 11:05:41 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${detail.name}</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../Views/Header.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="HomePageController">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">Category</a></li>
                                <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                <jsp:include page="../Views/Left.jsp"></jsp:include>
                    <div class="col-sm-9">
                        <div class="container">
                            <div class="card">
                                <div class="row">
                                    <aside class="col-sm-5 border-right">
                                        <article class="gallery-wrap"> 
                                            <div class="img-big-wrap">
                                                <div> <a href="#"><img src="${detail.image}"></a></div>
                                        </div> 

                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="num">${detail.price}</span><span class="currency"> đ</span>
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->


                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <form action="CartController" method="post">
                                                    <input type="hidden" value="${detail.id}" name="id">
                                                    <c:choose>
                                                        <c:when test="${detail.stocking}">
                                                            <dl class="param param-inline">

                                                                <dt>Quantity: </dt>
                                                                <dd>
                                                                    <input type="number" size="10" name="quantity" min="1"  value="1" class="tc item-quantity">
                                                                </dd>
                                                            </dl>  <!-- item-property .// --></c:when>
                                                        <c:otherwise>
                                                            <p>Hết hàng</p>
                                                        </c:otherwise>                  
                                                    </c:choose>
                                                    <br>
                                                    <c:choose>
                                                        <c:when test="${sessionScope.userlogin!=null&&sessionScope.userlogin.authority==0}">
                                                            <a href="UpdateProductController?update=${detail.id}"  class="btn btn-info btn-lg" data-toggle="modal"><i class="glyphicon glyphicon-pencil"  title="Edit">Edit Product</i></a>
                                                        </c:when>
                                                        <c:when test="${sessionScope.userlogin==null}">
                                                            <a href="LoginController"  class="btn btn-lg btn-primary text-uppercase" data-toggle="modal"><i class="glyphicon glyphicon-pencil"  title="Edit">BUY NOW</i></a>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <c:if test="${detail.stocking}">                                                            
                                                                <input type="submit" value="BUY NOW" class="btn btn-lg btn-primary text-uppercase" >
                                                            </c:if>
                                                        </c:otherwise> 
                                                    </c:choose>

                                                </form>
                                            </div> <!-- col.// -->                                           
                                        </div> <!-- row.// -->
                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->
                    </div>
                    <div class="container" style="margin-top: 30px;">
                        <div class="card">
                            <div class="card-header">
                                <h5>Description</h5>
                            </div>
                            <div class="card-body">
                                ${detail.description}
                            </div>
                            
                        </div>
                    </div>



                </div>
            </div>
        </div>
        <script>

        </script>                                           
        <jsp:include page="../Views/Footer.jsp"></jsp:include>
    </body>
</html>
