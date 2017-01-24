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
<h4 style="text-align: center;">Palauta ohjelmistodemo</h4>

<div class="keskita">

<form action="tallenna_suoritus" method="post">
<table>
<tr><td>Linkki proto383-palvelimelle</td><td><input type="text" name="linkki" size=35/></td></tr>
<tr><td>&nbsp;</td><td><button type="submit">Lähetä</button></td></tr>
</table>
</form>

</div>


<p style="text-align: center;"><a href="tarkastele">Tarkastele suorituksia</a></p>
</body>
</html>

