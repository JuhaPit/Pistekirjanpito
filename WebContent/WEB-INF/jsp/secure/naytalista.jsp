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
<h4 style="text-align: center;">Kirjautunut käyttäjänä "${kayttajatiedot.username}"</h4>
<br>

<div class="keskita">

<form action="PisteServlet" method="get">
<table>
			<caption>Palautukset</caption>
			<thead>
				<tr>
					<td>TEKIJÄ</td>
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
		
		<p>Tekijä</p><input type="text" name="arvioitava" placeholder="Tekijän opiskelijanumero tähän">
		<p>Pisteet</p><input type="text" name="pisteet" placeholder="Arviointinumero tähän">
		
		<button type="submit">Pisteytä</button>
		
		
	</form>	
</div>

		
		<p style="text-align: center;"><a href="site">Takaisin etusivulle</a></p>
</body>
</html>