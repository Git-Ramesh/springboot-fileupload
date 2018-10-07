<%@page isELIgnored="false"%>
<html>
<body>
	<div>
		<h2 style="text-align: center;">FileUpload</h2>
		<div>
			<form action="upload" method="post" enctype="multipart/form-data">
				Select Files: <input type="file" name="file" multiple="multiple" /><br />
				<input type="submit" value="Upload"/>
			</form>
		</div>
		<div>${status}</div>
	</div>
</body>
</html>
