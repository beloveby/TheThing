package dachuangchuang.group.thething;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MyGallery extends Activity {
    private GetPhoto photoTask = null;
    private GridLayout gridLayout = null;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int columnNum =  4;

    private int startPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        init();

        this.photoTask.execute();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MyGallery Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://dachuangchuang.group.thething/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);

        init();
    }

    private void init(){
        if(this.gridLayout == null){
            this.gridLayout = (GridLayout) findViewById(R.id.gridlayout);
            this.gridLayout.setColumnCount(this.columnNum);
        }
        if(this.photoTask == null){
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

//            this.gridLayout.setMinimumHeight(displayMetrics.heightPixels);

            this.photoTask = new GetPhoto(this, this.gridLayout, this.columnNum, displayMetrics.heightPixels, displayMetrics.widthPixels, -1);
        }

        Button button = (Button) findViewById(R.id.load);
        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.Status status = photoTask.getStatus();
                if(status == AsyncTask.Status.FINISHED) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                    gridLayout.setMinimumHeight(displayMetrics.heightPixels);
                    int startPosition = photoTask.getStartPosition();
                    photoTask = new GetPhoto(context, gridLayout, columnNum, displayMetrics.heightPixels, displayMetrics.widthPixels, startPosition);

                    photoTask.execute();
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MyGallery Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://dachuangchuang.group.thething/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void setStartPosition(int startPosition){
        this.startPosition = startPosition;
    }
}