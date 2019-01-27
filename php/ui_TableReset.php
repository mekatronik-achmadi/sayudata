<?php

	$tabel= $_GET['tabel'];

	require_once('db_Connect.php');

	$sql = "TRUNCATE TABLE $tabel";

	mysqli_query($con,$sql);

	mysqli_close($con);
	
	echo "<p>Tabel telah di-reset: $tabel</p>";
?>
