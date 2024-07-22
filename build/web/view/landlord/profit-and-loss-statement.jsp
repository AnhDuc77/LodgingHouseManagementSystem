
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<!DOCTYPE html>
<html lang="en">
    <style>.sub-menu {
            display: none;
        }

        #piechart {
            width: 200px;
            height: 200px;

            top: 300px; /* Cách đỉnh màn hình 20px */
            right: 20px; /* Cách phải màn hình 20px */
        }

        /* Định dạng lại các cột */
        .chart-col {
            padding: 15px;
        }

    </style>
    <head>
        <title>Báo cáo lợi nhuận</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resource/doc/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

    </head>

    <body  class="app sidebar-mini rtl">
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">
                <!-- User Menu-->
                <li><a class="app-nav__item" href="/ManageLodgingHouse/LoginServlet?service=logout"><i class='bx bx-log-out bx-rotate-180'></i> </a>
                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <hr>
            <ul class="app-menu">
                <li>
                    <a id="notificationLink" href="list-notification-landlord" class="app-menu__item">
                        <i class='app-menu__icon bx bx-id-card'></i>
                        <span class="app-menu__label">Thông Báo Mới</span>
                        <span class="badge">${sessionScope.NumberOfNotification}</span> <!-- Example: Replace with dynamic content -->
                    </a>
                </li>
                <li><a class="app-menu__item " href="home-controller"><i class='app-menu__icon bx bx-tachometer'></i><span
                            class="app-menu__label">Bảng điều khiển</span></a></li>
                <li><a class="app-menu__item " href="/ManageLodgingHouse/management-lodging-houses?index=1"><i class='app-menu__icon bx bx-id-card'></i> <span
                            class="app-menu__label">Quản lí các khu trọ</span></a></li>

                <li><a class="app-menu__item " href="table-data-table.html"><i class='app-menu__icon bx bx-id-card'></i> <span
                            class="app-menu__label">Dịch vụ</span></a></li>
                <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-user-voice'></i><span
                            class="app-menu__label">Chỉ số điện</span></a></li>
                <li><a class="app-menu__item" href="table-data-product.html"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a>
                </li>
                <li><a class="app-menu__item" href="table-data-oder.html"><i class='app-menu__icon bx bx-task'></i><span
                            class="app-menu__label">Chỉ số nước</span></a></li>
                <li><a class="app-menu__item" href="table-data-banned.html"><i class='app-menu__icon bx bx-run'></i><span
                            class="app-menu__label">Quản lý nội bộ
                        </span></a></li>
                <li><a class="app-menu__item" href="table-data-money.html"><i class='app-menu__icon bx bx-dollar'></i><span
                            class="app-menu__label">Phát sinh</span></a></li>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

                <li>
                    <a class="app-menu__item active" id="menu-item" href="quan-ly-bao-cao.html">
                        <i class='app-menu__icon bx bx-pie-chart-alt-2'></i>
                        <span class="app-menu__label">Báo cáo doanh thu</span>
                    </a>
                    <ul class="sub-menu">
                        <li><a href="#" style="color:white">Mục con 1</a></li>
                        <li><a href="#">Mục con 2</a></li>
                        <li><a href="#">Mục con 3</a></li>
                    </ul>
                </li>
                <li><a class="app-menu__item" href="page-calendar.html"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Tính tiền </span></a></li>
                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/type-of-investment-costs-servlet"><i class='app-menu__icon bx bx-id-card'></i>
                        <span class="app-menu__label">Quản lý loại phí đầu tư</span></a></li>                      
                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/list-role-of-staff"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i>
                        <span class="app-menu__label">Quản lý chức vụ</span></a></li>
                <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-cog'></i><span class="app-menu__label">Cài
                            đặt hệ thống</span></a></li>
            </ul>
        </aside>
        <main class="app-content">
            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
            <div  style="text-align: center"><h1>Báo cáo lợi nhuận</h1></div>
            <div class="row date-range-form" style="margin-bottom: 10px">
                <form id="searchForm" action="search-report" method="GET">
                    <div class="col-md-12">
                        <label for="select-year">Năm</label>
                        <select class="form-control" id="select-year" name="year">
                            <c:forEach items="${listYear}" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>

        </div>                  
        <div class="">
            <div class="row">
                <div class="col-md-12 row">

                    <div class=" col-md-12 tile">
                        <h3 class="tile-title">Tổng lợi nhuận</h3>
                        <div id="chart_div" style="width: 100%; height: 300px;"></div>
                    </div>
                </div>
                <div class="col-md-12 row">
                    <div class=" col-md-3 tile"style="margin-right: 30px">
                        <h3 class="tile-title">Báo cáo chi tiết</h3>

                        <!-- Hiển thị thông báo lợi nhuận cao nhất và lỗ thấp nhất -->
                        <p> Tổng chi phí thu:<fmt:formatNumber type="number" value="${totalPrice}" pattern="#,##0"/> VND </p>

                        <p>Tổng chi phí chi: <fmt:formatNumber type="number" value="${totalPriceCost}" pattern="#,##0"/> VND</p>
                    </div>
                    <div class=" col-md-8 tile">
                        <h3 class="tile-title">Tổng thu chi </h3>
                        <div id="piechart1" style="width: 100%; height: 400px;"></div>
                    </div>
                </div>
            </div>
        </div>
    </main>


    <!--===============================================================================================-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <!-- Đảm bảo bao gồm thư viện jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script>
        document.getElementById('select-year').addEventListener('change', function () {
            document.getElementById('searchForm').submit();
        });
    </script>

    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

            var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['Tổng thu', ${totalPrice}],
                ['Tổng chi',${totalPriceCost}]
            ]);

            var options = {
                title: 'Tổng thu chi trước thuế'
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart1'));

            chart.draw(data, options);
        }
    </script>
    <script>
           google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(drawBasic);

        function drawBasic() {
            var data = new google.visualization.DataTable();
            data.addColumn('number', 'X');
            data.addColumn('number', 'Lợi nhuận');

            var rows = [
        <% if (request.getAttribute("profitInYear") != null) {
                Map<Integer, Integer> priceByDate1 = (Map<Integer, Integer>) request.getAttribute("profitInYear");
                for (Map.Entry<Integer, Integer> entry : priceByDate1.entrySet()) {
                    int day = entry.getKey();
                    int price = entry.getValue();
        %>
                [<%= day %>, <%= price %>],
        <% } } %>
            ];

            data.addRows(rows);

            var options = {
                title: 'Biểu đồ của năm',
                hAxis: {
                    title: 'Tháng trong năm'
                },
                vAxis: {
                    title: 'Số tiền'
                }
            };

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.app-menu__item').click(function (e) {
                e.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a

                var $submenu = $(this).next('.sub-menu'); // Tìm submenu liền sau mục được click

                $('.sub-menu').not($submenu).slideUp(); // Đóng tất cả các submenu khác
                $submenu.slideToggle(); // Mở hoặc đóng submenu của mục được click
            });
        });
    </script>


</body>

</html>
