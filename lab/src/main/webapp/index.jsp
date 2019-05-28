<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改資料</title>
<style>
form {
	margin: 0 auto;
	width: 600px;
}
</style>
</head>

<body>
	<form name="insertMemberFormA" action="uvi.do" method="POST">
		<table border="1">
			<thead>
				<tr bgcolor='tan'>
					<th height="60" colspan="2" align="center">修改資料</th>
				</tr>
			</thead>
			<tbody>
				<tr bgcolor='tan'>
					<td width="120" height="40">id:</td>
					<td width="600" height="40" align="left"><input id='num'
						style="text-align: left" name="Id" type="text" size="14"required>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">縣市:</td>
					<td width="600" height="40" align="left"><input id='num'
						style="text-align: left" name="County" type="text" size="14">
					</td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">發佈機關:</td>
					<td width="600" height="40" align="left"><input
						name="PublishAgency" type="text" size="20"></td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">發布日期:</td>
					<td width="600" height="40" align="left"><input
						name="PublishTime" type="date" size="14"></td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">測站名稱:</td>
					<td width="600" height="40" align="left"><input
						name="SiteName" type="text" size="20"></td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">紫外線指數:</td>
					<td width="600" height="40" align="left"><input name="UVI"
						type="text" size="14"></td>
				</tr>
				<tr bgcolor='tan'>
					<td height="50" colspan="2" align="center"><input
						type="submit" value="新增" name="action"> <input
						type="submit" value="刪除" name="action"> <input
						type="submit" value="修改" name="action"> <input
						type="submit" value="查詢" name="action"></td>
				</tr>
			</tbody>
		</table>
	</form>

</body>
</html>