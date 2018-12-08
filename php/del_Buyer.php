<?php
	//Getting Id
	$id = $_GET['id'];

	//Importing database
	require_once('db_Connect.php');

	//Creating sql query
	$sql = "DELETE FROM pembeli WHERE id=$id;";

	//Deleting record in database
	if(mysqli_query($con,$sql)){
		echo 'Buyer Deleted Successfully';
	}else{
		echo 'Could Not Delete Buyer Try Again';
	}

	//closing connection
	mysqli_close($con);
?>
