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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Document</title>
</head>
<body>
<c:if test="${ f:length(sanPham.content) == 0 }">
    <h4 class="text-center">Không có dữ liệu</h4>
</c:if>
<c:if test="${ f:length(sanPham.content) != 0 }">
<div class="container">
    <div class="row text-center mt-4 mb-4">
        <h2>Sản phẩm bán chạy nhất</h2>
    </div>
    <!-- Sản phẩm -->
    <div class="row">
        <c:forEach var="ctsp" items="${sanPham.content}" varStatus="status" begin="0" end="3">
            <div class="col-md-3 col-12">
                <div class="card">
                    <img src="${ ctsp.sanPham.anh }" class="card-img-top img-fluid w50" alt="...">
                    <div class="card-body">
                        <a href="/user/chi-tiet-san-pham/${ ctsp.id } "
                           class="text-decoration-none fw-bold text-dark">
                            <p class="card-text text-center">${ ctsp.sanPham.ten }</p>
                        </a>
                        <span class="card text text-center fw-bold">${ ctsp.giaBan } VND</span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <nav aria-label="Page navigation example" class="mt-4">
        <ul class="pagination justify-content-end">
            <c:forEach begin="0" end="${sanPham.totalPages - 1}" varStatus="loop">
                <li class="page-item">
                    <a class="page-link" href="/user/san-pham?page=${loop.begin + loop.index}">
                            ${loop.begin + loop.index + 1}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>
    </c:if>
</div>
</body>
</html>