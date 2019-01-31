<?php
	$seller = $_GET['sayur'];

	require_once('db_Connect.php');
		
	$sql = "SELECT * FROM sedia WHERE sayur=$seller";

	$r = mysqli_query($con,$sql);

	$result = array();

	while($row = mysqli_fetch_array($r)){

		array_push($result,array(
			"id"=>$row['id'],
			"seller"=>$row['seller'],
			"tanggal"=>$row['tanggal'],
			"sayur"=>$row['sayur'],
			"stok"=>$row['stok'],
			"harga"=>$row['harga'],
			"satuan"=>$row['satuan'],
			"area"=>$row['area']
		));
	}

	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>