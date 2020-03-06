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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>


        <title>Notes</title>
    </head>
    <body>

        <div class="container">


            <h2>Notes List</h2>
            <table class="table table-bordered align-middle text-center table-hover table-sm">
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
                                <input  type="hidden" name="action" value="edit">
                                <input type="hidden" name="selectedNote" value="${note.noteid}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${note == null}">
                <h2>Edit Note</h2>
                <form action="notes" method="POST">
                    <input class="form-control" type="text" name="title" value="Title"><br>
                    <textarea class="form-control" name="contents" rows="4">Type a note.</textarea><br>
                    <!--add-->
                    <input type="hidden" name="action" value="add">
                    <input class="form-control bg-success text-white font-weight-bold " type="submit" value="Add">
                </form>
            </c:if>

            <c:if test="${note != null}">
                <h2>Edit Note</h2>
                <form action="notes" method="POST">
                    <input class="form-control" type="text" name="title" value="${note.title}"><br>
                    <input type="hidden" name="noteid" value="${note.noteid}">
                    <input type="hidden" name="notedatecreated" value="${note.datecreated}">       
                    <textarea class="form-control" name="contents" rows="4">${note.contents}</textarea><br>
                    <!--save-->
                    <input type="hidden" name="action" value="save">
                    <input class="form-control bg-success text-white font-weight-bold" type="submit" value="Save">
                </form>
                <br>
                <form action="notes" method="post" >
                    <!--delete-->
                    <input  class="form-control bg-danger text-white font-weight-bold" type="submit" value="Delete">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="selectedNote" value="${note.noteid}">
                </form>
            </c:if>

        </div>
    </body>
</html>
