<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Long</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>


    <body style="background: url(//theme.hstatic.net/1000288298/1000372327/14/wrap-bg-body.png?v=304) repeat;">
        <!--begin of menu-->
        <jsp:include page="../Views/Header.jsp"></jsp:include>          
            <!--end of menu-->
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="HomePageController">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">Category</a></li>
                                <li class="breadcrumb-item active" aria-current="#">All Category</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                <jsp:include page="../Views/Left.jsp"></jsp:include>   
                    <div class="col-sm-9">
                        <div class="row justify-content-center align-items-center" style="margin-bottom: 20px;">
                            <div class="">
                                <form action="${servlet}" method="post">
                                <input type="hidden" value="${currentNum}" name="num">
                                Sort by:    <select name="sort">
                                    <option value="cunhat" ${currentSort=="cunhat"?"selected":""}>Cũ nhất</option>
                                    <option value="moinhat"${currentSort=="moinhat"?"selected":""}>Mới nhất</option>                                       
                                    <option value="tangdan"${currentSort=="tangdan"?"selected":""}>Giá tăng dần</option>
                                    <option value="giamdan"${currentSort=="giamdan"?"selected":""}>Giá giảm dần</option>
                                    <option value="az"${currentSort=="az"?"selected":""}>A-Z</option>
                                    <option value="za"${currentSort=="za"?"selected":""}>Z-A</option>
                                </select>
                                <input type="submit" value="Sort" class="btn btn-dark">
                            </form>

                        </div>
                    </div>
                    <div class="row">
                        <c:if test="${empty plist}">

                            <h1 style="text-align: center;">Không có sản phẩm</h1>

                        </c:if>
                        <c:if test="${!empty plist}">


                            <c:forEach items="${plist}" var="o">

                                <div class="col-12 col-md-6 col-lg-4 product ">
                                    <div class="card" style="height: 450px; margin-bottom: 20px;">
                                        <a href="DetailProductController?pid=${o.id}"> <img class="card-img-top" src="${o.image}" width="100%" height="253px"alt="Card image cap"></a>
                                        <div class="card-body">
                                            <div style="height: 50px; margin-bottom: 20px;"><p class="card-title show_txt" style="overflow: hidden;"><a  href="DetailProductController?pid=${o.id}" title="View Product" >${o.name}</a></p></div>
                                            <p class=" show_txt text-center ${o.stocking?"text-success bg-light":"text-danger bg-warning"}">${o.stocking?"Còn hàng":"Hết hàng"}</p>
                                            <div class="row">
                                                <div class="col">
                                                    <p class="btn btn-danger btn-block">${o.price} đ</p>
                                                </div>
                                                <div class="col">
                                                    <a href="DetailProductController?pid=${o.id}" class="btn btn-success btn-block">Detail</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                        <div class="row justify-content-center align-items-center">
                            <nav aria-label="Page navigation example" class="text-center">
                                <form action="${servlet}" method="post">
                                    <input type="hidden" value="${currentSort}" name="sort">
                                    <input type="text" name="num" style="margin-bottom: 20px;" maxlength="10" size="1" value="${currentNum}"> Products in 1 page
                                    <input type="submit" value="Adjust">
                                </form>
                                <c:set scope="request" var="size" value="${plist.size()}"></c:set>                                   
                                    <ul class="pagination">
                                    <c:if test="${currentPage>1}">
                                        <li class="page-item"><a class="page-link" href="${servlet}?page=${currentPage-1}&sort=${currentSort}&num=${currentNum}">Previous</a></li>
                                        </c:if>                               
                                        <c:forEach begin="1" end="${endP}" var="i">
                                            <c:if test="${size==currentNum}">
                                            <li class="page-item"><a class="page-link" href="${servlet}?page=${i}&sort=${currentSort}&num=${currentNum}">${i}</a></li>
                                            </c:if>

                                    </c:forEach>
                                    <c:if test="${currentPage!=endP}">
                                        <li class="page-item"><a class="page-link" href="${servlet}?page=${currentPage+1}&sort=${currentSort}&num=${currentNum}">Next</a></li>
                                        </c:if>


                                </ul>

                            </nav>
                        </div>
                    </c:if> 
                </div>
            </div>

            <jsp:include page="../Views/Footer.jsp"></jsp:include>
    </body>
</html>

