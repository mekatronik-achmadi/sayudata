<?php

	//Importing Database Script
	require_once('db_Connect.php');

	//Creating sql query
	$sql = "SELECT * FROM komoditi";

	//getting result
	$result = mysqli_query($con,$sql);

	echo "<table border='1'>
	<tr>
	<th>ID</th>
	<th>Sayur</th>
	</tr>";

	while($row = mysqli_fetch_array($result))
	{
	echo "<tr>";
	echo "<td>" . $row['id'] . "</td>";
	echo "<td>" . $row['sayur'] . "</td>";
	echo "</tr>";
	}
	echo "</table>";

	mysqli_close($con);
?>
