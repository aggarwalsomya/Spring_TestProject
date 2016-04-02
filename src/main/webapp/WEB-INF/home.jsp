<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cmpe275_Lab2_Group_20 : Add Profile</title>
</head>
<body>


	<h1>Add New</h1>
	<form method="POST" action="addProfile">
		<table>
			<tr>
				<td><label>First Name</label></td>
				<td><input type="text" name="fname"
					placeholder="enter first name" required /></td>
			</tr>

			<tr>
				<td><label>Last Name</label></td>

				<td><input type="text" name="lname" placeholder="enter last name"
					required /></td>

			</tr>
			<tr>
				<td><label>Email</label></td>
				<td><input type="email" name="email" required></td>
			</tr>
			<tr>
				<td><label>Address</label></td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td><label>Organization</label></td>
				<td><input type="text" name="org" /></td>
			</tr>
			<tr>
				<td><label>About Myself</label></td>
				<td><input type="text" name="amy" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
