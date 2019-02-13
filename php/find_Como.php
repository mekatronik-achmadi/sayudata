<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$sayur = $_POST['sayur'];
	
		require_once('db_Connect.php');
	
		$sql = "SELECT * FROM komoditi WHERE sayur LIKE '%$sayur%'";
	
		$r = mysqli_query($con,$sql);
	
		$result = array();
	
		while($row = mysqli_fetch_array($r)){
	
			array_push($result,array(
				"id"=>$row['id'],
				"sayur"=>$row['sayur'],
				"image"=>$row['image']
			));
		}
	
		echo json_encode(array('result'=>$result));
	
		mysqli_close($con);
	}
?>
