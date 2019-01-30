<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$seller = $_POST['seller'];
		$tanggal = date("Y-m-d H:i:s");
		$sayur = $_POST['sayur'];
		$stok = $_POST['stok'];
		$harga = $_POST['harga'];
		$satuan = $_POST['satuan'];
		$area = $_POST['area'];

		$sql = "INSERT INTO sedia (seller,tanggal,sayur,stok,harga,satuan,area) VALUES ('$seller','$tanggal','$sayur','$stok','$harga','$satuan','$area')";

		require_once('db_Connect.php');

		if(mysqli_query($con,$sql)){
			echo "Provide Added Successfully";
		}else{
			echo "Could Not Add Comodity";
		}

		mysqli_close($con);
	}
?>
