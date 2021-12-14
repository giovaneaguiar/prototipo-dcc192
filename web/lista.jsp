
<%-- 
    Document   : lista
    Created on : 2 de dez. de 2021, 21:01:20
    Author     : giovaneaguiar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        
        import="java.sql.*"
       
        %>

<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300&display=swap" rel="stylesheet">
        <title>Listagem de Usuários</title>
    </head>
    <body style="font-family: 'Outfit', sans-serif;">
        <h1 align="center">Listagem de Usuários</h1>
        <form></form> 

<%
    try{
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
Connection connection = 
         DriverManager.getConnection
            ("jdbc:derby://localhost:1527/Giovane", "giovane", "aguiar");

       Statement statement = connection.createStatement();

       resultset = statement.executeQuery("select usuario from giovane.usuarios");
%>

<center>
        <h2>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
        <% } %>
        </h2>
        
</center>

<%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
        <h2 align="center">
        <a href="menu.jsp" style="text-decoration:none">Voltar para o Menu</a>
        </h2>
        
    </body>
</html>
