<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$sayur = $_POST['sayur'];

		$sql = "INSERT INTO komoditi (sayur) VALUES ('$sayur')";

		require_once('db_Connect.php');

		if(mysqli_query($con,$sql)){
			echo "Sayur Added Successfully: $sayur";
		}else{
			echo "Could Not Add Sayur: $sayur";
		}

		mysqli_close($con);
	}
?>
