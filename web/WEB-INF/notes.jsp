<%-- 
    Document   : notes
    Created on : Mar 1, 2020, 7:35:52 PM
    Author     : 798419
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <br>

        <table>
            <tr>
                <th>Date Created</th>
                <th>Title</th>
                <th>Edit</th>
            </tr>

            <c:forEach var="note" items="${notes}">
                <tr>
                    <td>${note.datecreated}</td>
                    <td>${note.title}</td>
                    <td>
                        <form action="notes" method="post">
                            <input type="submit" value="Edit">
                            <!--edit-->
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="selectedNote" value="${note.noteid}">
                        </form>
                    </td>
                </tr>              
            </c:forEach>
        </table>

        <c:if test="${note == null}">
            <h1>Edit Note</h1>
            <form action="notes" method="POST">
                <input type="text" name="title" value="title"><br>
                <textarea name="contents">type a note....</textarea><br>
                <!--add-->
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add">
            </form>
        </c:if>

        <c:if test="${note != null}">
            <h1>Edit Note</h1>

            <form action="notes" method="post" >
                <input type="submit" value="Delete">
                <!--delete-->
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="selectedNote" value="${note.noteid}">
            </form><br>
            <form action="notes" method="POST">
                <input type="text" name="title" value="${note.title}"><br>
                <input type="hidden" name="noteid" value="${note.noteid}"><br>
                <input type="hidden" name="notedatecreated" value="${note.datecreated}"><br>                
                <textarea name="contents">${note.contents}</textarea><br>
                <!--save-->
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save">
            </form>
        </c:if>



    </body>
</html>
