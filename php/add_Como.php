<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$sayur = $_POST['sayur'];
		$img = $_POST['img'];

		$sql = "INSERT INTO komoditi (sayur,img) VALUES ('$sayur','$img')";

		require_once('db_Connect.php');

		if(mysqli_query($con,$sql)){
			echo "Sayur Added Successfully: $sayur";
		}else{
			echo "Could Not Add Sayur: $sayur";
		}

		mysqli_close($con);
	}
?>
