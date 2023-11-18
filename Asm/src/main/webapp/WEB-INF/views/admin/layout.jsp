<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assignment_SOF3021_Java5</title>
    <!-- Favicon-->
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/../WEB-INF/views/admin/css/styles.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" />
</head>
<body>
<div class="d-flex " id="wrapper">
    <!-- Sidebar-->
    <div class="border-end bg-white" id="sidebar-wrapper">
        <div class="list-group list-group-flush">
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/san-pham/index">Sản phẩm</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/ctsp/index">Chi tiết Sản phẩm</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/dong-sp/index">Dòng sản phẩm</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/khach-hang/index">Khách Hàng</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/nhan-vien/index">Nhân Viên</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/cua-hang/index">Cửa Hàng</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/mau-sac/index">Màu sắc</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/nsx/index">Nhà sản xuất</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="/admin/chuc-vu/index">Chức vụ</a>
        </div>
    </div>
    <!-- Page content wrapper-->
    <div class="container-fluid">
        <div class="alert text-center mt-3">
            <p>${thongBao}</p>
        </div>
        <jsp:include page="${ view }"/>
    </div>

</div>
<div class="bg-success" style="min-height: 200px">
    <div class="row">
        <p>
        <h1 class="text-center text-white"></h1>
        </p>
    </div>
</div>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="/../js/bootstrap.min.js"></script>
<!-- Core theme JS-->
<script src="/../js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

<script>
    window.addEventListener('DOMContentLoaded', event => {
        const sidebarToggle = document.body.querySelector('#sidebarToggle');
        if (sidebarToggle) {
            sidebarToggle.addEventListener('click', event => {
                event.preventDefault();
                document.body.classList.toggle('sb-sidenav-toggled');
                localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
            });
        }

    });
</script>
</body>

</html>
