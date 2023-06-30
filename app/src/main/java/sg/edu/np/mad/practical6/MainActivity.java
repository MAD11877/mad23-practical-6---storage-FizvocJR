package sg.edu.np.mad.practical6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    final String TAG = "Main Activity";
    private boolean following = false;
    private Button followButton;
    private Button messageButton;

    TextView Username;
    TextView Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "On Create!");

        followButton = findViewById(R.id.followed);
        Username = findViewById(R.id.userName);
        messageButton = findViewById(R.id.message);
        Description = findViewById(R.id.Desc);

        Intent intent = getIntent();
        String UserName = intent.getStringExtra("username");
        String Desc = intent.getStringExtra("description");
        Username.setText(UserName);
        Description.setText(Desc);





        followButton.setOnClickListener(view -> {
            if(following == true){
                followButton.setText("Unfollow");
                Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_LONG).show();
            }
            else {
                followButton.setText("Follow");
                Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_LONG).show();
            }
            following = !following;
        });


    }
}