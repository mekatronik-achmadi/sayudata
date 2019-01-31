<?php
	$targetdir = "../../imagesayur/";
	$targetfile = $targetdir.basename($_FILES["fileToUpload"]["name"]);
	$uploadOk = 1;
	$imageFileType = strtolower(pathinfo($targetfile,PATHINFO_EXTENSION));
	
	if(isset($_POST["submit"])){
		$check = getimagesize($_FILES["fileToUpload"]["tmp_name"]);
		if($check !== false){
			echo "File is an image".$check["mime"].". \r\n";
			$uploadOk = 1;
		}
		else{
			echo "File not an image \r\n";
			$uploadOk = 0;
		}
	}
	
	if(file_exists($targetfile)){
		echo "File already exist. Overwriting \r\n";
	}
	
	if($_FILES["fileToUpload"]["size"] > 30000){
		echo "Sorry your file too big \r\n";
		$uploadOk = 0;
	}
	
	if($imageFileType != "jpg"){
		echo "Sorry your image is not JPG \r\n";
		$uploadOk = 0;	
	}
	
	if($uploadOk == 0){
		echo "Your image not uploaded \r\n";
	}
	else{
		if(move_uploaded_file($_FILES["fileToUpload"]["tmp_name"],$targetfile)){
			echo "File ".basename($_FILES["fileToUpload"]["name"])." uploaded \r\n";
		}
		else{
			echo "An error happen while uploading \r\n";
		}
	}
	
?>