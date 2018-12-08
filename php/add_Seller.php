<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Getting values
		$name = $_POST['name'];
		$wa = $_POST['wa'];

		//Creating an sql query
		$sql = "INSERT INTO penjual (name,wa) VALUES ('$name','$wa')";

		//Importing our db connection script
		require_once('db_Connect.php');

		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'Seller Added Successfully';
		}else{
			echo 'Could Not Add Seller';
		}

		//Closing the database
		mysqli_close($con);
	}
?>
