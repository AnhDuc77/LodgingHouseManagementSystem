<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Sidebar menu-->

<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user">
        <img class="app-sidebar__user-avatar" src="${userInfor.avatar}" width="100px" alt="User Image">
        <div>
            <p class="app-sidebar__user-name"><b>${userInfor.fullName}</b></p>
            <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
        </div>
    </div>
    <hr>
    <ul class="app-menu">
        
        <li><a class="app-menu__item active" href="/ManageLodgingHouse/home-manager"><i class='app-menu__icon bx bx-tachometer'></i><span class="app-menu__label">Quản lí trọ</span></a></li>
        <li><a class="app-menu__item" href="management-lodging-houses"><i class='app-menu__icon bx bx-id-card'></i><span class="app-menu__label">Quản lí thu chi</span></a></li>             
        <li>
            <a id="notificationLink " href="list-notification-for-manager" class="app-menu__item">
                <i class='app-menu__icon bx bx-id-card'></i>
                <span class="app-menu__label">Thông Báo Mới</span>
                <span class="badge">${sessionScope.NumberOfNotification}</span> <!-- Example: Replace with dynamic content -->
            </a>
        </li>
        <li><a class="app-menu__item " href="management-lodging-houses?index=1"><i class='app-menu__icon bx bx-id-card'></i> <span class="app-menu__label">Quản lí các khu trọ</span></a></li>
        <li><a class="app-menu__item" href="feedback?service=showManageFeedback"><i class='app-menu__icon bx bx-cog'></i><span class="app-menu__label">Quản Lý Đánh Giá</span></a></li>
        <li><a class="app-menu__item" href="asset?service=manageAsset"><i class='app-menu__icon bx bx-cog'></i><span class="app-menu__label">Quản Lý Tài Sản</span></a></li>
        <li><a class="app-menu__item" href="${pageContext.request.contextPath}/investment-costs-servlet"><i
                    class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lí khoản phí đầu tư</span></a>
        </li> 
        <li><a class="app-menu__item" href="${pageContext.request.contextPath}/list-staff"><i class='app-menu__icon bx bx-id-card'></i>
                <span class="app-menu__label">Quản lý nhân viên</span></a></li>
        <li><a class="app-menu__item " href="${pageContext.request.contextPath}/bill"><i class='app-menu__icon bx bx-id-card'></i>
                <span class="app-menu__label">Hoá đơn thanh toán</span></a></li> 
        
        <li><a class="app-menu__item " href="${pageContext.request.contextPath}/chat"><i class='app-menu__icon bx bx-mail-send'></i>
                <span class="app-menu__label">Tin nhắn</span></a></li>
    </ul>
</aside>

 <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user">
                <img class="app-sidebar__user-avatar" src="${requestScope.account.getAvartar()}" width="100px" alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b>${requestScope.account.fullName}</b></p>
                    <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
                </div>
            </div>
            <hr>
            <ul class="app-menu">
                <li><a class="app-menu__item active" href="/ManageLodgingHouse/home-manager"><i class='app-menu__icon bx bx-tachometer'></i><span class="app-menu__label">Quản lí trọ</span></a></li>
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

