<?php
	$host="localhost";
	$uname="root";
	$pwd='';
	$db="gala";

	$con = mysql_connect($host,$uname,$pwd,$db) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	 
	$prenom=$_REQUEST['prenom'];
	$nom=$_REQUEST['nom'];
	$email=$_REQUEST['email'];
	$prevente=$_REQUEST['prevente'];
	$flag['code']=0;
	 
	if($r=mysql_query("UPDATE etudiant SET prenom='$prenom',nom = '$nom',email='$email',prevente='$prevente' WHERE email ='$email'",$con))
	{
		$flag['code']=1;
	}
	 
	print(json_encode($flag));
	mysql_close($con);
?>