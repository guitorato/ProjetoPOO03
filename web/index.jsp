<%-- 
    Document   : index
    Created on : 19/06/2020, 13:56:38
    Author     : guih_
--%>

<%@page import="br.gov.sp.fatec.config.ConnectionDb"%>
<%@page import="br.gov.sp.fatec.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Início - QUIZ</title>
    </head>
     <body>
        <h1>SQLite</h1>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <h2>Index</h2>
        <%
        ArrayList<User> list = new ArrayList<>();
        try{
            list = User.getUsers();
        }catch(Exception ex){
            out.println("<h3>Erro: "+ex.getMessage()+"</h3>");
        }
        %>
        <table border="1">
            <tr>
                <th>Nome do usuário</th>
                <th>Login</th>
            </tr>
            <%for(User u: list){%>
            <tr>
                <td><%= u.getName() %></td>
                <td><%= u.getLogin() %></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>