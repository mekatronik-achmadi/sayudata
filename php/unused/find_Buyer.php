<?php

	$name = $_GET['name'];

	require_once('db_Connect.php');

	$sql = "SELECT * FROM pembeli WHERE name LIKE '%$name%'";

	$r = mysqli_query($con,$sql);

	$result = array();

	while($row = mysqli_fetch_array($r)){

		array_push($result,array(
			"id"=>$row['id'],
			"name"=>$row['name']
		));
	}

	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>
