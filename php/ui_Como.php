<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Manage Sayuran Form</title>
		<style>
			* {
			  box-sizing: border-box;
			}

			.column {
			  float: left;
			  width: 50%;
			  padding: 10px;
			  height: 300px; 
			}

			.row:after {
			  content: "";
			  display: table;
			  clear: both;
			}
		</style>
	</head>
	<body>
		<div class="row">
		  <div class="column">
			<h2> Tambah Komoditi </h2>
			<form action="add_Como.php" method="post">
				<p>
					Jenis Sayur &rarr; Nama Sayuran dalam bahasa Indonesia yang sekiranya dikenal luas. <br>
					Contoh &rarr; Bayam Merah
				</p>
				<p>
					<label for="JenisSayur">Jenis Sayur:</label>
					<input type="text" name="sayur" id="JenisSayur" onkeyup="setImgName();" required>
				</p>
				<p>
					Gambar Sayur &rarr; Nama File gambar sesuai nama sayur. <br>
					- tanpa ekstensi file. <br>
					- tanpa spasi. <br>
					- semua huruf kecil. <br>
					Contoh &rarr; bayammerah
				</p>
				<p>
					<label for="GambarSayur">Gambar Sayur:</label>
					<input type="text" name="image" id="GambarSayur" readonly required>
				</p>
	
				<input type="submit" value="Tambahkan">
				<input type="reset"  value="Bersihkan">
				
				<script>
				function setImgName() {
				  var txtsayur = document.getElementById("JenisSayur").value;
				  var strsayur = txtsayur.toLowerCase();
				  document.getElementById("GambarSayur").value = strsayur.replace(/\s+/g, '');
				}
				</script>
			</form>
		  </div>
		  
		  <div class="column">
			<h2> Upload Gambar Komoditi </h2>
			<script>
				function readURL(input) {
					if (input.files && input.files[0]) {
					  var reader = new FileReader();
				  
					  reader.onload = function(e) {
						$('#imgview').attr('src', e.target.result);
					  };
					  reader.readAsDataURL(input.files[0]);
					}
				}
				$("#fileToUpload").change(function() {
					readURL(this);
				});
			</script>
			<form action="imgup_Como.php" method="post" enctype="multipart/form-data" runat="server">
				<p>
					<input type="file" name="fileToUpload" id="fileToUpload" required>
					<img id="imgview" src="#"/>
				</p>
				<input type="submit" value="Upload Image" name="submit">
				<input type="reset"  value="Bersihkan">
			</form>
		  </div>
		</div>
		
		<div class="row">
			<div class="column">
				<h2> Hapus Komoditi </h2>
				<form action="del_Como.php" method="post">
					<p>
						<label for="IDSayur">ID Sayur:  </label>
						<input type="number" name="id" id="IDSayur" required>
					</p>
		
					<input type="submit" value="Hapuskan">
					<input type="reset"  value="Bersihkan">
				</form>
			</div>
			
			<div class="column">
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
			</div>
		</div>
		
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