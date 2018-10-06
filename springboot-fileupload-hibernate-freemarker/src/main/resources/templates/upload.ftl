 <!DOCTYPE html>
<html>
	<head>
		<title>Upload</title>
	</head>
<body>
	<div id="container">
		<h1 style="text-align: center;">Upload you files to server</h1>
		<div id="uploadFrom">
			<form action="fileUpload" method="POST" enctype="multipart/form-data">
				File: <input type="file" name="file"/><br />
				File: <input type="file" name="file"/><br />
				<input type="submit" value="Upload"/>
			</form>
		</div>
	<div>
	<div>
		<#if status??>
			${status}
		</#if>
	</div>
</body>
</html> 