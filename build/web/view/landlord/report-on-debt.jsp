<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Danh sách các chi phí đầu tư</title>
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
        <style>
            .pagination {
                display: inline-block;
                position: sticky;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
        </style>
    </head>
    <body onload="time()" class="app sidebar-mini rtl">
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">
                <!-- User Menu-->
                <li><a class="app-nav__item" href="/index.html"><i class='bx bx-log-out bx-rotate-180'></i> </a>
                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="/images/hay.jpg" width="50px"
                                                alt="User Image">

                <div>

                    <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
                </div>

            </div>
            <hr>
            <ul class="app-menu">
                <c:if test="${sessionScope.account.getRoleId() == 1}">
                    <li><a class="app-menu__item active" href="home-controller"><i class='app-menu__icon bx bx-tachometer'></i><span class="app-menu__label">Quản các phòng</span></a></li>
                            </c:if>
                            <c:if test="${sessionScope.account.getRoleId() == 2}">
                    <li><a class="app-menu__item active" href="home-manager?LodgingHouseID=3"><i class='app-menu__icon bx bx-tachometer'></i><span class="app-menu__label">Quản lí các phòng</span></a></li>
                            </c:if>
                <li><a class="app-menu__item" href="management-lodging-houses"><i class='app-menu__icon bx bx-id-card'></i><span class="app-menu__label">Quản lí thu chi</span></a></li>             
                <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-user-voice'></i><span class="app-menu__label">Chỉ số điện</span></a></li>
                <li><a class="app-menu__item" href="table-data-product.html"><i class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a></li>
                <li><a class="app-menu__item" href="table-data-oder.html"><i class='app-menu__icon bx bx-task'></i><span class="app-menu__label">Chỉ số nước</span></a></li>
                <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-cog'></i><span class="app-menu__label">Cài đặt hệ thống</span></a></li>

                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/investment-costs-servlet"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lí khoản phí đầu tư</span></a>
                </li> 
                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/list-staff"><i class='app-menu__icon bx bx-id-card'></i>
                        <span class="app-menu__label">Quản lý nhân viên</span></a></li>
            </ul>
        </aside>
        <main class="app-content">

            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Quản lí công nợ</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">                          
                            <form class="form-row" action="report-on-debt" method="post">
                                <div class="form-group col-md-4">
                                    <label class="control-label">Năm</label>
                                    <select class="form-control" id="year" required="" name="year">
                                        <c:forEach items="${requestScope.listYear}" var="c">
                                            <option value="${c}">${c}</option>
                                        </c:forEach>
                                    </select>
                                </div>        

                                <div class="form-group col-md-4">
                                    <label class="control-label">Tháng</label>
                                    <select class="form-control" id="month" required="" name="month">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                        <option value="9">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                    </select>
                                </div>
                                <c:if test="${sessionScope.account.getRoleId() == 1}">
                                    <div class="form-group col-md-4">
                                        <label class="control-label">Tên trọ</label>
                                        <select class="form-control" id="lodgingHouse" required="" name="lodgingHouse">
                                            <option value="0">Tất cả</option>
                                            <c:forEach items="${requestScope.listLodgingHouse}" var="c">
                                                <option value="${c.getLodgingHouseId()}">${c.getNameLodgingHouse()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>
                                <div class="form-group col-md-12">
                                    <button class="btn btn-save" id="submitButton" type="submit" style="margin-right: 10px;">Thống kê</button>
                                </div>
                            </form>
                            <div class="row">
                                <canvas class="col-md-4" id="myChart" style="width: 100%; max-width: 600px; height:500px;"></canvas>   
                                <div  class="col-md-4" id="myChart1" style="width:100%; max-width:600px; height:500px;"></div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </main>




        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="src/jquery.table2excel.js"></script>
        <script src="js/main.js"></script>
        <!-- The javascript plugin to display page loading on top-->
        <script src="js/plugins/pace.min.js"></script>
        <!-- Page specific javascripts-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin-->
        <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">$('#sampleTable').DataTable();</script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>



        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://www.gstatic.com/charts/loader.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function () {
            var debtJson = '<%= request.getAttribute("debt1") %>';
            var debtData = JSON.parse(debtJson);

            console.log(debtData);
            var label = '<%= request.getAttribute("label1") %>';
            var labelData = JSON.parse(label);

            // Sắp xếp các tháng
            const monthOrder = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
            const sortedDebtData = monthOrder.map(month => [month, debtData[month] || 0]);

            google.charts.load('current', {packages: ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                // Set Data
                const data = google.visualization.arrayToDataTable([
                    ['Tháng', 'Tiền nợ (VND)'],
                    ...sortedDebtData
                ]);

                // Set Options
                const options = {
                    title: labelData,
                    hAxis: {title: 'Tháng'},
                    vAxis: {title: 'Tiền nợ (VND)'},
                    legend: 'none'
                };

                // Draw
                const chart = new google.visualization.LineChart(document.getElementById('myChart1'));
                chart.draw(data, options);
            }
        });
        </script>

        <script>

            var debtJson = '<%= request.getAttribute("debtAllLodgingHouseJson") %>';
            var year = '<%= request.getAttribute("yearJson") %>';
            var month = '<%= request.getAttribute("monthJSon") %>';
            var lodgingName = '<%= request.getAttribute("lodgingHouseName") %>';
            var debtData = JSON.parse(debtJson);
            var yearData = JSON.parse(year);
            var monthData = JSON.parse(month);
            var lodgingNameData = JSON.parse(lodgingName);
            if (yearData != null && monthData != null && lodgingNameData != null) {

                document.getElementById('year').value = yearData;
                document.getElementById('month').value = monthData;
                document.getElementById('lodgingHouse').value = lodgingNameData;
            }


            console.log(debtData);
            var lable = '<%= request.getAttribute("lableJson") %>';
            var lableData = JSON.parse(lable);
            const xValues = Object.keys(debtData);
            const yValues = Object.values(debtData).map(value => value + " VND");
            const barColors = ["red", "green", "blue", "orange", "brown"];
            new Chart("myChart", {
                type: "bar",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: Object.values(debtData)
                        }]
                },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: lableData
                    },
                    scales: {
                        yAxes: [{
                                ticks: {
                                    callback: function (value, index, values) {
                                        return value + ' VND'; // Thêm đơn vị đo vào nhãn trục y
                                    }
                                },
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Tiền nợ (VND)'
                                }
                            }]
                    }
                }
            });
        </script>
        <script>
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
    </body>

</html>