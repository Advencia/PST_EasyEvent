<?php
	$host="localhost";
	$uname="root";
	$pwd='';
	$db="gala";

	$con = mysql_connect($host,$uname,$pwd,$db) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	 
	if($r=mysql_query("UPDATE `statistiques` SET `simple`=(select count(`prevente`) from `etudiant` where `prevente`="simple"),`bouteille`=(select count(`prevente`) from `etudiant` where `prevente`="bouteille"),`vip`=(select count(`prevente`) from `etudiant` where `prevente`="vip")",$con))
	{
		$flag['code']=1;
	}
	 
	print(json_encode($flag));
	mysql_close($con);
?>