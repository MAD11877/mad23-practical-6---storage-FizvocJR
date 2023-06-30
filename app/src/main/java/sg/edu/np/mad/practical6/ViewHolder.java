package sg.edu.np.mad.practical6;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView Name;
    TextView Description;
    ImageView profilepicture;

    public ViewHolder(View v) {
        super(v);
        profilepicture = v.findViewById(R.id.ProfileImage);
        Name = itemView.findViewById(R.id.ProfileName);
        Description = itemView.findViewById(R.id.Description);
    }
}
