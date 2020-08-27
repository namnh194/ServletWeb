<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 8/13/2020
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img width="50px" height="50px" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSMOHU_4l1TEoETT_SB6F1-HjOB050tvOa_Hg&usqp=CAU" alt="">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="trang-chu.tiles">Trang chủ <span class="sr-only">(current)</span></a>
                <!--Nếu href="/trang-chu.tiles"> sẽ hiểu là link tuyệt đối, localhost:8080/trang-chu.tiles</-->
            </li>
            <li class="nav-item">
                <a class="nav-link" href="quan-ly-san-pham.tiles">Sản phẩm</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="quan-ly-danh-muc-san-pham.tiles">Danh mục sản phẩm</a>
            </li>
        </ul>
    </div>
</nav>
