<?php

	$sayur = $_GET['sayur'];

	require_once('db_Connect.php');

	$sql = "SELECT * FROM komoditi WHERE sayur='%$sayur%'";

	$r = mysqli_query($con,$sql);

	$result = array();

	while($row = mysqli_fetch_array($r)){

		array_push($result,array(
			"id"=>$row['id'],
			"sayur"=>$row['sayur']
		));
	}

	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>
