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
    <title>Login to Fortagram</title>
</head>
<style>
    body
    {
        background-color: #7C7676;
        font-family: 'Lora', serif;
        color:white;
    }
    .jumbotron{
        position: relative;
        padding:60px;
        padding-left:400px;
        padding-right:30px;
        margin-top:40px !important;
        background-color: gold;
        margin-top: 23px;
        margin-left: 23px;
        margin-right: 23px;
        text-align:center;
        margin-bottom: 0 !important;
        height: 300px;
    }
    #dane
    {
        width:800px;
        font-size: 60px;
        font-family: 'Indie Flower', cursive;
        text-align:center;
        display:table-cell;
        vertical-align:middle;
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
        width:260px;
        font-size: 20px;
        padding:20px;
        text-align:right;
    }
    .naglowek
    {

        float:left;
        height: 300px;
    }
</style>
<script>
    function checkError(error){
        if(error) {
            alert("Wrong credentials, please try again.");
        }
    }
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
        document.getElementById("czas").innerHTML = dzien+"."+miesiac+"."+rok+"<br>"+godzina+":"+minuta+":"+sekunda;
        setTimeout("odliczanie(),1000");
    }
</script>
<body onload="checkError('${error}'), odliczanie();">
<section>
    <div class="jumbotron">
        <div class="naglowek" id="dane" >
            <p>Fortagram</p>
            <h2>Welcome on our page to share and grade photos with friends</h2>
        </div>
        <div class="naglowek" id="czas" >
        </div>
    </div>
</section>
<section class="container">
    <form:form  modelAttribute="loginDto" class="form-horizontal">
        <fieldset>
            <div id="msg">
                <p>Fill in the formular to log in</p>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="email">E-mail</label>
                <div class="col-lg-10">
                    <form:input userId="email" path="email" type="email" class="form:input-large" required="required"/>
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
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" userId="btnLogin" class="btn btn-primary" value ="Log in!"/>
                </div>
            </div>
        </fieldset>
    </form:form>
        <form:form class="form-horizontal" method="get" action="/register">
            <div id="msg">
                <p>Don't have account yet? Click here to register</p>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <td colspan="2" align="center"><input type="submit" class="btn btn-danger" value="Register now!" /></td>
                </div>
            </div>
        </form:form>
</section>
</body>
</html>