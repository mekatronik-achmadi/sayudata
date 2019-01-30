<?php
	require_once('db_Connect.php');

	/////////////////////////////////////////////////

	$tbl_como = "CREATE TABLE komoditi (
	 `id` INT(4) NOT NULL AUTO_INCREMENT ,
	 `sayur` TEXT NOT NULL ,
	 `img` TEXT NOT NULL ,
	  PRIMARY KEY (`id`)
	  ) ENGINE = InnoDB;";

	mysqli_query($con,$tbl_como);

	/////////////////////////////////////////////////

	$tbl_seller = "CREATE TABLE penjual (
	 `id` INT(4) NOT NULL AUTO_INCREMENT,
	 `name` TEXT NOT NULL,
	  PRIMARY KEY (`id`)
	  ) ENGINE = InnoDB;";

	mysqli_query($con,$tbl_seller);

	/////////////////////////////////////////////////

	$tbl_order = "CREATE TABLE pesan (
	 `id` INT(16) NOT NULL AUTO_INCREMENT,
	 `buyer` INT(4) NOT NULL,
	 `tanggal` DATE NOT NULL,
	 `sayur` INT(4) NOT NULL,
	 `jumlah` INT(16) NOT NULL,
	  PRIMARY KEY (`id`)
	  ) ENGINE = InnoDB;";

	mysqli_query($con,$tbl_order);

	/////////////////////////////////////////////////

	$tbl_provide = "CREATE TABLE sedia (
	 `id` INT(64) NOT NULL AUTO_INCREMENT,
	 `seller` TEXT NOT NULL,
	 `tanggal` DATE NOT NULL,
	 `sayur` INT(4) NOT NULL,
	 `stok` INT(16) NOT NULL,
	 `harga` INT(16) NOT NULL,
	 `satuan` TEXT NOT NULL,
	 `area` TEXT NOT NULL,
	  PRIMARY KEY (`id`)
	  ) ENGINE = InnoDB;";

	mysqli_query($con,$tbl_provide);

	/////////////////////////////////////////////////

	mysqli_close($con);
?>
