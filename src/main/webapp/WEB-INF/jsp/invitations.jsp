<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Zaproszenia</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Zaproszenia</h1>
            <p>Zaproszenia do polaczenia z uzytkownikami</p>
        </div>
        <a href="<c:url value="/logout" />" class="btn btn-danger btn-mini pull-right">wyloguj</a>
    </div>
</section>

<section class="container">
    <div class="row">
        <c:forEach items="${invitations}" var="invitation">
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                <div class="thumbnail">
                    <div class="caption">
                        <h3>Zaproszenie od ${invitation.from.userId}</h3>
                        <p>Id zaproszenia ${invitation.id}</p>
                        <p>
                        <div class="col-lg-offset-2 col-lg-10">
                            <a
                                    href=" <spring:url value="/users/${invitation.from.userId}" /> "
                                    class="btn btn-primary"> <span
                                    class="glyphicon-info-sign glyphicon" /></span> Zobacz profil
                            </a>
                        </div>

                        <form method="post" action="acceptInvitation/${invitation.id}">
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <input type="submit" userId="btnLogin" class="btn btn-primary" value ="Akceptuj zaproszenie"/>
                            </div>
                        </div>
                        </form>
                        </p>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
</body>
</html>