<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Quản Lý Tài Sản</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resource/doc/css/main.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            .avatar{
                width: 200px !important;
                height: 200px !important;
                border-radius: 50% !important;
                object-fit: cover !important;
            }
            .avatarHeader{
                width: 100px !important;
                height: 100px !important;
                border-radius: 50% !important;
                object-fit: cover !important;
            }
            .badge {
                display: inline-block;
                padding: 7px;
                font-size: 12px;
                font-weight: 500;
                line-height: 1;
                text-align: center;
                white-space: nowrap;
                vertical-align: baseline;
                border-radius: 0.25rem;
                color: white;
            }

            .form-control {
                width: 100%;
                box-sizing: border-box;

            }
            .styled-select {
                width: 100px;
                padding: 6px;
                font-size: 14px;
                line-height: 1.2;
                border: 1px solid #ccc;
                border-radius: 4px;
                background-color: #fff;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                background-repeat: no-repeat;
                background-position: right 10px center;
                background-size: 16px 16px;
            }
            .styled-select::-ms-expand {
                display: none;
            }
        </style>
    </head>

    <body onload="time()" class="app sidebar-mini rtl">
    <!-- Navbar-->
    <!-- Sidebar menu-->
    <header class="app-header">
        <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
        <!-- Navbar Right Menu-->
        <ul class="app-nav">
            <!-- User Menu-->
            <li><a class="app-nav__item" href="/ManageLodgingHouse/LoginServlet?service=logout"><i class='bx bx-log-out bx-rotate-180'></i> </a>
            </li>
        </ul>
    </header>
    <%@ include file="left-menu-manager.jsp" %>
    <main class="app-content">
        <div class="app-title">
            <ul class="app-breadcrumb breadcrumb side">
                <li class="breadcrumb-item active"><a href="asset?service=manageAsset"><b>Quản Lý Tài Sản</b></a></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <div class="tile-body">
                        <div class="row element-button">
                            <div class="col-sm-2">
                                <a href="asset?service=manageAsset" class="badge bg-danger">Trở Về</a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <h3>Phòng có tài sản</h3>
                                <table class="table table-hover table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Room ID</th>
                                            <th>Room Name</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${roomsWithAsset}" var="room">
                                            <tr>
                                                <td>${room.roomId}</td>
                                                <td>${room.roomName}</td>
                                                <td>
                                                    <a href="asset?service=removeAssetFromRoom&roomId=${room.roomId}&assetId=${assetId}" method="post" class="badge bg-danger">Remove</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-6">
                                <h3>Phòng không có tài sản</h3>
                                <table class="table table-hover table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Room ID</th>
                                            <th>Room Name</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${roomsWithoutAsset}" var="room">
                                            <tr>
                                                <td>${room.roomId}</td>
                                                <td>${room.roomName}</td>
                                                <td>
                                                    <a href="asset?service=addAssetIntoRoom&roomId=${room.roomId}&assetId=${assetId}" method="post" class="badge bg-primary">Add</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>


<!-- Essential javascripts for application to work-->
<script src="https://esgoo.net/scripts/jquery.js"></script>
<script src="${pageContext.request.contextPath}/Resource/doc/js/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/Resource/doc/js/popper.min.js"></script>
<script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/Resource/doc/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/Resource/doc/js/main.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/Resource/js/profile.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/Resource/doc/js/plugins/pace.min.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.request.contextPath}/Resource/doc/js/plugins/chart.js"></script>
<script type="text/javascript">
        //Thời Gian
        function time() {
            var today = new Date();
            var weekday = new Array(7);
            weekday[0] = "Chủ Nhật";
            weekday[1] = "Thứ Hai";
            weekday[2] = "Thứ Ba";
            weekday[3] = "Thứ Tư";
            weekday[4] = "Thứ Năm";
            weekday[5] = "Thứ Sáu";
            weekday[6] = "Thứ Bảy";
            var day = weekday[today.getDay()];
            var dd = today.getDate();
            var mm = today.getMonth() + 1;
            var yyyy = today.getFullYear();
            var h = today.getHours();
            var m = today.getMinutes();
            var s = today.getSeconds();
            m = checkTime(m);
            s = checkTime(s);
            nowTime = h + " giờ " + m + " phút " + s + " giây";
            if (dd < 10) {
                dd = '0' + dd
            }
            if (mm < 10) {
                mm = '0' + mm
            }
            today = day + ', ' + dd + '/' + mm + '/' + yyyy;
            tmp = '<span class="date"> ' + today + ' - ' + nowTime +
                    '</span>';
            document.getElementById("clock").innerHTML = tmp;
            clocktime = setTimeout("time()", "1000", "Javascript");

            function checkTime(i) {
                if (i < 10) {
                    i = "0" + i;
                }
                return i;
            }
        }
