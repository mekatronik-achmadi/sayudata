<?php
	//Getting Id
	$id = $_GET['id'];

	//Importing database
	require_once('dbConnect.php');

	//Creating sql query
	$sql = "DELETE FROM komoditi WHERE id=$id;";

	//Deleting record in database
	if(mysqli_query($con,$sql)){
		echo 'Sayur Deleted Successfully';
	}else{
		echo 'Could Not Delete Sayur Try Again';
	}

	//closing connection
	mysqli_close($con);
?>
