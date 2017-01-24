<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles/auth.css">
<title>Etusivu</title>
</head>
<body>
<p style="text-align: right;"><c:out value="${kayttajatiedot.username}"/><br/>
<a href="kirjaudu_ulos">Kirjaudu ulos</a>
<h1 style="text-align: center;">Pistekirjanpito</h1>
<h4 style="text-align: center;">Kirjautunut k�ytt�j�n� "${kayttajatiedot.username}"</h4>
<br>

<div class="keskita">

<form action="PisteServlet" method="get">
<table>
			<caption>Palautukset</caption>
			<thead>
				<tr>
					<td>TEKIJ�</td>
					<td>DEMO</td>
					<td>PISTEET</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${kayttajat}" var="kayt">
					<tr>
						<td><c:out value="${kayt.username}" /></td>
						<td><c:out value="${kayt.demo}" /></td>
						<td><c:out value="${kayt.pisteet}" /></td>
						
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
		
		<p>Tekij�</p><input type="text" name="arvioitava" placeholder="Tekij�n opiskelijanumero t�h�n">
		<p>Pisteet</p><input type="text" name="pisteet" placeholder="Arviointinumero t�h�n">
		
		<button type="submit">Pisteyt�</button>
		
		
	</form>	
</div>

		
		<p style="text-align: center;"><a href="site">Takaisin etusivulle</a></p>
</body>
</html>