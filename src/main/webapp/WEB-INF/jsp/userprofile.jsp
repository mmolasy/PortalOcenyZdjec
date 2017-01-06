<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/css/bootstrap-select.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <title>User id: ${user.userId} profile</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.1.0/Chart.js"></script>
    <script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/js/bootstrap-select.min.js"></script>
    <script>
        $(document).ready(function(e) {
            $('.selectpicker').selectpicker();
        });
    </script>
</head>
<script>

    function returnZeroIfUndefined(value){
        if(value == undefined)
            return 0;
        else
            return value;
    }

    function grade(index, gradeMap) {

        var data =
                 [
                    {
                        value: returnZeroIfUndefined(gradeMap["1"]),
                        color:"#F7464A",
                        highlight: "#FF5A5E",
                        label: "Amount of 1 grade"
                    },
                    {
                        value: returnZeroIfUndefined(gradeMap["2"]),
                        color: "#46BFBD",
                        highlight: "#5AD3D1",
                        label: "Amount of 2 grade"
                    },
                    {
                        value: returnZeroIfUndefined(gradeMap["3"]),
                        color: "#FDB45C",
                        highlight: "#FFC870",
                        label: "Amount of 3 grade"
                    },
                     {
                         value: returnZeroIfUndefined(gradeMap["4"]),
                         color: "#1799D1",
                         highlight: "#44A6D0",
                         label: "Amount of 4 grade"
                     },
                     {
                         value: returnZeroIfUndefined(gradeMap["5"]),
                         color: "#2EEC19",
                         highlight: "#81E976",
                         label: "Amount of 5 grade"
                     }
                ];

        var option = {
            responsive: true,
        };

        var ctx = document.getElementById("myChart"+index).getContext('2d');
        var myDoughnutChart = new Chart(ctx).Doughnut(data,option);
    };

    function sendGrade(photoId, index){
        var selectBox = document.getElementById("selectBox"+index);
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
        window.location = 'http://localhost:8080/users/'+photoId+'/grade/'+selectedValue;
    }

    function calculateGrade(gradeMap){
        var sumOfGrades=0;
        var totalSum=0;
        for(var i=1;i<=5;i++){
            sumOfGrades+=returnZeroIfUndefined(gradeMap[i]);
            totalSum+=returnZeroIfUndefined(gradeMap[i])*i;
        }
        return totalSum/sumOfGrades;
    }
    function Round(n, k)
    {
        var factor = Math.pow(10, k);
        return Math.round(n*factor)/factor;
    }

</script>
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
    <c:forEach items="${photoList}" var="photo" varStatus="loop">
    <div class="row">
        <div class="col-md-5">
            <img src="<c:url value="/data/${photo.user.userId}/photos/${photo.photoId}"></c:url>" alt="image"  style = "width:100%"/>
        </div>

        <div class="col-md-5">
            <p>
                <strong>Id zdjecia: </strong><span class="label label-warning">${photo.photoId}</span>
            </p>
            <p>
                <strong>Opis</strong>:  ${photo.description}
            </p>
            <p>
                <strong>Ocena</strong>: <div id="grade${loop.index}"></div>
            </p>
                ${photo.transformedGrades}
            <script>
                var result = calculateGrade(${photo.transformedGrades});
                if(isNaN(result)){
                    document.getElementById("grade${loop.index}").innerHTML="No grades yet";
                }else{
                    document.getElementById("grade${loop.index}").innerHTML=Round(calculateGrade(${photo.transformedGrades}),2);
                }
            </script>
            <p>
                <strong>Data dodania</strong>: ${photo.createdDate}
            </p>
            <p>
                <strong>Widocznosc zdjecia</strong>: ${photo.visibility}
            </p>
            <canvas id="myChart${loop.index}"></canvas>
            <script>grade(${loop.index},${photo.transformedGrades}); </script>
            <form action="demo_form.asp">
                <select class="selectpicker show-tick" id="selectBox${loop.index}" onchange="sendGrade(${photo.photoId}, ${loop.index});">
                    <option id="0${loop.index}">NoRate</option>
                    <option id="1${loop.index}">One</option>
                    <option id="2${loop.index}">Two</option>
                    <option id="3${loop.index}">Three</option>
                    <option id="4${loop.index}">Four</option>
                    <option id="5${loop.index}">Five</option>
                </select>
                <script>
                    document.getElementById("selectBox${loop.index}").options.namedItem("${photo.currentUserGrade}${loop.index}").selected=true;
                </script>
            </form>
            <c:if test="${relationShip == 'SESSION'}">
                <form name="removePhoto" method="post" action="/users/${photo.photoId}/remove">
                    <td colspan="3" align="center"><input type="submit" value="Remove photo"/></td>
                </form>
            </c:if>
        </div>
    </div>
        </c:forEach>

</section>
</body>
</html>