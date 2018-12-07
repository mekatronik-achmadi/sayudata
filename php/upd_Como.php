<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values
		$id = $_POST['id'];
		$name = $_POST['sayur'];

		//importing database connection script
		require_once('dbConnect.php');

		//Creating sql query
		$sql = "UPDATE komoditi SET sayur = '$sayur' WHERE id = $id;";

		//Updating database table
		if(mysqli_query($con,$sql)){
			echo 'Sayur Updated Successfully';
		}else{
			echo 'Could Not Update Sayur Try Again';
		}

		//closing connection
		mysqli_close($con);
	}
?>
