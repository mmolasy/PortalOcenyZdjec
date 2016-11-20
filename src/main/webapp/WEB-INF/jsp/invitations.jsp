<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<title>Home page</title>
<body>
Welcome ${user.nickName}

Your photos:<br/>
<c:forEach items="${invitations}" var="invitation">

    ${invitation.from.userId}<br/>
    ${invitation.id}<br/>
    <form method="post" action="acceptInvitation/${invitation.id}">
        <td colspan="2" align="center"><input type="submit" value="Accept invitation" /></td>
    </form>
    ----------------------- <br/>
</c:forEach>
</body>
</html>