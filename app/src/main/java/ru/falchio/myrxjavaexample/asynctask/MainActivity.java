package ru.falchio.myrxjavaexample.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import ru.falchio.myrxjavaexample.R;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private MyAsyncTask myAsyncTask;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAsyncTask=new MyAsyncTask();
                myAsyncTask.execute();
                Log.d(TAG, "onClick:  завершен");
            }
        });
    }


    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        private String TAG = this.getClass().getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: начато выполнение AsyncTask");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: ");
            for (int i = 0; i <5 ; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Log.e(TAG, "doInBackground: " + Thread.currentThread().getName()+":" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG, "onPostExecute: выполнение AsyncTask завершено");
        }
    }
}
