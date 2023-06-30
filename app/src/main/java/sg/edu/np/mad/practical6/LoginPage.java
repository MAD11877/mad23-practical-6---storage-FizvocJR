package sg.edu.np.mad.practical6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText enteredusername = findViewById(R.id.loginuserenteredname);
        EditText enteredpassword = findViewById(R.id.loginuserenteredpassword);
        Button submit = findViewById(R.id.login);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://practical6-fb4ce-default-rtdb.firebaseio.com/");
                DatabaseReference myRef = database.getReference("User");

                String username = enteredusername.getText().toString();
                String password = enteredpassword.getText().toString();




                myRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Log.v("zzz","+" + dataSnapshot.child("password").getValue().toString());

                            String savedPassword = dataSnapshot.child("password").getValue().toString();




                            if (savedPassword.equals(password) && savedPassword !=null) {
                                Log.v("LoginPage", "Login successful");

                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginPage.this, ListActivity.class);
                                Log.v("Login Username","+"+username);

                                startActivity(intent);
                            } else {
                                Log.v("LoginPage", "Invalid username or password");
                                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.v("LoginPage", "Error: " + databaseError.getMessage());
                    }
                });
            }
        });
    }
}
