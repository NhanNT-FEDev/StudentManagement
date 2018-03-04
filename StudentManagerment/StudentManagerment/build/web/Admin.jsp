<%-- 
    Document   : Admin
    Created on : Jan 11, 2018, 4:13:10 AM
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
        <script type="text/javascript">
            function checkAll(checkRole) {
                checkName = document.getElementsByName("chkAdmin");
                for (var i = 0; i < checkName.length; i++) {
                    checkName[i].checked = checkRole.checked;
                }
            }
        </script>
        <h2>Welcome, ${sessionScope.USERNAME}</h2>
        <h4>
            <c:url var="logOut" value="MainController" >
                <c:param name="btnAction" value="LogOut"/>
            </c:url><a href="${logOut}">Logout</a>
        </h4>
        <h1>SHOW ALL USER</h1>
        <!--All là phải có rồi-->
        <c:url var="loadMember" value="MainController" >
            <c:param name="btnAction" value="AdminPage"/>
            <c:param name="roleID" value=""/>
        </c:url>  <a href="${loadMember}" >All(${requestScope.ALLSIZE})</a> ,


        <c:forEach var="roleCount" items="${requestScope.LISTCOUNT}" varStatus="counter">
            <c:url var="loadMember" value="MainController" >
                <c:param name="btnAction" value="AdminPage"/>
                <c:param name="roleID" value="${roleCount['key'].roleID}"/>
            </c:url>
            <a href="${loadMember}">${roleCount['key'].roleName}(${roleCount['value']})   </a>,
        </c:forEach>


        <c:set var="currentRoleID" value="${requestScope.ROLEID}"/>
        <!--Xử lí đống trên-->
        <div id="searchAll">
            <form action="MainController" method="POST">
                Search User <input type="search" name="txtSearchValue" value=""/>
                <input type="hidden" name="roleID" value="${currentRoleID}" />
                <input type="submit" name="btnAction" value="Search" />
            </form>
        </div> 
        <form action="MainController" method="POST">
            <!--Load role here-->
            <!--
                
            -->
            <select name="ChangeRole">
                <c:set var="loadRole" value="${requestScope.LISTROLE}"/>
                <c:forEach var="rs" items="${pageScope.loadRole}" >
                    <option value="${rs.roleID}">${rs.roleName}</option>
                </c:forEach>
            </select>
            <input type="submit" name="btnAction" value="Change"/>
            <c:set var="result" value="${requestScope.LISTMEMBER}" />
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th><input type="checkbox" onclick="checkAll(this)"/>Check All</th>

                            <th>Email</th>
                            <th>Website</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${pageScope.result}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="${dto.username}" 
                                           <c:if test="${dto.username}">
                                               checked="checked"
                                           </c:if>
                                           />
                                    ${dto.username}
                                </td>
                                <td>${dto.email}</td>
                                <td>${dto.website}</td>
                                <td>${dto.roleID}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </form>
        <c:url var="Insert" value="MainController">
            <c:param value="Insert New User" name="btnAction"/>
        </c:url><a href="${Insert}">Insert User Here</a>

    </body>
</html>
