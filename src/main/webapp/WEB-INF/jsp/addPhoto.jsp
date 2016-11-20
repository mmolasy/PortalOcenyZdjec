<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Dodaj nowe zdjecie</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Zdjecia</h1>
            <p>Dodaj zdjecie</p>
        </div>
        <a href="<c:url value="/logout" />" class="btn btn-danger btn-mini pull-right">wyloguj</a>

    </div>
</section>
<section class="container">
    <form method="post" action="addPhoto" enctype="multipart/form-data">
            <legend>Dodaj nowe zdjecie</legend>

            <div class="form-group">
                <div class="col-lg-10">
                    <input type="file" name="fileUpload" size="50" accept="image/jpeg, image/gif" required/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-10">
                    Opis zdjecia<br/>
                    <textarea name="description" rows="3" cols="35" wrap="soft" required></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-10">
                    <input type="radio" name="visibility" value="PUBLIC" required> Publiczny
                    <input type="radio" name="visibility" value="FRIENDS_ONLY" required> Widza Tylko Znajomi
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value ="Dodaj"/>
                </div>
            </div>
    </form>
</section>
</body>
</html>