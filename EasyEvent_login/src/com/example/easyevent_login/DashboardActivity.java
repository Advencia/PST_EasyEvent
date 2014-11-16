package com.example.easyevent_login;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends Activity {
	
	Button btnLogout;

	public static final int NOTIFICATION_ID = 42;
	private String notificationTitle;

	@Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    /********************************/
	    /* Définit le nom de l'Activity */
	    /********************************/
	    setTitle("Home Easy Event");

	    /****************************************************/
	    /* Vérifier le statut de connexion de l'utilisateur */
	    /****************************************************/

	    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	    int count = db.getRowCount();
	    if(count != 0)
	    {
	      /***********************/
	      /* Afficher l'activité */
	      /***********************/
	      setContentView(R.layout.dashboard);

	      /****************************************/
	      /* Affiche la notification de connexion */
	      /****************************************/

	      notificationTitle = this.getResources().getString(R.string.notification);
	      createNotification();

	      /*************************************/
	      /* Clic sur le bouton de déconnexion */
	      /*************************************/

	      btnLogout = (Button) findViewById(R.id.btnLogout);

	      btnLogout.setOnClickListener(new View.OnClickListener() {

	        public void onClick(View arg0) {

	          /************************************/
	          /* Réinitialisation de la connexion */
	          /************************************/  

	          DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	          db.resetTables();

	          /***********************************/
	          /* Lancement de l'Activity "Login" */
	          /***********************************/

	          Intent login = new Intent(getApplicationContext(), LoginActivity.class);
	          login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	          startActivity(login);

	          /****************************/
	          /* Ferme l'Activity "Login" */
	          /****************************/

	          finish();
	        }
	      });

	    }
	    else {      

	      /*****************************************/
	      /* Supprime la notification de connexion */
	      /*****************************************/

	      deleteNotification();

	      /*********************************/
	      /* L'utilisateur n'est pas loggé */
	      /*********************************/

	      Intent login = new Intent(getApplicationContext(), LoginActivity.class);
	      login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	      startActivity(login);

	      /********************************/
	      /* Ferme l'Activity "Dashboard" */
	      /********************************/

	      finish();
	    }

	  }

	  @SuppressWarnings("deprecation")
	private final void createNotification()
	  {
	    /****************************************/
	    /* Récupération du notification Manager */
	    /****************************************/

	    final NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

	    /*****************************************************************************************/
	    /* Création de la notification avec spécification de l'icone de notification et le texte */
	    /*****************************************************************************************/

	    @SuppressWarnings("deprecation")
		final Notification notification = new Notification(R.drawable.ic_launcher, notificationTitle, System.currentTimeMillis());  

	    /******************************************************************************************************************/
	    /* Définit la redirection au moment du clic sur la notification. Ici, la notification redirige vers l'application */
	    /******************************************************************************************************************/

	    final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, DashboardActivity.class), 0);

	    /**********************************************************/
	    /* Récupération du titre et description de la notfication */
	    /**********************************************************/

	    final String notificationTitle = getResources().getString(R.string.notification_title);
	    final String notificationDesc = getResources().getString(R.string.notification_desc);     

	    /****************************/
	    /* Notification &amp; Vibration */
	    /****************************/

	    notification.setLatestEventInfo(this, notificationTitle, notificationDesc, pendingIntent);
	    notification.vibrate = new long[] {0,200,100,200,100,200};

	    notificationManager.notify(NOTIFICATION_ID, notification);
	  }

	  private void deleteNotification()
	  {
	    /****************************************/
	    /* Récupération du notification Manager */
	    /****************************************/

	    final NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

	    /*************************************************/     
	    /* Suppression de la notification grâce a son ID */
	    /*************************************************/

	    notificationManager.cancel(NOTIFICATION_ID);
	  }
}
