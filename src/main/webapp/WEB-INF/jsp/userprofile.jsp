<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<title>Home page</title>
<body>
Welcome ${user.nickName}

Your photos:<br/>
<c:forEach items="${photoList}" var="photo">

    <img src="/data/${photo.user.userId}/photos/${photo.photoId}" alt="Smiley face"><br/>
        ${photo.description}<br/>
        ${photo.createdDate}<br/>
        ${photo.visibility}<br/>
        ${photo.user.nickName}<br/>
        ----------------------- <br/>
</c:forEach>
</body>
</html>