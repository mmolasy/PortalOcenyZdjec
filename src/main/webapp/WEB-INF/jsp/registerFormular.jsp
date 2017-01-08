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
    <title>Register to Fortagram</title>
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
        text-align:left;
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
            alert(error);
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
    function validatePasswords(){
        var one = document.getElementById("passwordOne").value;
        var two = document.getElementById("passwordTwo").value;
        if(one !== two) {
            alert("Passwords must be the same");
            return false;
        }
        if(one.length<8 || two.length<8)
        {
            alert("Min password length must be 8");
            return false;
        }
        return true;
    }
//    function sila_hasla()
//    {
//        var a = document.getElementById("passwordOne");
//        var b = document.getElementById("passwordTwo");
//
//        if(a==b && a.length >=5 )
//        {
//            var c=0;
//            var l=0;
//            for(var i=0 ; i<(a.length) ; i++){
//                if(isNaN(a[i]))
//                    l++;
//                else
//                    c++;
//            }
//            if(l==0 || c==0){
//                document.getElementById("kolor").value = "#1BFF60";
//                document.getElementById("sila").value = "Słabe";}
//            if(c==1 || l==1){
//                document.getElementById("kolor").value = "#ECEBEB";
//                document.getElementById("sila").value = "Średnie";}
//            if(c>=2 && l>=2){
//                document.getElementById("kolor").value = "#FF0000";
//                document.getElementById("sila").value = "Silne";
//            }
//        }else{
//            document.getElementById("kolor").value = "#000000";
//            document.getElementById("sila").value = "";
//        }}
</script>
<body onload="checkError('${error}'), odliczanie();">
<section>
    <div class="jumbotron">
        <div class="naglowek" id="dane" >
            <p>Fortagram</p>
            <h2>Please fill in formular to register</h2>
        </div>
        <div class="naglowek" id="czas" >
        </div>
    </div>
</section>
<section class="container">
    <form:form  modelAttribute="newUser" class="form-horizontal" onsubmit="return validatePasswords();">
        <fieldset>
            <div id="msg">
            <p>Add new user</p>
            </div>
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
                        <form:input userId="password" id="passwordOne" path="password" type="password" onchange="sila_hasla()" class="form:input-large" required="required"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="password">Confirm Password</label>
                <div class="col-lg-10">
                    <div class="form:input-prepend">
                        <input userId="password" id="passwordTwo" type="password" class="form:input-large" onchange="sila_hasla()" required="required"/>
                    </div>
                </div>
            </div>
            <div id="kolor"></div>
            <div id="sila"></div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="email">E-mail</label>
                <div class="col-lg-10">
                    <form:input userId="email" path="email" type="email" class="form:input-large" required="required"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2">Birth Date</label>
                <div class="col-lg-10">
                    <input name="date" type="date" class="form:input-large" required="required"/>
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
</body>
</html>