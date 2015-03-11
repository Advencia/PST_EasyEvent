<?php
	
	$host = "localhost"; // host of MySQL server
	$user = "root"; // MySQL user
	$pwd = ""; // MySQL user's password
	$db = "gala"; // database name
	
	$con = mysql_connect($host,$user,$pwd,$db) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	
	 
	// Récupération des variables nécessaires 
	$prenom=$_REQUEST['prenom'];
	$nom=$_REQUEST['nom'];
	$email=$_REQUEST['email'];
	$prevente=$_REQUEST['prevente'];
	$login = $_REQUEST['login'];
	$valid="non";
	$flag['code']=0;
	
	if($r=mysql_query("insert into etudiant (`nom`,`prenom`,`email`,`prevente`,`validation`)values('$prenom','$nom','$email','$prevente', '$valid') ",$con))
	{
	        $flag['code']=1;
	        echo"hi";
	}
	 
	
	// Génération aléatoire d'une clé
	$cle = md5(microtime(TRUE)*100000);
	 
	 
	// Insertion de la clé dans la base de données
	$stmt = $dbh->prepare("UPDATE etudiant SET cle=:cle WHERE login like :login");
	$stmt->bindParam(':cle', $cle);
	$stmt->bindParam(':login', $login);
	$stmt->execute();
	 
	 
	// Préparation du mail
	$destinataire = $email;
	$sujet = "Gala ESIEA" ;
	$entete = "From: easyevent@esiea.com" ;
	 
	$message = 'Bonjour,
	 
	Nous vous confirmons lachat de votre place du Gala ESIEA qui aura lieu le 3 avril 2015.
	
	---------------
	Ceci est un mail automatique, Merci de ne pas y répondre.';
	 
	 
	mail($destinataire, $sujet, $message, $entete) ; // Envoi du mail
	 
	print(json_encode($flag));
	
mysql_close($con);

?>