<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Getting values
		$sayur = $_POST['sayur'];

		//Creating an sql query
		$sql = "INSERT INTO komoditi (sayur) VALUES ('$sayur')";

		//Importing our db connection script
		require_once('db_Connect.php');

		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'Sayur Added Successfully';
		}else{
			echo 'Could Not Add Sayur';
		}

		//Closing the database
		mysqli_close($con);
	}
?>
