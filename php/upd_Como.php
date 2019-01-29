<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$id = $_POST['id'];
		$sayur = $_POST['sayur'];
		$image = $_POST['image'];

		require_once('db_Connect.php');

		$sql = "UPDATE komoditi SET sayur = '$sayur',  image = '$image' WHERE id = $id;";

		if(mysqli_query($con,$sql)){
			echo "Sayur Updated Successfully: $sayur";
		}else{
			echo "Could Not Update Sayur: $sayur";
		}

		mysqli_close($con);
	}
?>
