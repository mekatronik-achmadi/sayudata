<?php
	//Importing Database Script
	require_once('db_Connect.php');

	//Creating sql query
	$sql = "SELECT * FROM komoditi";

	//getting result
	$r = mysqli_query($con,$sql);

	//creating a blank array
	$result = array();

	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){

		//Pushing name and id in the blank array created
		array_push($result,array(
			"id"=>$row['id'],
			"sayur"=>$row['sayur']
		));
	}

	//Displaying the array in json format
	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>