</script>
<script>
    function validateAddForm() {
        return validateForm('assetName', 'description', 'status', 'price', 'quantity', 'lodgingHouseId');
    }

    function validateEditForm() {
        return validateForm('editAssetName', 'editDescription', 'editStatus', 'editPrice', 'editQuantity', 'editLodgingHouseId');
    }

    function validateForm(nameId, descriptionId, statusId, priceId, quantityId, lodgingHouseId) {
        var name = document.getElementById(nameId).value;
        var description = document.getElementById(descriptionId).value;
        var status = document.getElementById(statusId).value;
        var price = document.getElementById(priceId).value;
        var quantity = document.getElementById(quantityId).value;
        var lodgingHouse = document.getElementById(lodgingHouseId).value;

        if (name === '' || description === '' || status === '' || price === '' || quantity === '' || lodgingHouse === '') {
            alert('Vui lòng điền đầy đủ thông tin.');
            return false;
        }

        if (isNaN(price) || isNaN(quantity)) {
            alert('Giá và số lượng phải là số.');
            return false;
        }

        if (price <= 0 || quantity <= 0) {
            alert('Giá và số lượng phải lớn hơn 0.');
            return false;
        }

        return true;
    }
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var yearSelect = document.getElementById('select-year');
        var monthSelect = document.getElementById('select-month');
        var monthSelectContainer = document.getElementById('month-select-container');

        // Khi chọn năm
        yearSelect.addEventListener('change', function () {
            var year = yearSelect.value;
            var month = monthSelect.value;

            if (year && month) {
                var url = 'asset?service=showManageAssets';
                url += '&year=' + year;
                url += '&month=' + month;
                window.location.href = url;
            }
        });

        // Khi chọn tháng
        monthSelect.addEventListener('change', function () {
            var year = yearSelect.value;
            var month = monthSelect.value;

            if (year && month) {
                var url = 'asset?service=showManageAssets';
                url += '&year=' + year;
                url += '&month=' + month;
                window.location.href = url;
            }
        });
    });
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        // Function to get URL parameter
        function getParameterByName(name, url = window.location.href) {
            name = name.replace(/[\[\]]/g, '\\$&');
            var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
                    results = regex.exec(url);
            if (!results)
                return null;
            if (!results[2])
                return '';
            return decodeURIComponent(results[2].replace(/\+/g, ' '));
        }

        // Set selected value for year
        var selectedYear = getParameterByName('year');
        if (selectedYear) {
            document.getElementById('select-year').value = selectedYear;
        }

        // Set selected value for month
        var selectedMonth = getParameterByName('month');
        if (selectedMonth) {
            document.getElementById('select-month').value = selectedMonth;
        }
    });
</script>
<script>
    $('#editAssetModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var assetId = button.data('asset-id');
        var assetName = button.data('asset-name');
        var description = button.data('description');
        var status = button.data('status');
        var price = button.data('price');
        var quantity = button.data('quantity');
        var lodgingHouseId = button.data('lodging-house-id');

        var modal = $(this);
        modal.find('#editAssetId').val(assetId);
        modal.find('#editAssetName').val(assetName);
        modal.find('#editDescription').val(description);
        modal.find('#editStatus').val(status);
        modal.find('#editPrice').val(price);
        modal.find('#editQuantity').val(quantity);
        modal.find('#editLodgingHouseId').val(lodgingHouseId);
    });
</script>

</body>

</html>
