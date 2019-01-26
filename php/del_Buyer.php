<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$id = $_POST['id'];

		require_once('db_Connect.php');

		$sql0 = "SELECT * FROM pembeli WHERE id=$id;";

		$r0 = mysqli_query($con,$sql0);

		$row0 = mysqli_fetch_array($r0);
		$nm = $row0['name'];

		$sql = "DELETE FROM pembeli WHERE id=$id;";

		if(mysqli_query($con,$sql)){
			echo "Buyer Deleted Successfully: $nm";
		}else{
			echo "Could Not Delete Sayur: $nm";
		}

		mysqli_close($con);
	}
?>
