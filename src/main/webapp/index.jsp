<%@ page import="ua.spalah.bank.model.Client" %>
<%@ page contentType="text/html; utf-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>FindClient</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <meta charset="utf-8">
</head>
<body style="padding: 10px 10px 10px 10px">
<h4>FindClient:</h4>
<form method="post" action="/main">
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
проверка utf-8 :(<br>
<%
        Client client = (Client) request.getAttribute("client");
        out.println(client.toString());
%>

<%
    }
%>
</body>
</html>