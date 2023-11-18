<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <c:forEach var="ctsp" items="${list}" varStatus="status">
            <div class="col-6">
                <img src="${ctsp.sanPham.anh}" alt="" height="500px" width="500px">
            </div>
            <div class="col-6 justify-content-center">
                <h3>${ctsp.sanPham.ten}</h3>
                <p class="card-text mau-sac">Màu sắc :${ctsp.mauSac.ten}</p>
                <p class="card-text mo-ta">Mô tả: ${ctsp.moTa}</p>
                <form novalidate method="POST" action="/user/gio-hang/${ctsp.id}">
                    <div class="input-group mb-5 mt-5">
                        <p class="mt-1 fw-bold">Số lượng</p>

                        <button onclick="decrease(event)" class="btn btn-success ms-2">-</button>
                        <input type="number" class="form-control" id="quantity" name="soLuong" value="1"
                               max="${ctsp.soLuongTon}">
                        <button onclick="increase(event)" class="btn btn-success">+</button>

                        <p class="mt-1 text-danger ms-5"> còn ${ctsp.soLuongTon} sản phẩm</p>
                    </div>
                    <h3 class="fw-bold mb-5 text-center">${ctsp.giaBan} VND</h3>
                    <div class="row">
                        <button class="col-6 offset-3 btn text-white btn-success w-50" type="submit">
                            <img src="img/icons8-shopping-cart-30.png" class="img-fluid" alt="">
                            Thêm vào giỏ hàng
                        </button>
                    </div>
                </form>
            </div>
        </c:forEach>
    </div>
    <h3 class="fw-bold mt-5 text-center">Các sản phẩm tương tự</h3>
    <div class="row col-8 offset-2 justify-content-center">
        <c:forEach var="ctsp" items="${listByCategoty}" varStatus="status" begin="0" end="2">
            <div class="col-md-3 col-12">
                <div class="card">
                    <img src="${ ctsp.sanPham.anh }" class="card-img-top img-fluid" alt="...">
                    <div class="card-body">
                        <a href="/user/chi-tiet-san-pham/${ ctsp.id }"
                           class="text-decoration-none fw-bold text-dark">
                            <p class="card-text text-center">${ ctsp.sanPham.ten }</p>
                        </a>
                        <span class="card text text-center fw-bold">${ ctsp.giaBan }</span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    // function increase(event) {
    //     event.preventDefault();
    //     var quantity = document.getElementById("quantity");
    //     var currentValue = parseInt(quantity.value);
    //     var maxValue = parseInt(quantity.getAttribute("max"));
    //     if (currentValue < maxValue) {
    //         quantity.value = currentValue + 1;
    //     }
    // }
    //
    // function decrease(event) {
    //     event.preventDefault();
    //     var quantity = document.getElementById("quantity");
    //     if (quantity.value > 1) {
    //         quantity.value = parseInt(quantity.value) - 1;
    //     }
    // }
</script>
</body>
</html>