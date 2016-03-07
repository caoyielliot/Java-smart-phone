<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Option Set</title>
<style>
table, td, th {
    border: 1px solid black;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th {
    height: 50px;
}
</style>
</head>
<body>
	<div>
		<h1>Basic Car Choice<h1>
		<form action="/project1_unit5_client/selectedChoices"  method="post" >
			<table >
			<tr>
				<td>Make/Model:</td>
				<td>
				 <input type="text" name="selectedchoices"" value="${model}">
				</td>
			</tr>
			<tr>
		  <td>Color:</td>
				<td>
					<select name="selectedchoices">
						${Color}
					</select>
				</td>
			</tr>
			<tr>
				<td>Transmission:</td>
				<td>
					<select name="selectedchoices">
						${Transmission}
					</select>
				</td>
			</tr>
			<tr>
				<td>Brakes/TractionControl:</td>
				<td>
					<select name="selectedchoices">
						${BrakesTractionControl}
					</select>
				</td>
			</tr>
			
			<tr>
				<td>Side Impact Air Bags</td>
				<td>
					<select name="selectedchoices">
						${SideImpactAirbags}
					</select>
				</td>
			</tr> 
			<tr>
				<td>Power Moonroof</td>
				<td>
					<select name="selectedchoices">
						${PowerMoonroof}
					</select>
				</td>
			</tr>   
		    </table>
		    <input type="submit" value="DONE">
		</form>
	</div>
    
</body>
</html>