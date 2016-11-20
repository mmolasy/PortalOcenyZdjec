<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>User id: ${user.userId} profile</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>User id: ${user.userId} profile</h1>
    <c:if test="${relationShip == 'NOTFRIEND'}">
        <form method="post" action="invite/${user.userId}">
            <td colspan="2" align="center"><input type="submit" value="Invite to friends" /></td>
        </form>
        ${error}
    </c:if>
            <c:if test="${relationShip == 'SESSION'}">
                <a href="<c:url value="/users/addPhoto" />" class="btn btn-danger btn-mini pull-right">Dodaj nowe zdjecie</a><br/>
            </c:if>
            <a href="<c:url value="/logout" />" class="btn btn-danger btn-mini pull-right">wyloguj</a>
        </div>
    </div>

</section>
<section class="container">
    <c:forEach items="${photoList}" var="photo">
    <div class="row">
        <div class="col-md-5">
            <img src="<c:url value="/data/${photo.user.userId}/photos/${photo.photoId}"></c:url>" alt="image"  style = "width:100%"/>
        </div>

        <div class="col-md-5">
            <p>
                <strong>Id zdjecia: </strong><span class="label label-warning">${photo.photoId}}</span>
            </p>
            <p>
                <strong>Opis</strong>:  ${photo.description}
            </p>
            <p>
                <strong>Ocena</strong>: ${photo.grade}
            </p>
            <p>
                <strong>Data dodania</strong>: ${photo.createdDate}
            </p>
            <p>
                <strong>Widocznosc zdjecia</strong>: ${photo.visibility}
            </p>

        </div>
    </div>
        </c:forEach>

</section>
</body>
</html>