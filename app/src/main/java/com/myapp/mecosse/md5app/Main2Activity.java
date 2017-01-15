package com.myapp.mecosse.md5app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main2Activity extends AppCompatActivity {

    static String answer = "";
    static int total = 0;
    static String str = "";
    static Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.textView4);


        Intent intent = getIntent();
        str = intent.getStringExtra("hash");
        textView.setText(str);



        final String pute = intent.getStringExtra("saMere");

        System.out.println(pute);

        button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bruteforce(pute.length());
                TextView nique = (TextView) findViewById(R.id.textView3);
                nique.setText(answer);
            }

        });
    }

    public static String getEncodedPassword(String key) {
        byte[] uniqueKey = key.getBytes();
        Object hash = null;

        byte[] var7;
        try {
            var7 = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException var6) {
            throw new Error("no MD5 support in this VM");
        }

        StringBuffer hashString = new StringBuffer();

        for(int i = 0; i < var7.length; ++i) {
            String hex = Integer.toHexString(var7[i]);
            if(hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }


    public static boolean testPassword(String clearTextTestPassword, String encodedActualPassword) {
        String encodedTestPassword = getEncodedPassword(clearTextTestPassword);
        return encodedTestPassword.equals(encodedActualPassword);
    }
    public static void bruteforce(int lenght) {
        char c;

        // D�finition du nombre de possibilites
        int fin=(int) (Math.pow(97, lenght)-1);
        for (int i=0 ; i<=fin ; i++){
            //Boucle de generation de motde passe a tester en fonction de la longueur du mot de passe saisi
            for (int j=0 ; j<=lenght-1 ; j++) {
                int nombre=((int) (i/(Math.pow(97,  j))))%97;
                c = (char) ((char) nombre +33);
                answer += c ;

            }
            // Test de comparaison du mot de passe � tester non encrypt� et du mot de passe saisi encrypt�
            // Si les mots de passe correspondent => Affiche succ�s avec le mot de passe trouv� puis le nombre d'essais avant de trouver puis sortie de la boucle
            if(testPassword(answer, str))
            {
                System.out.println("Succes, mot de passe : "+answer);
                total++;
                System.out.println("Nombre d'essais : "+total);
                break;
            }
            // Sinon => Affiche �chec avec le mot de passe test�, vide la variable answer et incr�mente le nombre d'essais
            else
            {
//                System.out.println("Echec, mot de passe test : "+answer);
                answer = "";
                total++;
            }
        }

    }
}