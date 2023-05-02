<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>


<%
ArrayList<HashMap> record = (ArrayList<HashMap>)request.getAttribute("record");

%>

<body>
<form action="updaterecord" method="post" enctype="multipart/form-data">
<input type="text" value="<%=record.get(0).get("name") %>" name="name" placeholder="Name"><br>
<input type="email" value="<%=record.get(0).get("email") %>" name="email" placeholder="Email"><br>
<input type="password"  value="<%=record.get(0).get("password") %>" name="pass" placeholder="Password"><br>
<input type="tel" value="<%=record.get(0).get("mobile") %>" name="mobile" placeholder="Mobile"><br>
<input type="file" value="upload/<%=record.get(0).get("file") %>"  name="file">
<input type="submit">
</form>

</body>
</html>