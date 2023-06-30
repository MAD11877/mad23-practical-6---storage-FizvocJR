package sg.edu.np.mad.practical6;


import java.io.Serializable;

public class User implements Serializable {
    String Username;
    String Description;
    int Id;
    boolean Follow;

    public User(String username, String description,int id,boolean follow){
        Username = username;
        Description = description;
        Id = id;
        Follow = follow;
    }

    public User(){

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isFollowed() {return Follow;}

    public void setFollowed(boolean follow) {Follow = follow;}

}
