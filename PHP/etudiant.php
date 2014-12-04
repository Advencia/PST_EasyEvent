<?php
$host = "localhost"; 
$user = "root"; 
$pwd = ""; 
$db = "gala"; 

// Créer connection
$con = mysqli_connect($host, $user, $pwd, $db);

// test connection
if(mysqli_connect_errno($con)) {
	die("Failed to connect to MySQL: " . mysqli_connect_error());
}

//recupere les données
$sql = "SELECT * FROM etudiant ORDER By id";
$result = mysqli_query($con, $sql);

// tab pr sauver les données
$rows = array();
while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
	$rows[] = $row;
}

// fermer connection
mysqli_close($con);
echo json_encode($rows);
?>