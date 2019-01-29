<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$sayur = $_POST['sayur'];
		$image = $_POST['image'];

		$sql = "INSERT INTO komoditi (sayur,image) VALUES ('$sayur','$image')";

		require_once('db_Connect.php');

		if(mysqli_query($con,$sql)){
			echo "Sayur Added Successfully: $sayur";
		}else{
			echo "Could Not Add Sayur: $sayur";
		}

		mysqli_close($con);
	}
?>
