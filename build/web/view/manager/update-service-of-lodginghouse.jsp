<%-- 
    Document   : update-service-of-lodginghouse
    Created on : Jun 22, 2024, 10:54:44 PM
    Author     : ASUS ZenBook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.ServletContext, model.Account, model.InformationOfUser, dal.InformationOfUserDAO, jakarta.servlet.http.HttpSession, java.util.List, dal.ServiceDAO, model.Service, model.ServiceOfLodgingHouse, dal.ServiceOfLodgingHouseDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Cập nhật dịch vụ trọ | Quản trị Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resource/doc/css/main.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
        <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
        <script>

            function readURL(input, thumbimage) {
                if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("#thumbimage").attr('src', e.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                } else { // Sử dụng cho IE
                    $("#thumbimage").attr('src', input.value);

                }
                $("#thumbimage").show();
                $('.filename').text($("#uploadfile").val());
                $('.Choicefile').css('background', '#14142B');
                $('.Choicefile').css('cursor', 'default');
                $(".removeimg").show();
                $(".Choicefile").unbind('click');

            }
            $(document).ready(function () {
                $(".Choicefile").bind('click', function () {
                    $("#uploadfile").click();

                });
                $(".removeimg").click(function () {
                    $("#thumbimage").attr('src', '').hide();
                    $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
                    $(".removeimg").hide();
                    $(".Choicefile").bind('click', function () {
                        $("#uploadfile").click();
                    });
                    $('.Choicefile').css('background', '#14142B');
                    $('.Choicefile').css('cursor', 'pointer');
                    $(".filename").text("");
                });
            })
        </script>
    </head>

    <body class="app sidebar-mini rtl">
        <style>
            .Choicefile {
                display: block;
                background: #14142B;
                border: 1px solid #fff;
                color: #fff;
                width: 150px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                padding: 5px 0px;
                border-radius: 5px;
                font-weight: 500;
                align-items: center;
                justify-content: center;
            }

            .Choicefile:hover {
                text-decoration: none;
                color: white;
            }

            #uploadfile,
            .removeimg {
                display: none;
            }

            #thumbbox {
                position: relative;
                width: 100%;
                margin-bottom: 20px;
            }

            .removeimg {
                height: 25px;
                position: absolute;
                background-repeat: no-repeat;
                top: 5px;
                left: 5px;
                background-size: 25px;
                width: 25px;
                /* border: 3px solid red; */
                border-radius: 50%;

            }

            .removeimg::before {
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                content: '';
                border: 1px solid red;
                background: red;
                text-align: center;
                display: block;
                margin-top: 11px;
                transform: rotate(45deg);
            }

            .removeimg::after {
                /* color: #FFF; */
                /* background-color: #DC403B; */
                content: '';
                background: red;
                border: 1px solid red;
                text-align: center;
                display: block;
                transform: rotate(-45deg);
                margin-top: -2px;
            }

            .validate-input.success input {
                border-color: #2ecc71;
            }
            .validate-input.error input {
                border-color: #e74c3c;
            }

            small {
                color: #e74c3c;
                bottom: -20px;
                left: 0;
                visibility: hidden;
                font-size: 12px;
                font-style: italic;
                font-size: 10px;
            }

            .validate-input.error small {
                visibility: visible;
            }

            .validate-input.success small {
                visibility: visible;
            }
        </style>
        <!-- Declare variable -->
        <%
            HttpSession s = request.getSession();
            InformationOfUserDAO id = new InformationOfUserDAO();
            ServiceDAO sd = new ServiceDAO();
            ServiceOfLodgingHouseDAO sld = new ServiceOfLodgingHouseDAO();
            
            Account account = (Account) s.getAttribute("account");
            int accountId = account.getAccountID();
            InformationOfUser informationOfUser = id.getInformationByAccountID(accountId);
            
            int lodgingHouseId = (int) s.getAttribute("lodgingID");
                   
            ServiceOfLodgingHouse serviceNeedUpdate = (ServiceOfLodgingHouse) request.getAttribute("serviceNeedUpdate");
        %>
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button-->
            <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">
                <!-- User Menu-->
                <li><a class="app-nav__item" href="/index.html"><i class='bx bx-log-out bx-rotate-180'></i></a></li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <%@ include file="left-menu-manager.jsp"%>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Cập nhật dịch vụ trọ</a></li>
                </ul>
            </div>  
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 style="margin-bottom: 20px" class="tile-title">Cập nhật dịch vụ trọ</h3>
                        <div style="color: red; margin-bottom: 20px; font-size: 12px">
                            <strong> *Lưu ý:</strong><br>        
                            <strong> - Hãy chắc chắn giá tiền dịch vụ trước khi cập nhật vì dùng để tính các khoản chi phí sau này.</strong><br>
                        </div>
                        <div class="tile-body">
                            <form id="form" class="row">                                               
                                <div class="form-group col-md-3">
                                    <label class="control-label">Dịch vụ</label><br>
                                    <%
                                        String nameOfService = sd.getServiceById(serviceNeedUpdate.getServiceId()).getServiceName();
                                    %>
                                    <input class="form-control" type="text" value="<%=nameOfService%>" disabled="">
                                </div>

                                <div class="form-group col-md-3">
                                    <label class="control-label">Giá tiền</label>
                                    <input id="price" class="form-control" name="price" type="number" min="1000" max="10000000" 
                                           value="<%=serviceNeedUpdate.getPrice()%>" required="">
                                </div>  

                                <div class="form-group col-md-6">
                                </div>

                                <button style="margin-left: 15px" class="btn btn-save">
                                    Cập nhật
                                </button>     
                                <input type="hidden" name="service" value="updateServiceOfLodgingHouse">
                                <a style="margin-left: 10px" class="btn btn-cancel" href="${pageContext.request.contextPath}/service-of-lodginghouse">Hủy bỏ</a>
                            </form>
                        </div>
                    </div>
                    </main>

                    <script src="js/jquery-3.2.1.min.js"></script>
                    <script src="js/popper.min.js"></script>
                    <script src="js/bootstrap.min.js"></script>
                    <script src="js/main.js"></script>
                    <script src="js/plugins/pace.min.js"></script>
                    <script>
            const inpFile = document.getElementById("inpFile");
            const loadFile = document.getElementById("loadFile");
            const previewContainer = document.getElementById("imagePreview");
            const previewContainer = document.getElementById("imagePreview");
            const previewImage = previewContainer.querySelector(".image-preview__image");
            const previewDefaultText = previewContainer.querySelector(".image-preview__default-text");
            inpFile.addEventListener("change", function () {
                const file = this.files[0];
                if (file) {
                    const reader = new FileReader();
                    previewDefaultText.style.display = "none";
                    previewImage.style.display = "block";
                    reader.addEventListener("load", function () {
                        previewImage.setAttribute("src", this.result);
                    });
                    reader.readAsDataURL(file);
                }
            });

                    </script>           

                    <script type="text/javascript">
                        document.getElementById('form').addEventListener('submit', function (event) {
                            event.preventDefault();

                            swal({
                                title: "Cảnh báo",
                                text: "Bạn có chắc chắn là muốn cập nhật dịch vụ này không?",
                                icon: "warning",
                                buttons: ["Hủy bỏ", "Đồng ý"]
                            })
                                    .then((acceptInsert) => {
                                        if (acceptInsert) {
                                            var priceVal = document.getElementById('price').value;

                                            var url = '/ManageLodgingHouse/service-of-lodginghouse?service=updateServiceOfLodgingHouse' +
                                                    '&serviceId=' + <%=serviceNeedUpdate.getServiceId()%> + '&price=' + priceVal;

                                            // Gửi dữ liệu tới servlet bằng Fetch API
                                            fetch(url)
                                                    .then(response => {
                                                        return response.text();
                                                    }) // hoặc response.json() nếu servlet trả về JSON
                                                    .then(data => {
                                                        console.log(data);
                                                        if (data === "OK") {
                                                            swal({
                                                                title: "Cập nhật thành công!",
                                                                text: "Bạn đã cập nhật dịch vụ thành công.",
                                                                icon: "success",
                                                                buttons: ["Ở lại", "Thoát"]
                                                            })
                                                                    .then((acceptBack) => {
                                                                        if (acceptBack) {
                                                                            window.location.href = "${pageContext.request.contextPath}/service-of-lodginghouse";
                                                                        }
                                                                    });
                                                        } else {
                                                            swal({
                                                                title: "Cập nhật không thành công!",
                                                                text: "Bạn đã cập nhật dịch vụ không thành công.",
                                                                icon: "error"
                                                            });
                                                        }
                                                    })
                                                    .catch((error) => {
                                                        console.error('Error:', error);
                                                    });
                                        } else {
                                            swal({
                                                title: "Cập nhật không thành công!",
                                                text: "Bạn đã huỷ cập nhật dịch vụ.",
                                                icon: "error"
                                            });
                                        }
                                    });
                        });
                    </script>
                    </body>

                    </html>

