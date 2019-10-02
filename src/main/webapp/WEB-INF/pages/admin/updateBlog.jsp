<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC 修改博客</title>

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
    <h1>修改</h1>
    <hr/>
    <form:form action="/admin/blogs/updateP" method="post" commandName="blogP" role="form">
        <div class="form-group">
            <label for="title">事由:</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="Enter Title:" value="${blog.title}"/>
        </div>
        <div class="form-group">
            <label for="titleId">事由ID:</label>
            <input type="text" class="form-control" id="titleId" name="titleId" placeholder="Enter Title:" value="${blog.titleId}"/>
        </div>
        <div class="form-group">
            <label for="userByUserId.id">用户:</label>
            <select class="form-control" id="userByUserId.id" name="userByUserId.id">
                <c:forEach items="${userList}" var="user">
                    <c:if test="${user.id==blog.userByUserId.id}">
                        <option value="${user.id}" selected="selected">${user.nickname}, ${user.firstName} </option>
                    </c:if>
                    <c:if test="${user.id!=blog.userByUserId.id}">
                        <option value="${user.id}">${user.nickname}, ${user.firstName} </option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="pubDate">加班时间:</label>
            <input type="text" class="form-control" id="pubDate" name="pubDate"
                   value="${blog.pubDate }" />
        </div>
        <div class="form-group">
            <label for="costtime">加班时长(只允许数字,精确到小数点后1位,不能出现汉字)</label>
            <input required onKeyPress="if((event.keyCode<48 || event.keyCode>57) && event.keyCode!=46 || /\.\d$/.test(value))event.returnValue=false"
                   type="text" class="form-control" id="costtime" name="costtime" value="${blog.costtime}"/>
        </div>

        <div><label >（一）元旦，放假1天(1月1日)；</label></div>
        <div><label >（二）春节，放假3天(农历正月初一、初二、初三)；</label></div>
        <div><label >（三）清明节，放假1天(农历清明当日)；</label></div>
        <div><label >（四）劳动节，放假1天(5月1日)；</label></div>
        <div><label >（五）端午节，放假1天(农历端午当日)；</label></div>
        <div><label >（六）中秋节，放假1天(农历中秋当日)；</label></div>
        <div><label >（七）国庆节，放假3天(10月1日、2日、3日)。</label></div>
        <div class="form-group">
        <label for="type">加班类型(只有以上7条才能填报节假日加班):</label>
            <select class="form-control" id="type" name="type" value="${blog.type}">
                <option value="日常" >日常</option>
                <option value="周末" >周末</option>
                <option value="节假日" >法定节假日</option>
            </select>
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea class="form-control" id="content" name="content" rows="3"
                      placeholder="Please Input Content">${blog.content}</textarea>
        </div>
        <!-- 把 id 一并写入 blogP 中 -->
        <input type="hidden" id="id" name="id" value="${blog.id}"/>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    $type="${blog.type}";
    $('option').each(function () {
        if(this.value=="${blog.type}"){
            this.selected=true;
        }
    })
</script>
</body>
</html>