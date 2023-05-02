<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${record}
${record.get(0).get("name")}

<%
ArrayList<HashMap> record = (ArrayList<HashMap>)request.getAttribute("record");
%>

<table border="2px">
<tr>
<th>Name</th>
<th>Email</th>
<th>Password</th>
<th>Mobile</th>
<th>Image</th>
<td>Operation</td>
</tr>

<% for(int i=0;i<record.size();i++) { %>
<tr>
<td><%=record.get(i).get("name") %></td>
<td><%=record.get(i).get("email") %></td>
<td><%=record.get(i).get("password") %></td>
<td><%=record.get(i).get("mobile") %></td>
<td><img alt="Image" src="upload/<%=record.get(i).get("image") %>" height="100px" width="80px"></td>
<td><a href='update?email=<%=record.get(i).get("email") %>'>Update</a> | <a href='delete?email=<%=record.get(i).get("email") %>'>Delete</a></td>
</tr>

<% }%>
</table>



</body>
</html>









</tr>

</table>


</body>
</html>