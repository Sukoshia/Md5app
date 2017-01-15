package com.myapp.mecosse.md5app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.R.attr.button;
import static android.R.id.button1;
import static android.R.id.edit;
import static android.R.id.inputExtractEditText;
import static com.myapp.mecosse.md5app.R.id.editText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(MainActivity.this);

//        Button button2 = (Button) findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("clicks","You Clicked B1");
//                Intent i=new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(i);
//            }
//        });
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



    @Override
    public void onClick(View view) {
//        Context context = getApplicationContext();
//        CharSequence text = "Ma bite";
//        int duration = Toast.LENGTH_SHORT;

        EditText editText = (EditText) findViewById(R.id.editText);
        String saMere = editText.getText().toString();

        Toast cul = Toast.makeText(this, "Vous avez hashé : "+saMere, Toast.LENGTH_LONG);
        cul.show();

        // Create MD5 Hash
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(saMere.getBytes());
        byte messageDigest[] = digest.digest();

        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        //return hexString.toString();
        TextView mdpC = (TextView) findViewById(R.id.textView2);

        mdpC.setText(Html.fromHtml("<h2><u>Résultat</u></h2><hr/><p>MD5 : </p>"+hexString.toString()));
        mdpC.setText(hexString);

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("hash", mdpC.getText().toString());
        intent.putExtra("saMere", saMere);
        System.out.println("bite au cul 1");
        startActivity(intent);



    }

}
