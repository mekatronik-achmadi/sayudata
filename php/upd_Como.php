<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values
		$id = $_POST['id'];
		$sayur = $_POST['sayur'];

		//importing database connection script
		require_once('db_Connect.php');

		//Creating sql query
		$sql = "UPDATE komoditi SET sayur = '$sayur' WHERE id = $id;";

		//Updating database table
		if(mysqli_query($con,$sql)){
			echo "Sayur Updated Successfully: $sayur";
		}else{
			echo "Could Not Update Sayur: $sayur";
		}

		//closing connection
		mysqli_close($con);
	}
?>
