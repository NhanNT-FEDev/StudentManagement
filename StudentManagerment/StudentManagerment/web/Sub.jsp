<%-- 
    Document   : Sub
    Created on : Jan 11, 2018, 4:27:16 AM
    Author     : Trung Nhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sub Page</h1>
        <h3>Welcome, ${sessionScope.USERNAME}</h3>
        <h4>
            <c:url var="logOut" value="MainController" >
                <c:param name="btnAction" value="LogOut"/>
            </c:url><a href="${logOut}">Logout</a>
        </h4>
        <h1> SHOW ALL USER </h1>
        <c:set var="showInfo" value="${requestScope.SHOWINFO}"/>
        <c:if test="${not empty showInfo}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Gmail</th>
                        <th>Website</th>
                        <th>Role</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${pageScope.showInfo}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.username}</td>
                            <td>${dto.email}</td>
                            <td>${dto.website}</td>
                            <td>${dto.roleID}</td>
                            <td>
                                <c:url var="viewDetail" value="MainController">
                                    <c:param name="btnAction" value="UpdateProfile"/>
                                </c:url><a href="${viewDetail}">Update Profile</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
    </body>
</html>
