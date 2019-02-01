<?php
	require_once('db_Connect.php');

	/////////////////////////////////////////////////

	$tbl_como = "CREATE TABLE komoditi (
	 `id` INT(16) NOT NULL AUTO_INCREMENT ,
	 `sayur` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	 `img` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  PRIMARY KEY (`id`)
	  ) ENGINE = InnoDB;";

	mysqli_query($con,$tbl_como);
	
	/////////////////////////////////////////////////

	$tbl_wiki = "CREATE TABLE sayuwiki (
	 `id` INT(16) NOT NULL AUTO_INCREMENT,
	 `idsayur` INT(16) NOT NULL,
	 `wiki` TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  PRIMARY KEY (`id`)
	  ) ENGINE = InnoDB;";

	mysqli_query($con,$tbl_wiki);

	/////////////////////////////////////////////////

	$tbl_seller = "CREATE TABLE penjual (
	 `id` INT(16) NOT NULL AUTO_INCREMENT,
	 `name` TEXT NOT NULL,
	  PRIMARY KEY (`id`)
	  ) ENGINE = InnoDB;";

	mysqli_query($con,$tbl_seller);

	/////////////////////////////////////////////////

	$tbl_order = "CREATE TABLE pesan (
	 `id` INT(64) NOT NULL AUTO_INCREMENT,
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
	 `seller` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	 `tanggal` DATE NOT NULL,
	 `idsayur` INT(16) NOT NULL,
	 `stok` INT(16) NOT NULL,
	 `harga` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	 `satuan` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	 `area` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  PRIMARY KEY (`id`)
	  ) ENGINE = InnoDB;";

	mysqli_query($con,$tbl_provide);

	/////////////////////////////////////////////////

	mysqli_close($con);
?>
