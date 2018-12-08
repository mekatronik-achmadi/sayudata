<?php
	if($_SERVER['REQUEST_METHOD']=='GET'){

		$id = $_GET['id'];

		require_once('db_Connect.php');

		$sql0 = "SELECT FROM komoditi WHERE id=$id;";

		$r = mysqli_query($con,$sql0);

		$row = mysqli_fetch_array($r);
		$syr = $row['sayur'];

		$sql = "DELETE FROM komoditi WHERE id=$id;";

		if(mysqli_query($con,$sql)){
			echo "Sayur Deleted Successfully: $syr";
		}else{
			echo "Could Not Delete Sayur: $syr";
		}

		mysqli_close($con);
	}
?>
