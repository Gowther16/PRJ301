
package model;
public class Accounts {
    private int id;
    private String user;
    private String email;
    private String pass;
    private int isSell;
    private int isAdmin;

    public Accounts() {
    }

    public Accounts(int id, String user,String email, String pass, int isSell, int isAdmin) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.pass = pass;
        this.isSell = isSell;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIsSell() {
        return isSell;
    }

    public void setIsSell(int isSell) {
        this.isSell = isSell;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", user=" + user+ ", email=" + email + ", pass=" + pass + ", isSell=" + isSell + ", isAdamin=" + isAdmin + '}';
    }

  
    
}
