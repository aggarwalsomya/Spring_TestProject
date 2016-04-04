<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cmpe275_Lab2_Group_20 : User Profile</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#Delete').click(function() {
			$.ajax({
				url : 'http://52.53.209.152:8080/profile/' + $('#id').val(),
				type : 'DELETE',
	            success: function(msg){
            		window.location.href = "http://52.53.209.152:8080/profile"
	            }
			});
		});
	});
</script>

</head>
<body>

	<form method="POST" action="updateProfile">
		<table>
			<tr>
				<td><label>ID:</label></td>
				<td><input type="text" name="id" id="id" value="${id}" readonly /></td>
			</tr>

			<tr>
				<td><label>First Name</label></td>
				<td><input type="text" name="fname" value="${fname}" required /></td>
			</tr>

			<tr>
				<td><label>Last Name</label></td>

				<td><input type="text" name="lname" value="${lname}" required /></td>

			</tr>
			<tr>
				<td><label>Email</label></td>
				<td><input type="email" value="${email}" name="email" required></td>
			</tr>
			<tr>
				<td><label>Address</label></td>
				<td><input type="text" name="address" value="${address}"	required /></td>
			</tr>
			<tr>
				<td><label>Organization</label></td>
				<td><input type="text" name="org" value="${org}" required /></td>
			</tr>
			<tr>
				<td><label>About Myself</label></td>
				<td><input type="text" name="amy" value="${amy}" required /></td>
			</tr>
			<tr>
				<td><input type="submit" name="Update" class="submit"
					value="Update"></td>

				<td><input type="button" class=button value="Delete"
					id="Delete" name="Delete"></td>
			</tr>
		</table>
	</form>


</body>
</html>