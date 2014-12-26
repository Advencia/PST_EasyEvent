<?php
	
$host = "localhost"; // host of MySQL server
$user = "root"; // MySQL user
$pwd = ""; // MySQL user's password
$db = "gala"; // database name

$con = mysql_connect($host,$user,$pwd,$db) or die("connection failed");
mysql_select_db($db,$con) or die("db selection failed");
	 
$email=$_REQUEST['email'];
	 
$flag['code']=0;
	 
if($r=mysql_query("DELETE FROM etudiant WHERE email= '$email'",$con))

{
	$flag['code']=1;

}

print(json_encode($flag));
mysql_close($con);

?>