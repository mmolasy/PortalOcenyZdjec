<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Add new Photo</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Photo</h1>
            <p>Add new photo</p>
        </div>
    </div>
</section>
<section class="container">

    <form method="post" action="addPhoto" enctype="multipart/form-data">
        <table border="0">
            <tr>
                <td>Set file</td>
                <td><input type="file" name="fileUpload" size="50" accept="image/jpeg, image/gif"/></td>
            </tr>
            <tr>
                <td>Set description</td>
                <td><input type="text" name="description" size="50" /></td>
            </tr>
            <tr>
                <td>Set visibility</td>
                <td><input type="radio" name="visibility" value="PUBLIC"> PUBLIC</td>
                <td><input type="radio" name="visibility" value="PRIVATE"> PRIVATE</td>
                <td><input type="radio" name="visibility" value="FRIENDS_ONLY"> FRIENDS_ONLY</td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Upload" /></td>
            </tr>
        </table>
    </form>
</section>
${error}
</body>
</html>