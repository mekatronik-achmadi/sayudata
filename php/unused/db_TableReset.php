<?php

	require_once('db_Connect.php');
	
	$tbl_como = "TRUNCATE TABLE komoditi;";
	mysqli_query($con,$tbl_como);
	
	$tbl_seller = "TRUNCATE TABLE penjual;";
	mysqli_query($con,$tbl_seller);
	
	$tbl_buyer = "TRUNCATE TABLE pembeli;";
	mysqli_query($con,$tbl_buyer);
	
	$tbl_order = "TRUNCATE TABLE pesan;";
	mysqli_query($con,$tbl_order);
	
	$tbl_provide = "TRUNCATE TABLE sedia;";
	mysqli_query($con,$tbl_provide);
	
	mysqli_close($con);
	
	echo "<p>Seluruh Tabel telah di-reset.</p>";
?>
