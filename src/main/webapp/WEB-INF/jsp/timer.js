		function sprawdz()
{
	
	var name = document.getElementById("imie").value;
	var surname = document.getElementById("nazwisko").value;
	var mail = document.getElementById("email").value;
	var page = document.getElementById("strona").value;
	var date = document.getElementById("data").value;	

	if(name=="" || surname=="" || mail=="" || page=="" || date==""){
	document.getElementById("twojedane").innerHTML = "Niewystarczająca ilość danych w formularzu";
	}
	else{
		document.getElementById("twojedane").innerHTML = "Witaj "+name+" "+surname+". Twój adres e-mail to: "+mail+", a strona internetowa: "+page+". Urodziłeś/aś się: "+date;
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
			document.getElementById("czas").innerHTML = dzien+"."+miesiac+"."+rok+"<br>"+godzina+":"+minuta+":"+sekunda;
			setTimeout("odliczanie(),1000");
			}
