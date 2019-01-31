<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$seller = $_POST['seller'];
		$tanggal = date("Y-m-d");
		$idsayur = $_POST['idsayur'];
		$stok = $_POST['stok'];
		$harga = $_POST['harga'];
		$satuan = $_POST['satuan'];
		$area = $_POST['area'];

		$sql = "INSERT INTO sedia (seller,tanggal,idsayur,stok,harga,satuan,area) VALUES ('$seller','$tanggal','$idsayur','$stok','$harga','$satuan','$area')";

		require_once('db_Connect.php');

		if(mysqli_query($con,$sql)){
			echo "Provide Added Successfully";
		}else{
			echo "Could Not Add Comodity<br>";
			//printf("Errormessage: %s\n", mysqli_error($con));
		}

		mysqli_close($con);
	}
?>
