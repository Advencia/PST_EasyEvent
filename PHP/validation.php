<?php
	$host="localhost";
	$uname="root";
	$pwd='';
	$db="gala";

	$con = mysql_connect($host,$uname,$pwd,$db) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	 
	$email=$_REQUEST['email'];
	$validation=$_REQUEST['validation'];
	$flag['code']=0;
	 
	if($r=mysql_query("UPDATE etudiant SET validation='$validation' WHERE email ='$email'",$con))
	{
		$flag['code']=1;
	}
	 
	print(json_encode($flag));
	mysql_close($con);
?>