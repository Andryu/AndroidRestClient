package shunsuke_andoh.pluginlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sample.mylibrary.ApiClient;
public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ApiClient api= new ApiClient();
        api.HttpClient();
        Log.d("MainActivity", "onCreate");
        while(true) {
            if(api.getResult() != null) {
                //((TextView) findViewById(R.id.text)).setText(api.getResult());
                Log.d("MainActivity", "Result: " + api.getResult());
                break;
            }
        }
        Log.d("MainActivity", "onCreate");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
