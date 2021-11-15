package com.example.ejercicionotificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewOperation, textCorrect;
    private Button btn;
    private EditText response;
    private NotificationManager nfm;
    private int num = 0;
    private int correctResponse = 0;

    static final String CHANNEL_ID = "channel1";
    static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewOperation = (TextView) findViewById(R.id.textViewOperation);
        textCorrect = (TextView) findViewById(R.id.textCorrect);
        textCorrect.setText(String.valueOf(num));

        btn = (Button) findViewById(R.id.button);
        response = (EditText) findViewById(R.id.txtNumber);

        operation();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    nfm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel nfc = new NotificationChannel(CHANNEL_ID, "Notificaciones Operaciones", NotificationManager.IMPORTANCE_DEFAULT);
                        nfc.setDescription("La descripcion illo");
                        nfm.createNotificationChannel(nfc);
                    }
                    if (Integer.parseInt(response.getText().toString()) == correctResponse) {
                        num++;
                        if (num == 1) {
                            NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                    .setContentTitle(":O")
                                    .setContentText("has acertado 10 veces :0");
                            nfm.notify(NOTIFICATION_ID, notification.build());
                        }
                        System.out.println(num);
                        textCorrect.setText(String.valueOf(num));
                    }
                    operation();
                } catch(Exception e) {

                }
            }
        });
    }

    private void operation() {
        int num1 = 0;
        int num2 = 0;
        int op = (int) (1 + Math.random() * 4);
        switch(op) {
            case 1:
                num1 = (int)(1 + Math.random() * 100);
                num2 = (int)(1 + Math.random() * 100);
                correctResponse = num1 + num2;
                textViewOperation.setText(num1 + " + " + num2);
                System.out.println(num1 + " + " + num2 + " = "  + correctResponse);
                break;
            case 2:
                num1 = (int)(1 + Math.random() * 100);
                num2 = (int)(1 + Math.random() * 100);
                correctResponse = num1 - num2;
                textViewOperation.setText(num1 + " - " + num2);
                System.out.println(num1 + " - " + num2 + " = "  + correctResponse);
                break;
            case 3:
                num1 = (int)(1 + Math.random() * 100);
                num2 = (int)(1 + Math.random() * 100);
                correctResponse = num1 * num2;
                textViewOperation.setText(num1 + " * " + num2);
                System.out.println(num1 + " * " + num2 + " = "  + correctResponse);
                break;
            case 4:
                num1 = (int)(1 + Math.random() * 100);
                num2 = (int)(1 + Math.random() * 100);
                while(num2 > num1) {
                    num2 = (int)(1 + Math.random() * 100);
                }
                correctResponse = num1 / num2;
                textViewOperation.setText(num1 + " / " + num2);
                System.out.println(num1 + " / " + num2 + " = "  + correctResponse);
                break;
        }
    }
}