<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC 添加博客</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>添加
        <a href="/admin/users/add" type="button" class="btn btn-primary btn-sm">添加用户</a>
    </h1>
    <hr/>
    <form:form action="/admin/blogs/addP" method="post" commandName="blog" role="form">
        <div class="form-group">
            <label for="title">事由:</label>
            <input required type="text" class="form-control" id="title" name="title" placeholder="Enter Title:"/>
        </div>
        <div class="form-group">
            <label for="titleId">事由ID:</label>
            <input required type="text" class="form-control" id="titleId" name="titleId" placeholder="Enter Title:"/>
        </div>
        <div class="form-group">
            <label for="userByUserId.id">用户:</label>
            <select class="form-control" id="userByUserId.id" name="userByUserId.id">
                <c:forEach items="${userList}" var="user">
                    <option value="${user.id}">${user.nickname}, ${user.firstName} </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="pubDate">加班时间:</label>
            <input required type="text" class="form-control" id="pubDate" name="pubDate"/>
        </div>
        <div class="form-group">
            <label for="costtime">加班时长(只允许数字,精确到小数点后1位,不能出现汉字)</label>
            <input required onKeyPress="if((event.keyCode<48 || event.keyCode>57) && event.keyCode!=46 || /\.\d$/.test(value))event.returnValue=false"
                    type="text" class="form-control" id="costtime" name="costtime"/>
        </div>
        <div><label >（一）元旦，放假1天(1月1日)；</label></div>
        <div><label >（二）春节，放假3天(农历正月初一、初二、初三)；</label></div>
        <div><label >（三）清明节，放假1天(农历清明当日)；</label></div>
        <div><label >（四）劳动节，放假1天(5月1日)；</label></div>
        <div><label >（五）端午节，放假1天(农历端午当日)；</label></div>
        <div><label >（六）中秋节，放假1天(农历中秋当日)；</label></div>
        <div><label >（七）国庆节，放假3天(10月1日、2日、3日)。</label></div>        <div class="form-group">
            <label for="type">加班类型(只有以上7条才能填报节假日加班):</label>
            <select class="form-control" id="type" name="type">
                <option value="日常">日常</option>
                <option value="周末">周末</option>
                <option value="节假日">法定节假日</option>
            </select>
        </div>
        <div class="form-group">
            <label for="content">工作地点及内容:</label>
            <textarea required  class="form-control" id="content" name="content" rows="3" placeholder="Please Input Content"></textarea>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>