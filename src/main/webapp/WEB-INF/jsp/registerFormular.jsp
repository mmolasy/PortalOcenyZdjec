<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>UserRegister</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>User</h1>
            <p>Register new user</p>
        </div>
    </div>
</section>
<section class="container">
    <form:form  modelAttribute="newUser" class="form-horizontal">
        <fieldset>
            <legend>Add new user</legend>

            <div class="form-group">
                <label class="control-label col-lg-2" for="nickName">Nickname</label>
                <div class="col-lg-10">
                    <form:input userId="nickName" path="nickName" type="text" class="form:input-large" required="required"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="password">Password</label>
                <div class="col-lg-10">
                    <div class="form:input-prepend">
                        <form:input userId="password" path="password" type="password" class="form:input-large" required="required"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="email">E-mail</label>
                <div class="col-lg-10">
                    <form:input userId="email" path="email" type="email" class="form:input-large" required="required"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="birthDate">Birth Date</label>
                <div class="col-lg-10">
                    <form:input userId="birthDate" path="birthDate" type="date" class="form:input-large" required="required"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" userId="btnAdd" class="btn btn-primary" value ="Register NOW !"/>
                </div>
            </div>

        </fieldset>
    </form:form>
</section>
${error}
</body>
</html>