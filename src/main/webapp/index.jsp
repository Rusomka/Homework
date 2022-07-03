<%@ page import="com.example.lesson2.LoginServlet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Enterprise-JSP</title>
    </head>

         <body>
            <% String login = (String) session.getAttribute("use_login");%>
            <%String  age = request.getAttribute("age")==null?"":(String) request.getAttribute("age");%>
            <%String wrongData = request.getAttribute("wrongData")==null?"":(String)request.getAttribute("wrongData");%>
            <%String fields= request.getAttribute("fields")==null?"":(String) request.getAttribute("fields");%>

            <% if (login == null || "".equals(login)){ %>
                 <form action="/login" method="POST">
                     Login: <input type="text" name="login"><br>
                     Password: <input type="password" name="password"  placeholder="over 10 symbols"><br>
                     <h3 style="color:green"> Пароль должен содержать только латинские буквы минимум одну цифру, заглавную букву и не мене 10 символов.</h3>
                     Age: <input type="text" name="age"><br>
                     <h1 style="color:Red"> <%=age%> </h1>
                        <input type="submit"/>
                     <h1 style="color:Red"> <%=fields%> </h1>
                     <h1 style="color:Red"> <%=wrongData%> </h1>

                 </form>
             <%} else {%>
                <h1>You are logged in as: <%= login %></h1>
                <br>Click this link to <a href="/login?a=exit">logout</a>
            <%}%>

         </body>
</html>