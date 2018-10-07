<#import "utils.ftl" as u>
<@u.page title="upload">
	<div id="container">
		<h1 style="text-align: center;">Upload you files to server</h1>
		<div id="uploadFrom">
			<form action="fileUpload" method="POST" enctype="multipart/form-data">
				Files: <input type="file" name="file" mutiple/><br />
				<input type="submit" value="Upload"/>
			</form>
		</div>
	</div>
	<div>
		<#if status??>
			${status}
		</#if>
	</div>
</@u.page>
