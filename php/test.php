<!DOCTYPE html>
<html lang="en">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<style>
			* {
			  box-sizing: border-box;
			}

			.column {
			  float: left;
			  width: 50%;
			  padding: 10px;
			  height: 300px; 
			}

			.row:after {
			  content: "";
			  display: table;
			  clear: both;
			}
		</style>
	</head>
	<body>
		<h2>Two Equal Columns</h2>
	
		<div class="row">
		  <div class="column" style="background-color:#aaa;">
			<h2>Column 1</h2>
			<p>Some text..</p>
		  </div>
		  <div class="column" style="background-color:#bbb;">
			<h2>Column 2</h2>
			<p>Some text..</p>
		  </div>
		</div>
	</body>
</html>