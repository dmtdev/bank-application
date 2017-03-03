<%@ page import="ua.spalah.bank.model.Client" %>
<%@ page contentType="text/html; utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>FindClient</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body style="padding: 10px 10px 10px 10px">
<h4>FindClient:</h4>
<form method="post" action="/main" accept-charset="UTF-8">
    <input type="text" name="clientname">
    <input type="submit" value="FindClient">
</form>
${message}<br>
<%
    if(request.getAttribute("client")!= null) {
%>
<table class="table">
    <tr><td>Client </td><td><a href="#client_id=${client.clientId}">${clientname}</a></td></tr>
    <tr><td>City </td><td>${clientcity}</td></tr>
    <tr><td>Email </td><td>${client.email}</td></tr>
    <tr><td>Phone </td><td>${client.tel}</td></tr>
</table>
<%
        Client client = (Client) request.getAttribute("client");
        out.println(client.toString());
%>

<%
    }
%>
</body>
</html>