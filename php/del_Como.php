<?php
	//Getting Id
	$sayur = $_GET['id'];

	//Importing database
	require_once('db_Connect.php');

	//Creating sql query
	$sql = "DELETE FROM komoditi WHERE id=$id;";

	echo "ID is $id";
	//Deleting record in database
	if(mysqli_query($con,$sql)){
		echo 'Sayur Deleted Successfully';
	}else{
		echo 'Could Not Delete Sayur Try Again';
	}

	//closing connection
	mysqli_close($con);
?>
