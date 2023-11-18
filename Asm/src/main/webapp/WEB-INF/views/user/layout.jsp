<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/../WEB-INF/css/bootstrap.min.css">
    <style>
        .card {
            border: none;
        }

        footer {
            background-color: #e6dbb9;
            color: #000000;
        }

        .shopping-cart {
            position: relative;
            display: inline-block;
            cursor: pointer;
        }

        .shopping-cart i {
            font-size: 2rem;
            color: blue;
        }

        .shopping-cart .cart-items {
            color: red;
            padding: 0.375rem 0.75rem;
            border-radius: 50%;
            position: absolute;
            top: -8px;
            right: -8px;
            font-size: 1.125rem;
        }
    </style>
</head>
<body>
<header class="d-flex align-items-center">
    <div class="col-2 mt-3">
        <a class="navbar-brand col-2 fw-bold " href="/user/san-pham">
            <img class="img-fluid w-50" src="/../images/logo1.png" alt="Logo">
        </a>
    </div>
    <form class="d-flex col-4 mt-3" role="search">
        <input class="form-control" type="search" placeholder="Tìm kiếm" aria-label="Search">
        <button class="btn btn-outline-primary" type="submit">
            <svg xmlns="http://www.w3.org/2000/svg" width="46" height="16" fill="currentColor" class="bi bi-search"
                 viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
            </svg>
        </button>
    </form>
    <nav class="navbar navbar-expand-lg bg-body-tertiary col-6 d-flex justify-content-end">

        <c:if test="${user == null}">
            <a class="nav-link" class="btn btn-info"
               href="/admin/san-pham/index">
                Quản lý
            </a>
        </c:if>
        <c:if test="${user != null}">
            <a class="nav-link active me-3" aria-current="page" href="/user/hoa-don/trang-cua-toi">Trang của tôi</a>
        </c:if>
        <c:if test="${user != null}">
            <div class="shopping-cart d-flex align-items-center">
                <a href="/user/gio-hang" class="btn btn-primary">
                    <img src="/../images/icons8-shopping-cart-30.png" alt="Shopping cart"></a>
            </div>
        </c:if>
    </nav>
</header>
<%--  --%>
<div class="row mt-3 mb-3">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand active fw-bold" href="/user/san-pham">Trang chủ</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link" href="#product">Sản phẩm</a>
                    <a class="nav-link" href="#introduce">Giới thiệu cửa hàng</a>
                    <a class="nav-link" href="#event">Sự kiện</a>
                </div>
                <div class="navbar-nav ms-auto">
                    <c:if test="${user != null}">
                        <span class="text-success mt-2">Xin chào</span>
                        <a class="nav-link" href="#profile">
                                ${user.getHo()} ${user.getTenDem()} ${user.getTen()}
                        </a>
                        <a class="nav-link" href="/user/logout">Đăng xuất</a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</div>
<%-- content --%>
<div class="container">
    <jsp:include page="${ banner }"/>
    <div>
        <jsp:include page="${view}"/>
    </div>
</div>

<footer class="row mt-5 ">
    <div class="row">
        <div>
            <div class="row col-7 ms-4 ">
                <p class="mt-3 ms-5"><img src="/../images/logo2.png" alt="#" width="150px" height="150px"></p>
                <a href="#" class="facebook col-1">
                    <img src="/../images/icons8-facebook-48.png" class="img-fluid" alt="">
                </a>
                <a href="#" class="instagram col-1">
                    <img src="/../images/icons8-instagram-48.png" class="img-fluid" alt="">
                </a>
                <a href="#" class="youtube col-1">
                    <img src="/../images/icons8-youtube-48.png" class="img-fluid " alt="">
                </a>
                <a href="#" class="zalo col-1">
                    <img src="/../images/icons8-zalo-48.png" class="img-fluid " alt="">
                </a>
            </div>
            <hr>
            <ul class="row col-11 list-unstyled ms-4">
                <li class="col-3">
                    <a class="text-decoration-none  text-black fs-6">Chính sách giao hàng và
                        thanh toán</a>
                </li>
                <li class="col-3">
                    <a class="text-decoration-none text-black ">|</a>
                    <a href="" class="text-decoration-none background: text-black  ">Chính sách bảo mật thông tin
                        khách hàng</a>
                </li>
                <li class="col-2">
                    <a href="" class="text-decoration-none text-black ">|</a>
                    <a href="" class="text-decoration-none text-black">Chính sách mua hàng</a>
                </li>
                <li class="col-2">
                    <a class="text-decoration-none text-black ">|</a>
                    <a href="" class="text-decoration-none  text-black">Chính sách trả hàng</a>
                </li>
            </ul>
            <ul class="row list-unstyled ms-4">
                <li>
                    Thời gian làm việc
                    <span>: Thứ 2 ~ Thứ 6 09:00 ~ 17:00 (trừ Thứ 7, Chủ Nhật và ngày lễ)</span>
                </li>
                <li>
                    Tư vấn khách hàng
                    <a href="tel:02838279777" class="text-decoration-none text-black">: 028 3827 9777</a>
                </li>
                <li>
                    Email
                    <span><a href="#" class="text-decoration-none text-black">:
                        </a></span>
                </li>
            </ul>
        </div>
    </div>
</footer>
<script src="/../WEB-INF/js/bootstrap.min.js"></script>
<script src="/../WEB-INF/js/jquery.min.js"></script>
<script src="/../WEB-INF/js/popper.js"></script>
</body>
</html>