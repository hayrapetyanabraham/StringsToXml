package example.com.stringtoxml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextLikeXml;
    private String textAll = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextLikeXml = findViewById(R.id.edit_text_like_xml);
        logGreek();
    }

    private void logGreek() {
        List<String> greekKeys = stringsTexts(R.raw.greek_keys);
        List<String> greek = stringsTexts(R.raw.greek);
        List<String> stringArrayList = new ArrayList<>();
        Spanned spanned = Html.fromHtml("&#34;");
        for (int i = 0; i < greekKeys.size(); i++) {
            String value = "<string name=" + spanned + greekKeys.get(i) + spanned + ">" + greek.get(i) + "</string>" + "\n";
            stringArrayList.add(value);
        }
        for (int i = 0; i < stringArrayList.size(); i++) {
            textAll = textAll + stringArrayList.get(i);
        }
        editTextLikeXml.setText(textAll);
        Log.d("TEXT_XML", textAll);
    }

    private List<String> stringsTexts(int resourceId) {
        List<String> keys = new ArrayList<>();
        BufferedReader reader;
        try {
            final InputStream file = getResources().openRawResource(resourceId);
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                keys.add(line);
                line = reader.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return keys;
    }

}
