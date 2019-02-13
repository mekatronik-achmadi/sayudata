<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Manage Sayuran Form</title>
	</head>

	<body>
		<h2> Tambah Komoditi </h2>
		<form action="add_Como.php" method="post">
			<p>
				Jenis Sayur &#9654; Nama Sayuran dalam bahasa Indonesia yang sekiranya dikenal luas. <br>
				Contoh &#9654; Bayam Merah
			</p>
			<p>
		    	<label for="JenisSayur">Jenis Sayur:</label>
		    	<input type="text" name="sayur" id="JenisSayur" required>
			</p>
			<p>
		    	<label for="GambarSayur">Gambar Sayur:</label>
		    	<input type="text" name="image" id="GambarSayur" required>
			</p>

			<input type="submit" value="Tambahkan">
			<input type="reset"  value="Bersihkan">
		</form>
		
		<h2> Upload Gambar Komoditi </h2>
		<form action="imgup_Como.php" method="post" enctype="multipart/form-data">
			<p>
				<input type="file" name="fileToUpload" id="fileToUpload" required>
			</p>
			<input type="submit" value="Upload Image" name="submit">
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
			   	echo "<h2>Tabel Komoditi.</h2>";
				$sql0 = "SELECT * FROM komoditi";
				$result0 = mysqli_query($con,$sql0);
				echo "<table border='1'>
				<tr>
				<th>No</th>
				<th>ID</th>
				<th>Sayur</th>
				<th>Image</th>
				</tr>";
				$i = 0;
				while($row0 = mysqli_fetch_array($result0))
				{
					$i++;
					echo "<tr>";
					echo "<td>" . $i . "</td>";
					echo "<td>" . $row0['id'] . "</td>";
					echo "<td>" . $row0['sayur'] . "</td>";
					echo "<td>" . $row0['image'] . "</td>";
					echo "</tr>";
				}
				echo "</table>";
		?>

	</body>
</html>
