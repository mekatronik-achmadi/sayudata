<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Manage Pesanan Form</title>
	</head>

	<body>
		<h2> Tambah Persediaan </h2>
		<form action="add_Provide.php" method="post">
			<p>
		    	<label for="Seller">Penjual:</label>
		    	<input type="text" name="seller" id="Seller" required>
			</p>
			<p>
		    	<label for="Sayur">Sayur:</label>
		    	<input type="text" name="sayur" id="Sayur" required>
			</p>
			<p>
		    	<label for="Stok">Stok:  </label>
		    	<input type="number" name="stok" id="Stok" required>
			</p>
			<p>
		    	<label for="Harga">Harga:  </label>
		    	<input type="number" name="harga" id="Harga" required>
			</p>
			<p>
		    	<label for="Satuan">Satuan:  </label>
		    	<input type="text" name="satuan" id="Satuan" required>
			</p>
			<p>
		    	<label for="Area">Area:  </label>
		    	<input type="text" name="area" id="Area" required>
			</p>

			<input type="submit" value="Tambahkan">
			<input type="reset"  value="Bersihkan">
		</form>

		<h2> Hapus Komoditi </h2>
		<form action="del_Como.php" method="post">
			<p>
		    	<label for="IDSayur">ID Sayur:  </label>
		    	<input type="number" name="id" id="IDSayur" required>
			</p>

			<input type="submit" value="Hapuskan">
			<input type="reset"  value="Bersihkan">
		</form>

		<h2> Ganti Nama Komoditi </h2>
		<form action="upd_Como.php" method="post">
			<p>
		    	<label for="IDSayur">ID Sayur:  </label>
		    	<input type="number" name="id" id="IDSayur" required>
			</p>

			<p>
		    	<label for="NamaSayur">Nama Sayur:  </label>
		    	<input type="text" name="sayur" id="NamaSayur" required>
			</p>
			
			<p>
		    	<label for="GambarSayur">Gambar Sayur:  </label>
		    	<input type="text" name="image" id="GambarSayur" required>
			</p>

			<input type="submit" value="Gantikan">
			<input type="reset"  value="Bersihkan">
		</form>

		<?php
			require_once('db_Connect.php');
		
			$sql = "SELECT * FROM sedia";
		
			$r = mysqli_query($con,$sql);
		
			$result = array();
		
			while($row = mysqli_fetch_array($r)){
		
				array_push($result,array(
					"id"=>$row['id'],
					"seller"=>$row['seller'],
					"tanggal"=>$row['tanggal'],
					"sayur"=>$row['sayur'],
					"stok"=>$row['stok'],
					"harga"=>$row['harga'],
					"satuan"=>$row['satuan'],
					"area"=>$row['area']
				));
			}
		
			echo json_encode(array('result'=>$result));
		
			mysqli_close($con);
		?>

	</body>
</html>
