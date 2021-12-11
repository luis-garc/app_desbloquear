package com.example.app_fecha;

import static java.text.DateFormat.getDateTimeInstance;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static int day;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txvClave = findViewById(R.id.txvClave);

        mDatabase.setValue(ServerValue.TIMESTAMP);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long timestamp = (Long) snapshot.getValue();
                //System.out.println(getTimeDate(timestamp));
                //System.out.println(day);
                getTimeDate(timestamp);
                txvClave.setText(desbloquear(day));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public String desbloquear(int day) {
        if(day == 29) {
            return clave();
        } else {
            return "***";
        }
    }

    public String clave() {
        int clave = (int) (10000 * Math.random());
        return clave+"";
    }

    public static void getTimeDate(Long timestamp){
        try{
            DateFormat dateFormat = getDateTimeInstance();
            DateFormat dayFormat = new SimpleDateFormat("dd");
            Date netDate = (new Date(timestamp));
            day = Integer.parseInt(dayFormat.format(netDate));
            dateFormat.format(netDate);
        } catch(Exception e) {
            System.out.println("***");
        }
    }
}
