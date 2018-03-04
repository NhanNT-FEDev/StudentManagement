<%-- 
    Document   : Insert
    Created on : Jan 11, 2018, 11:37:41 PM
    Author     : Trung Nhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
        <style>
            h1 {
                text-align: center;
                font-size: 3em;
                color: chocolate;
            }
            #wrapper {
                width: 30%;
                margin: 50px auto;
                padding: 50px;
                background: #D7FBFF;
            }
            form {
                margin: 30px auto;
            }
            .textInput {
                border: none;
                height: 20px;
                margin: 2px;
                border: 1px solid #6B7363;
                font-size: 1.2em;
                padding: 5px;
                width: 95%;
            }
            .textInput:focus {
                outline: none;
            }
            .btn {
                width: 98.6%;
                border: none;
                margin-top: 5px;
                color: white;
                background-color: #3b5998;
                border-radius: 5px;
                padding: 12px;
            }        label {  font-size: 1.5em;   }
        </style>
    </head>
    <body>
        <h1>This Is Insert Page</h1>
        <div id="wrapper">
            <form action="MainController" method="POST" onsubmit="return Validate()" name="vform" >
                <div id="username_div">
                    <label>UserID</label>
                    <input type="text" name="insertUserID" value="" placeholder="Enter your ID" class="textInput" required/> </br>
                    <c:set var="rs" value="${requestScope.DUPLICATEUSERID}" />
                    <c:if test="${rs}" >
                        <font color="red">
                            <h3>This UserID has been existed</h3>
                        </font>
                    </c:if>
                </div>
                <div id="password_div">
                    <label>Password</label>
                    <input type="password" name="insertPassword" value="" placeholder="Enter your password" required class="textInput"/> </br>

                </div>
                <div id="pass_confirm_div">
                    <label>Re-Password</label>
                    <input type="password" name="rePassword" value="" placeholder="Enter your password again" required class="textInput"/> </br>
                    <div id="password_error"></div>
                </div>
                <div id="firstname_div">
                    <label>FirstName</label>
                    <input type="text" name="insertFirstName" value="" placeholder="Enter your FirstName" required class="textInput"/> </br>
                    <div id="firstname_error"></div>
                </div>

                <div id="lastname_div">
                    <label>LastName</label>
                    <input type="text" name="insertLastName" value="" placeholder="Enter your Lastname" required class="textInput"/> <br/>
                    <div id="lastname_error"></div>
                </div>
                <div id="email_div">
                    <label>Email</label>
                    <input type="email" name="insertEmail" value="" placeholder="Enter your email" required class="textInput"/> <br/>
                    <div id="email_error"></div>
                </div>
                <div id="website_div">
                    <label>Website</label>
                    <input type="text" name="insertWebsite" value="" placeholder="Enter your website" required class="textInput"/> <br/>
                    <div id="website_error"></div>
                </div>
                <div id="role_div">
                    <label> Role</label>
                    <select name="Role">
                        <!-- <option value="1"> Admin </option>
                         <option value="2"> Subcriber </option>
                        -->
                        <c:set var="result" value="${requestScope.SHOWROLE}"/>
                        <c:forEach var="dto" items="${pageScope.result}">
                            <option value="${dto.roleID}">${dto.roleName}</option>
                        </c:forEach>


                    </select> 
                </div>              
                <!-- 
                    Click submit => checkUserIDServlet(true) => insertServlet(true) => AdminServlet
                                (false)=>ShowRoleServlet => insert.jsp 
                    
                -->
                <label>Send Notification</label>
                <input type="checkbox" name="chkSendNoti" 
                       value="chkSendNoti"
                        
                       /> Send the new User an email 
                
                <input type="submit" name="btnAction" value="Insert" class="btn"/> 
            </form>
        </div>

    </body>
</html>
