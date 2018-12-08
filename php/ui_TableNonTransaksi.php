<?php
    echo "<h1>Welcome</h1>";

    ///////////////////////////////////////////

    require_once('db_Connect.php');

    ///////////////////////////////////////////

   	echo "<p>Tabel Komoditi.</p>";

	$sql0 = "SELECT * FROM komoditi";

	$result0 = mysqli_query($con,$sql0);

	echo "<table border='1'>
	<tr>
	<th>ID</th>
	<th>Sayur</th>
	</tr>";

	while($row0 = mysqli_fetch_array($result0))
	{
	echo "<tr>";
	echo "<td>" . $row0['id'] . "</td>";
	echo "<td>" . $row0['sayur'] . "</td>";
	echo "</tr>";
	}
	echo "</table>";

	echo "<br />";

	///////////////////////////////////////////

	echo "<p>Tabel Penjual.</p>";

	$sql1 = "SELECT * FROM penjual";

	$result1 = mysqli_query($con,$sql1);

	echo "<table border='1'>
	<tr>
	<th>ID</th>
	<th>NAMA</th>
	<th>WA</th>
	</tr>";

	while($row1 = mysqli_fetch_array($result1))
	{
	echo "<tr>";
	echo "<td>" . $row1['id'] . "</td>";
	echo "<td>" . $row1['name'] . "</td>";
	echo "<td>" . $row1['wa'] . "</td>";
	echo "</tr>";
	}
	echo "</table>";

	echo "<br />";

	///////////////////////////////////////////

	echo "<p>Tabel Pembeli.</p>";

	$sql2 = "SELECT * FROM pembeli";

	$result2 = mysqli_query($con,$sql2);

	echo "<table border='1'>
	<tr>
	<th>ID</th>
	<th>NAMA</th>
	<th>WA</th>
	</tr>";

	while($row2 = mysqli_fetch_array($result2))
	{
	echo "<tr>";
	echo "<td>" . $row2['id'] . "</td>";
	echo "<td>" . $row2['name'] . "</td>";
	echo "<td>" . $row2['wa'] . "</td>";
	echo "</tr>";
	}
	echo "</table>";

	mysqli_close($con);
?>
