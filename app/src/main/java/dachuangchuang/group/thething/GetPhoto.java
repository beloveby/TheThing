package dachuangchuang.group.thething;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by 边园 on 2016/1/22.
 * The first integer is the params' type of doInBackground
 * The second integer is the params' type of update method
 * String is the return type of doInBackground and also the params'type of onPostExecute
 */
public class GetPhoto extends AsyncTask<Integer, String, String> {
    private Context context;
    private GridLayout gridLayout;
    private int startPosition = -1;
    private final int maxLoadNum = 20;

    private int diviceHeight;
    private int diviceWidth;

    private int columnNum = 4;

    public GetPhoto(Context context, GridLayout gridLayout, int columnNum, int diviceHeight, int diviceWidth, int startPosition){
        this.context = context;
        this.gridLayout = gridLayout;
        this.columnNum = columnNum;
        this.diviceHeight = diviceHeight;
        this.diviceWidth = diviceWidth;
        this.startPosition = startPosition;
    }

    @Override
    protected void onPreExecute(){
        //this method execute in main thread
        Toast.makeText(this.context, "This is preExecute", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(Integer... params) {
        ContentResolver mResolver = this.context.getContentResolver();

        String[] projection = new String[] { MediaStore.Images.Media.DATA };
        Cursor cursor = mResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
                null, MediaStore.Images.Media.DEFAULT_SORT_ORDER);
        cursor.moveToFirst();
        int fileNum = cursor.getCount();

        if(this.startPosition == -1){
            this.startPosition = fileNum - 1;
        }

        cursor.moveToPosition(this.startPosition);
        for (int counter = 0; this.startPosition - counter > 0 && counter < this.maxLoadNum; counter++) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            this.publishProgress(path);
            cursor.moveToPrevious();
            this.startPosition--;
        }
        cursor.close();


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(this.context, "task finished", Toast.LENGTH_SHORT);


    }

    /**
     *
     * @param params get params from method execute
     */
    @Override
    protected void onProgressUpdate(String... params){
        File file = new File(params[0]);
        if(file.exists()){
            Bitmap bitmap = this.getBitmapByPath(params[0]);

            ImageView imageView = new ImageView(this.context);
            imageView.setImageBitmap(bitmap);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            this.gridLayout.addView(imageView);
        }

        Toast.makeText(this.context, "update", Toast.LENGTH_SHORT);
    }

    private Bitmap getBitmapByPath(String path){
        int pictureWidth = this.diviceWidth / this.columnNum;
        int pictureHeight = pictureWidth;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(path, options);
        int height = options.outHeight;
        int width = options.outWidth;

        options.inJustDecodeBounds = false;
        options.inSampleSize = max(height / pictureHeight, width / pictureWidth);

        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, pictureWidth, pictureHeight, false);
        bitmap.recycle();

        return bitmap1;
    }

    private int max(int t1, int t2){
        return t1 > t2 ? t1 : t2;
    }

    public int getStartPosition(){
        return this.startPosition;
    }
}
