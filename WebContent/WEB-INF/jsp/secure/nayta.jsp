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

<p style="text-align:center;">Suoritukset</p>
<p style="text-align:center;">Palautettu demo: ${demo}</p>
<p style="text-align:center;">Pistemäärä: ${pisteet}</p>
<p style="text-align: center;"><a href="site">Takaisin etusivulle</a></p>

</body>
</html>