<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$name = $_POST['name'];

		$sql = "INSERT INTO pembeli (name) VALUES ('$name')";

		require_once('db_Connect.php');

		if(mysqli_query($con,$sql)){
			echo "Buyer Added Successfully: $name";
		}else{
			echo "Could Not Add Buyer: $name";
		}

		mysqli_close($con);
	}
?>
