package me.makram.libgen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class MainActivity extends AppCompatActivity {

    static final String SEARCH_URL =
            "http://gen.lib.rus.ec/search.php?&req=%s&phrase=1&view=simple&column=def&sort=title&sortmode=ASC&page=%d";

    public final OkHttpClient client = new OkHttpClient();

    EditText searchEdit;
    Spinner sourceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEdit = (EditText) findViewById(R.id.searchEdit);
        sourceSpinner = (Spinner) findViewById(R.id.searchSourceSpinner);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchButtonClicked(View view) {
        String query = searchEdit.getText().toString();
        int pageNumber = 1;
        String finalUrl = String.format(SEARCH_URL, query, pageNumber);

        Request request = new Request.Builder()
                .url(finalUrl)
                .build();

        new GetPageTask(this).execute(request);
    }

}
