<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$name = $_POST['name'];

		$sql = "INSERT INTO penjual (name) VALUES ('$name')";

		require_once('db_Connect.php');

		if(mysqli_query($con,$sql)){
			echo "Seller Added Successfully: $name";
		}else{
			echo "Could Not Add Seller: $name";
		}

		mysqli_close($con);
	}
?>
