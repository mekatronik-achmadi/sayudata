<?php

	$id = $_GET['id'];

	require_once('db_Connect.php');

	$sql = "SELECT * FROM komoditi WHERE id=$id";

	$r = mysqli_query($con,$sql);

	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id"=>$row['id'],
			"sayur"=>$row['sayur'],
		));

	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>
