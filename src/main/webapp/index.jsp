<%@ page import="ua.spalah.bank.model.Client" %>
<%@ page contentType="text/html; utf-8" language="java" %>
<html>
<head>
    <title>FindClient</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body style="padding: 10px 10px 10px 10px">
</h3>


<h3>FindClient:</h3>
<form method="post" action="/main">
    <input type="text" name="clientname">
    <input type="submit" value="FindClient">
</form>
${message}<br>
${clientname}<br>
${clientcity}<br>
<%
    if(request.getAttribute("client")!= null) {
        Client client = (Client) request.getAttribute("client");
        out.println(client.toString());
%>
<br><br>
<a href="#">Client's accounts</a><br>
<a href="#">Add account</a><br>
<a href="#">Remove client</a><br>
<%
    }
%>
</body>
</html>