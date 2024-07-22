
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user" ><img class="app-sidebar__user-avatar" src="" width="100px"
                                                 alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b></b></p>
                    <p class="app-sidebar__user-designation">Chào m?ng b?n tr? l?i</p>
                </div>
            </div>
            <hr>
            <ul class="app-menu">

                <li><a class="app-menu__item active" href="home-controller"><i class='app-menu__icon bx bx-tachometer'></i><span
                            class="app-menu__label">Trang Chủ</span></a></li>
                <li><a class="app-menu__item " href="/ManageLodgingHouse/management-lodging-houses?index=1"><i class='app-menu__icon bx bx-id-card'></i> <span
                            class="app-menu__label">Quản Lý Khu Trọ</span></a></li>
                               <li>
                    <a id="notificationLink" href="list-notification-landlord" class="app-menu__item">
                        <i class='app-menu__icon bx bx-id-card'></i>
                        <span class="app-menu__label">Thông Báo Mới</span>
                        <span class="badge">${sessionScope.NumberOfNotification}</span> <!-- Example: Replace with dynamic content -->
                    </a>
                </li>

                <li><a class="app-menu__item" href="table-data-money.html"><i class='app-menu__icon bx bx-dollar'></i><span
                            class="app-menu__label">Phát sinh</span></a></li>
                <li><a class="app-menu__item" href="quan-ly-bao-cao.html"><i
                            class='app-menu__icon bx bx-pie-chart-alt-2'></i><span class="app-menu__label">Báo cáo doanh thu</span></a>
                </li>
                <li><a class="app-menu__item" href="page-calendar.html"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Tính Tiền</span></a></li>
                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/type-of-investment-costs-servlet"><i class='app-menu__icon bx bx-id-card'></i>
                        <span class="app-menu__label">Quản Lý Phí Đầu Tư</span></a></li>                      
                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/list-role-of-staff"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i>
                        <span class="app-menu__label">Quản Lý Chức Vụ</span></a></li>
                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/statictis-bill?service=showStatisticRevenue"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i>
                        <span class="app-menu__label">Báo Cáo Doanh Thu</span></a></li>
                <li><a class="app-menu__item" href="account?service=logout"><i class='app-menu__icon bx bx-log-out'></i><span
                    class="app-menu__label">Đăng Xuất</span></a></li>
            </ul>
        </aside>