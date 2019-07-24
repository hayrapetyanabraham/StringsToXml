package example.com.stringtoxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TEXT_KEY_LOG";
    String textAll = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> keys = stringsTexts(R.raw.keys);
        List<String> russian = stringsTexts(R.raw.russian);
        List<String> rusianKeyValue = new ArrayList<>();


        Spanned sp = Html.fromHtml( "&#34;");

        for (int i = 0; i < keys.size(); i++) {
           // String value = "<string name=" + "\\u0022" + keys.get(i) + "\\u0022" + ">" + russian.get(i) + "</string>";
            String value = "<string name=" + sp + keys.get(i) + sp + ">" + russian.get(i) + "</string>";
            Log.d("KEY_STRING", value);
            rusianKeyValue.add(value);
        }


        for (int i = 0; i < rusianKeyValue.size(); i++) {
            textAll = textAll + rusianKeyValue.get(i);

        }
        Log.d("TEXT_XML", textAll);

        Log.d(TAG, " SIZE " + rusianKeyValue.size());
        Log.d(TAG, " Values " + rusianKeyValue.toString());
    }


    private List<String> stringsTexts(int resourceId) {
        List<String> keys = new ArrayList<>();
        BufferedReader reader;
        try {
            final InputStream file = getResources().openRawResource(resourceId);
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                Log.d("StackOverflow", line);
                keys.add(line);
                line = reader.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return keys;
    }


    public static String UnicodeToString(String Hex) {
        String enUnicode = null;
        String deUnicode = null;

        for (int i = 0; i < Hex.length(); i++) {
            if (enUnicode == null)
                enUnicode = String.valueOf(Hex.charAt(i));
            else
                enUnicode = enUnicode + Hex.charAt(i);

            if (i % 4 == 3) {
                if (enUnicode != null) {
                    if (deUnicode == null)
                        deUnicode = String.valueOf((char) Integer.valueOf(
                                enUnicode, 16).intValue());
                    else
                        deUnicode = deUnicode
                                + String.valueOf((char) Integer.valueOf(
                                enUnicode, 16).intValue());
                }

                enUnicode = null;
            }
        }

        return deUnicode;
    }


}
