package es.age.apps.notificationexample;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public static String EXTRA_RESULT = "es.ageapps.notificationexample.EXTRA_RESULT";
    public static String EXTRA_NOTIFICATION_ID = "es.ageapps.notificationexample.EXTRA_NOTIFICATION_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String result = getIntent().getStringExtra(EXTRA_RESULT);
        int id = getIntent().getIntExtra(EXTRA_NOTIFICATION_ID, -1);

        if (id > 0) {
            // Cancel Action Button notifications
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(id);
        }

        TextView resultText = (TextView) findViewById(R.id.result_text);
        resultText.setText(result);
    }

}
