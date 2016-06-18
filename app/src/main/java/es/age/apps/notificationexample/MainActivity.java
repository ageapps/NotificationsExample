package es.age.apps.notificationexample;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "notificationexample";

    private NotificationManager mNotificationManager;
    private TaskStackBuilder stackBuilder;

    private final int NOTIFICATION_ID = 88;
    private final int DEFAULT_NOTIFICATION = 0;
    private final int EXPANDED_NOTIFICATION = 1;
    private final int ACTION_NOTIFICATION = 2;
    private final int BIG_TEXT_NOTIFICATION = 3;
    private final int SPECIAL_NOTIFICATION = 4;
    private final int PROGRESS_NOTIFICATION = 5;
    private final int MEDIA_NOTIFICATION = 6;
    private final int CUSTOM_NOTIFICATION = 7;
    private final int EXPANDED_ACTION_NOTIFICATION = 8;
    private final int BIG_PICTURE_NOTIFICATION = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        stackBuilder = TaskStackBuilder.create(MainActivity.this);

        Button default_notification = (Button) findViewById(R.id.default_notification);
        Button expanded_notification = (Button) findViewById(R.id.expanded_notification);
        Button action_buttons_notification = (Button) findViewById(R.id.action_buttons_notification);
        Button big_text_notification = (Button) findViewById(R.id.big_text_notification);
        Button special_notification = (Button) findViewById(R.id.special_notification);
        Button progress_notification = (Button) findViewById(R.id.progress_notification);
        Button media_notification = (Button) findViewById(R.id.media_notification);
        Button custom_notification = (Button) findViewById(R.id.custom_notification);
        Button expanded_action_notification = (Button) findViewById(R.id.expanded_action_notification);
        Button image_notification = (Button) findViewById(R.id.image_notification);
        Button priority_notification = (Button) findViewById(R.id.priority_notification);


        assert default_notification != null;
        default_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(DEFAULT_NOTIFICATION, "Default Notification");
            }
        });


        assert expanded_notification != null;
        expanded_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(EXPANDED_NOTIFICATION, "Expanded Notification");
            }
        });


        assert action_buttons_notification != null;
        action_buttons_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(ACTION_NOTIFICATION, "Action Buttons Notification");
            }

        });

        assert big_text_notification != null;
        big_text_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(BIG_TEXT_NOTIFICATION, "Big Text Notification");

            }

        });
        assert special_notification != null;
        special_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSpecialNotification();
            }

        });
        assert progress_notification != null;
        progress_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendProgressNotification();
            }

        });
        assert media_notification != null;
        media_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMediaNotification();
            }

        });

        assert custom_notification != null;
        custom_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomNotification();
            }

        });
        assert expanded_action_notification != null;
        expanded_action_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(EXPANDED_ACTION_NOTIFICATION, "Expanded Action Buttons Notification");
            }

        });
        assert image_notification != null;
        image_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(BIG_PICTURE_NOTIFICATION, "Big Image Notification");
            }

        });
        assert priority_notification != null;
        priority_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHighPriorityNotification();
            }

        });
    }

    public void sendCustomNotification() {

        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.notification_custom);

        remoteViews.setTextColor(R.id.title, getResources().getColor(android.R.color.white));
        remoteViews.setTextColor(R.id.text, getResources().getColor(android.R.color.white));
        // Set Notification Title
        String strtitle = "Custom Notification Title";
        // Set Notification Text
        String strtext = "Custom Notification Description";

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(this, SpecialActivity.class);
        // Send data to NotificationView Class
        intent.putExtra(ResultActivity.EXTRA_RESULT, strtitle);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                // Set Icon
                .setSmallIcon(R.mipmap.ic_launcher)
                // Set Ticker Message
                .setTicker("Custom Notification")
                // Dismiss Notification
                .setAutoCancel(true)
                // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                .setColor(getResources().getColor(android.R.color.background_dark))
                // set default vibration and led color
                .setDefaults(Notification.DEFAULT_ALL)
                // Set RemoteViews into Notification
                .setContent(remoteViews);

        // Locate and set the Image into customnotificationtext.xml ImageViews
        remoteViews.setImageViewResource(R.id.imagenotileft, R.mipmap.ic_launcher);
        remoteViews.setImageViewResource(R.id.imagenotiright, R.drawable.ic_error_outline_white_24dp);

        // Locate and set the Text into customnotificationtext.xml TextViews
        remoteViews.setTextViewText(R.id.title, strtitle);
        remoteViews.setTextViewText(R.id.text, strtext);

        // Build Notification with Notification Manager
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

    }


    public void sendSpecialNotification() {
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_error_outline_white_24dp)
                        .setContentTitle("Special Text Notification")
                        .setContentText("Hello World!");

        // Creates an Intent for the Activity
        Intent notifyIntent =
                new Intent(this, SpecialActivity.class);
        // Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // Puts the PendingIntent into the notification builder
        mBuilder.setContentIntent(notifyPendingIntent);
        // Notifications are issued by sending them to the
        // NotificationManager system service.
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds an anonymous Notification object from the builder, and
        // passes it to the NotificationManager
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(SPECIAL_NOTIFICATION, notification);
    }


    public void sendProgressNotification() {
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_error_outline_white_24dp)
                        .setContentTitle("Download Progress Notification")
                        .setContentText("Hello World!");

        Intent notifyIntent =
                new Intent(this, SpecialActivity.class);
        // Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Creates the PendingIntent
        final PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setOngoing(true); // non removable notification

        // Start a lengthy operation in a background thread
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr += 5) {
                            // Sets the progress indicator to a max value, the
                            // current completion percentage, and "determinate"
                            // state
                            mBuilder.setProgress(100, incr, false);
                            // Displays the progress bar for the first time.
                            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
                            // Sleeps the thread, simulating an operation
                            // that takes time
                            try {
                                // Sleep for 1/2 second
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                Log.d(TAG, "sleep failure");
                            }
                        }
                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("Download complete")
                                .setContentIntent(notifyPendingIntent)
                                // Removes the progress bar
                                .setProgress(0, 0, false)
                                .setOngoing(false);
                        Notification notification = mBuilder.build();
                        notification.flags |= Notification.FLAG_AUTO_CANCEL;
                        mNotificationManager.notify(NOTIFICATION_ID, notification);
                    }
                }
                // Starts the thread by calling the run() method in its Runnable
        ).start();
    }

    public void sendHighPriorityNotification() {

        Toast.makeText(MainActivity.this, "Wait for Notification", Toast.LENGTH_LONG).show();
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_error_outline_white_24dp)
                        .setContentTitle("High Priority Notification");

        Intent notifyIntent =
                new Intent(this, SpecialActivity.class);
        // Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Creates the PendingIntent
        final PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setOngoing(true); // non removable notification

        // Start a lengthy operation in a background thread
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        try {
                            // Sleep for 5 seconds
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException e) {
                            Log.d(TAG, "sleep failure");
                        }

                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("High Priority Notification Description")
                                .setContentIntent(notifyPendingIntent)
                                // Removes the progress bar
                                .setProgress(0, 0, false)
                                .setOngoing(false)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setPriority(Notification.PRIORITY_HIGH);
                        Notification notification = mBuilder.build();
                        notification.flags |= Notification.FLAG_AUTO_CANCEL;
                        mNotificationManager.notify(NOTIFICATION_ID, notification);
                    }
                }
                // Starts the thread by calling the run() method in its Runnable
        ).start();
        finish();
    }


    public void sendMediaNotification() {

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);

        resultIntent.putExtra(ResultActivity.EXTRA_RESULT, "Media Notification");
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ResultActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);

        // With FLAG_ONE_SHOT we don´t overwrite the Extras
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Notification notification = new Notification.Builder(MainActivity.this)
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_error_outline_white_24dp)
                // Add media control buttons that invoke intents in your media service
                .addAction(android.R.drawable.ic_media_previous, "Previous", resultPendingIntent) // #0
                .addAction(android.R.drawable.ic_media_pause, "Pause", resultPendingIntent)  // #1
                .addAction(android.R.drawable.ic_media_next, "Next", resultPendingIntent)     // #2

                // Apply the media style template
                .setStyle(new Notification.MediaStyle()
                        .setShowActionsInCompactView(1 /* #1: pause button */)
                        .setMediaSession(null))
                .setContentTitle("Media Notification Title")
                .setContentText("Media Notification Description")
                .setLargeIcon(bm)
                .setOngoing(true) // non removable notification
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        // notificationType allows you to update the notification later on.
        mNotificationManager.notify(NOTIFICATION_ID, notification);


    }

    public void sendNotification(int notificationType, String intentText) {


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_error_outline_white_24dp)
                        .setContentTitle(intentText)
                        .setColor(getResources().getColor(R.color.colorPrimary))
                        .setContentText("Hello World!");


        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);

        resultIntent.putExtra(ResultActivity.EXTRA_RESULT, intentText);

        stackBuilder = TaskStackBuilder.create(MainActivity.this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ResultActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);

        // With FLAG_ONE_SHOT we don´t overwrite the Extras
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);

        if (notificationType == MEDIA_NOTIFICATION) {

            return;
        }


        switch (notificationType) {
            case DEFAULT_NOTIFICATION: {
                // Don nothing
                break;

            }
            case EXPANDED_NOTIFICATION: {
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

                // Sets a title for the Inbox in expanded layout
                inboxStyle.setBigContentTitle("Expanded Notification:");
                // Moves events into the expanded layout
                for (int i = 0; i < 6; i++) {

                    inboxStyle.addLine("Hello World! Expanded");
                }
                // Moves the expanded layout object into the notification object.
                mBuilder.setStyle(inboxStyle);
                break;
            }
            case ACTION_NOTIFICATION: {
                Intent resultAddIntent = new Intent(this, ResultActivity.class);
                resultAddIntent.putExtra(ResultActivity.EXTRA_RESULT, "Action Button Add");
                resultAddIntent.putExtra(ResultActivity.EXTRA_NOTIFICATION_ID, NOTIFICATION_ID);
                Intent resultDeleteIntent = new Intent(this, ResultActivity.class);
                resultDeleteIntent.putExtra(ResultActivity.EXTRA_RESULT, "Action Button Delete");
                resultDeleteIntent.putExtra(ResultActivity.EXTRA_NOTIFICATION_ID, NOTIFICATION_ID);

                stackBuilder.addParentStack(ResultActivity.class);
                stackBuilder.addNextIntent(resultAddIntent);
                PendingIntent piAdd = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
                stackBuilder.addParentStack(ResultActivity.class);
                stackBuilder.addNextIntent(resultDeleteIntent);
                PendingIntent piDelete = stackBuilder.getPendingIntent(2, PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.addAction(android.R.drawable.ic_menu_add, "Add", piAdd)
                        .addAction(android.R.drawable.ic_delete, "Delete", piDelete)
                        .addAction(R.drawable.ic_error_outline_white_24dp, "More", resultPendingIntent);
                break;
            }
            case BIG_TEXT_NOTIFICATION: {
                String long_text = "Hello World! Big\nHello World! Big\nHello World! Big\nHello World! Big\nHello World! Big\nHello World! Big.....";
                mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(long_text));
                break;

            }
            case EXPANDED_ACTION_NOTIFICATION: {
                String msg = ("Sets the big view \"big text\" style and supplies the text (the user's reminder message)" +
                        " that will be displayed  in the detail area of the expanded notification.\n" +
                        "These calls are ignored by the support library for pre-4.1 devices.");
                Intent resultAddIntent = new Intent(this, ResultActivity.class);
                resultAddIntent.putExtra(ResultActivity.EXTRA_RESULT, "Action Button Atention");

                Intent resultDeleteIntent = new Intent(this, ResultActivity.class);
                resultDeleteIntent.putExtra(ResultActivity.EXTRA_RESULT, "Action Button Alert");

                stackBuilder.addParentStack(ResultActivity.class);
                stackBuilder.addNextIntent(resultAddIntent);
                PendingIntent piAtention = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
                stackBuilder.addParentStack(ResultActivity.class);
                stackBuilder.addNextIntent(resultDeleteIntent);
                PendingIntent piAlert = stackBuilder.getPendingIntent(2, PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setDefaults(Notification.DEFAULT_ALL) // requires VIBRATE permission
        /*
         * Sets the big view "big text" style and supplies the
         * text (the user's reminder message) that will be displayed
         * in the detail area of the expanded notification.
         * These calls are ignored by the support library for
         * pre-4.1 devices.
         */
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .addAction(R.drawable.ic_error_outline_white_24dp,
                                "Atention", piAtention)
                        .addAction(android.R.drawable.ic_dialog_alert,
                                "Alert", piAlert);

                break;
            }
            case BIG_PICTURE_NOTIFICATION: {
                Intent resultAddIntent = new Intent(this, ResultActivity.class);
                resultAddIntent.putExtra(ResultActivity.EXTRA_RESULT, "Action Button Add");
                resultAddIntent.putExtra(ResultActivity.EXTRA_NOTIFICATION_ID, NOTIFICATION_ID);

                Intent resultDeleteIntent = new Intent(this, ResultActivity.class);
                resultDeleteIntent.putExtra(ResultActivity.EXTRA_RESULT, "Action Button Delete");
                resultDeleteIntent.putExtra(ResultActivity.EXTRA_NOTIFICATION_ID, NOTIFICATION_ID);

                stackBuilder.addParentStack(ResultActivity.class);
                stackBuilder.addNextIntent(resultAddIntent);
                PendingIntent piAdd = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
                stackBuilder.addParentStack(ResultActivity.class);
                stackBuilder.addNextIntent(resultDeleteIntent);
                PendingIntent piDelete = stackBuilder.getPendingIntent(2, PendingIntent.FLAG_UPDATE_CURRENT);

                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                mBuilder.setLargeIcon(bm)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(bm))
                        .addAction(android.R.drawable.ic_input_add,
                                "Add", piAdd)
                        .addAction(android.R.drawable.ic_delete,
                                "Delete", piDelete);

                break;
            }
        }

        mBuilder.setAutoCancel(true)
                .setContentIntent(resultPendingIntent);
        Notification notification = mBuilder.build();
        // notificationType allows you to update the notification later on.
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }


    @Override
    protected void onStop() {
        super.onStop();
        // cancel media notification if it is showing
        //mNotificationManager.cancel(MEDIA_NOTIFICATION);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.author, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_author) {
            String url = "http://ageapps.cf";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
