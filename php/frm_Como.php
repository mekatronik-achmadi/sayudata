<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Manage Sayuran Form</title>
	</head>

	<body>
		<form action="add_Como.php" method="post">
			<p>
		    	<label for="JenisSayur">Jenis Sayur:</label>
		    	<input type="text" name="sayur" id="JenisSayur" required>
			</p>

			<input type="submit" value="Tambahkan">
			<input type="reset"  value="Bersihkan">
		</form>

		<form action="del_Como.php" method="get">
			<p>
		    	<label for="IDSayur">ID Sayur:  </label>
		    	<input type="number" name="id" id="IDSayur">
			</p>

			<input type="submit" value="Hapuskan">
			<input type="reset"  value="Bersihkan">
		</form>

		<?php
				require_once('db_Connect.php');
			   	echo "<p>Tabel Komoditi.</p>";
				$sql0 = "SELECT * FROM komoditi";
				$result0 = mysqli_query($con,$sql0);
				echo "<table border='1'>
				<tr>
				<th>No</th>
				<th>ID</th>
				<th>Sayur</th>
				</tr>";
				$i = 0;
				while($row0 = mysqli_fetch_array($result0))
				{
					$i++;
					echo "<tr>";
					echo "<td>" . $i . "</td>";
					echo "<td>" . $row0['id'] . "</td>";
					echo "<td>" . $row0['sayur'] . "</td>";
					echo "</tr>";
				}
				echo "</table>";
		?>

	</body>
</html>
