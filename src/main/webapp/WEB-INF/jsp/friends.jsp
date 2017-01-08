<<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Lora&subset=latin,latin-ext' rel='stylesheet'/>
    <link href='https://fonts.googleapis.com/css?family=Indie+Flower' rel='stylesheet'/>
    <title>See friends</title>
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
<style>
    body
    {
        background-color: #7C7676;
        font-family: 'Lora', serif;
        color:white;
    }
    .jumbotron{
        position: relative;
        padding:30px;
        padding-right:30px;
        margin-top:10px !important;
        background-color: gold;
        margin-top: 23px;
        margin-left: 23px;
        margin-right: 23px;
        text-align:center;
        margin-bottom: 0 !important;
        height: 250px;
    }
    #dane
    {
        width:600px;
        font-size: 60px;
        font-family: 'Indie Flower', cursive;
        text-align:left;
        display:table-cell;
        padding:10px;
    }
    #msg
    {
        width:800px;
        font-size: 30px;
        font-family: 'Indie Flower', cursive;
        text-align:center;
        display:table-cell;
        vertical-align:middle;
        padding:10px;
    }
    #czas
    {
        width:100px;
        height:100px;
        font-size: 20px;
    }
    #buttons
    {
        width:100px;
        font-size: 20px;
        text-align:left;
    }
    .naglowek
    {
        padding-left:800px;
        float:left;
    }
    .friends
    {
    }
    #friend
    {
        width:600px;
        font-size: 60px;
        font-family: 'Indie Flower', cursive;
        text-align:left;
        display: inline-block;
        padding:10px;
    }
    #buttonn
    {
        width:1000px;
        font-size: 20px;
        display: inline-block;
        text-align: right;
        padding-left: 10px;
    }
</style>
<script>
    function odliczanie()
    {
        var dzisiaj = new Date();

        var dzien = dzisiaj.getDate();
        var miesiac = dzisiaj.getMonth()+1;
        var rok = dzisiaj.getFullYear();

        var godzina = dzisiaj.getHours();
        var minuta = dzisiaj.getMinutes();
        var sekunda = dzisiaj.getSeconds();

        if(sekunda>=0 && sekunda<10)
        {
            sekunda="0"+sekunda;
        }
        if(minuta>=0 && minuta<10)
        {
            minuta="0"+minuta;
        }
        if(godzina>=0 && godzina<10)
        {
            godzina="0"+godzina;
        }
        if(dzien>=0 && dzien<10)
        {
            dzien="0"+dzien;
        }
        if(miesiac>=0 && miesiac<10)
        {
            miesiac="0"+miesiac;
        }
        document.getElementById("czas").innerHTML = dzien+"."+miesiac+"."+rok+"</br>"+godzina+":"+minuta+":"+sekunda;
        setTimeout("odliczanie(),1000");
    }
</script>
<body onload="odliczanie();">
<section>
    <div class="jumbotron">
        <div class="naglowek" id="dane" >
            <form method="post" action="/users/search">
                <input type="text" name="mail">
                <input type="submit" class="btn btn-danger btn-mini pull-left" value="Search friend by email" />
            </form>
            <h2>Browse user friends.</h2>

        </div>
        <div class="naglowek" id="buttons">
            <a href="<c:url value="/users/me" />" class="btn btn-primary pull-left">Show my profile</a>
            <c:if test="${relationShip == 'SESSION'}">
                <a href="<c:url value="/users/addPhoto" />" class="btn btn-danger pull-left">Add new photo</a>
            </c:if>
            <a href="<c:url value="/users/invitations" />" class="btn btn-danger pull-left">Invitations</a>
            <a href="<c:url value="/logout" />" class="btn btn-danger pull-left">Log out</a>
        </div>
        <div class="naglowek" id="czas" >
        </div>
    </div>
</section>
<section class="container">
    <hr style="width: 100%; color: black; height: 1px; background-color:black;" />
    <c:forEach items="${friends}" var="user">
        <div class="col-sm-6 col-md-12" style="padding-bottom: 30px">
            <div class="thumbnail" style="background-color: lightgrey">
                <div class="caption">
                    <div class="friends" id="friend" >
                        <h4>User nick: ${user.nickName}</h4>
                        <h4>User email: ${user.email}</h4>
                        <h4>User age: ${user.age}</h4>
                    </div>
                    <div class="friends" id="buttonn" >
                        <a href="<c:url value="/users/${user.userId}" />" class="btn btn-primary pull-right">Show friend profile</a>
                    </div>
                    <hr style="width: 100%; color: black; height: 1px; background-color:black;" />
                </div>
            </div>
        </div>
        <hr style="width: 100%; color: black; height: 1px; background-color:black;" />
    </c:forEach>
</section>
</body>
</html>