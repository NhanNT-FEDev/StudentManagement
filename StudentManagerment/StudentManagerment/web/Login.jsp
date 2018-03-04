<%-- 
    Document   : Login
    Created on : Jan 8, 2018, 11:14:52 PM
    Author     : Trung Nhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        
        <div>
            <form action="MainController" method="POST">
                Username <input type="text" name="txtUsername" value=""  required/> <br/>
                Password <input type="password" name="txtPassword" value="" required/> <br/>
                <input type="submit" value="Login" name="btnAction"/>
                <input type="reset" value="Reset" />
            </form>
           
        </div>
    </body>
</html>
