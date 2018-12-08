<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Getting values
		$name = $_POST['name'];
		$wa = $_POST['wa'];

		//Creating an sql query
		$sql = "INSERT INTO pembeli (name,wa) VALUES ('$name','$wa')";

		//Importing our db connection script
		require_once('db_Connect.php');

		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'Buyer Added Successfully';
		}else{
			echo 'Could Not Add Buyer';
		}

		//Closing the database
		mysqli_close($con);
	}
?>
