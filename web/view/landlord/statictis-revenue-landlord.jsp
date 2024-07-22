<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Quản Lý Phản Hồi</title>
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
            .rating {
                position: relative;
                display: flex;
            }

            .rating {
                display: flex;
                gap: 8px; /* Adjust spacing between stars if needed */
            }

            .star {
                width: 40px;
                height: 40px;
                display: inline-block;
                background: url('https://icon-library.com/images/star-png-icon/star-png-icon-0.jpg') no-repeat center center;
                background-size: contain;
                position: relative;
            }

            .star::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK_x0xsTpZtA8UkG6hGE7Y8E-EXVXwT8NWbY0KymT2o_0pxmI&s') no-repeat center center;
                background-size: contain;
                clip-path: polygon(0 0, var(--percent, 0%) 0, var(--percent, 0%) 100%, 0 100%);
            }
            .labelStar {
                font-size: 16px;
                font-weight: bold;
                margin-bottom: 10px;
            }
            .ratingBlock {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
                align-items: center;
                gap: 10px; /* Adjust this value to change the spacing between elements */
            }
            .star1{
                display: inline-block;
                width: 30px;
                height: 30px;
                margin-right: 10px;
                background-image: url('https://icon-library.com/images/star-png-icon/star-png-icon-0.jpg');
                background-size: contain;
                cursor: pointer;
            }
            .star1:hover,
            .star1.active{
                background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK_x0xsTpZtA8UkG6hGE7Y8E-EXVXwT8NWbY0KymT2o_0pxmI&s');
            }
        </style>
    </head>

    <body onload="time()" class="app sidebar-mini rtl">
        <!-- Navbar-->
        <!-- Sidebar menu-->
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
        <%@ include file="left-menu-lanlord.jsp" %>

        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href=""><b>Thống Kê</b></a></li>
                </ul>

                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">
                                <div class="col-sm-2">
                                    <a class="btn btn-delete btn-sm nhap-tu-file" href="" title="all"><i class="fas fa-arrow-left" ></i>
                                        Trở Về</a>
                                </div>
                                <div>
                                    <select  id="select-year" name="year" class="styled-select btn btn-excel btn-sm">
                                        <option value="" disabled selected>Chọn Năm</option>
                                        <option value="2024">2024</option>
                                        <option value="2023">2023</option>
                                        <option value="2022">2022</option>
                                        <option value="2021">2021</option>
                                    </select>
                                </div>
                            </div>
                            <h3>Khu Trọ </h3>
                            <div class="row">
                                <div class="col-md-6" id="top_x_div" style="width: 800px; height: 600px;"></div>
                                <div class="col-md-6" id="piechart" style="width: 900px; height: 500px;"></div>
                            </div>
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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages: ["corechart"]});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Period', 'Revenue'],
                <c:forEach var="revenue" items="${listRe}">
                    ['${revenue.year}', ${revenue.revenue}],
                </c:forEach>
            ]);

            var options = {
                title: 'Revenue Statistics',
                legend: 'none',
                pieSliceText: 'label',
                pieStartAngle: 100,
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
        }
    </script>
<script type="text/javascript">
    google.charts.load('current', {'packages': ['bar']});
    google.charts.setOnLoadCallback(drawStuff);

    function drawStuff() {
        var data = new google.visualization.arrayToDataTable([
            ['Move', 'Percentage'],
            ["King's pawn (e4)", 44],
            ["Queen's pawn (d4)", 31],
            ["Knight to King 3 (Nf3)", 12],
            ["Queen's bishop pawn (c4)", 10],
            ["Queen's bishop pawn (c4)", 10],
            ["Queen's bishop pawn (c4)", 10],
            ["Queen's bishop pawn (c4)", 10],
            ["Queen's bishop pawn (c4)", 10],
            ["Queen's bishop pawn (c4)", 10],
            ["Queen's bishop pawn (c4)", 10],
            ["Queen's bishop pawn (c4)", 10],
            ['Other', 3]
        ]);

        var options = {
            width: 800,
            legend: {position: 'none'},
            chart: {
                title: 'Chess opening moves',
                subtitle: 'popularity by percentage'},
            axes: {
                x: {
                    0: {side: 'top', label: 'White to move'} // Top x-axis.
                }
            },
            bar: {groupWidth: "90%"}
        };

        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
        // Convert the Classic options to Material options.
        chart.draw(data, google.charts.Bar.convertOptions(options));
    }
    ;
</script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
    const stars = document.querySelectorAll(".star1");
    const ratingInput = document.getElementById("ratingInput");
    let initialRating = parseInt(ratingInput.value); // Lấy giá trị rating ban đầu từ input

    // Đánh dấu số sao tương ứng với initialRating
    stars.forEach((star, index) => {
        const starRating = parseInt(star.getAttribute("data-rating"));

        if (starRating <= initialRating) {
            star.classList.add("active");
        } else {
            star.classList.remove("active");
        }
    });</script>



</body>

</html> 