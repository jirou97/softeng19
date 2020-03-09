package gr.ntua.ece.softeng19b.data.model;

public class User {

    private String username;
    private String password;
    private String email;
    private String token;
    private int requestsPerDayQuota; //negative values indicate no quota
    public User() {
        //Keep this for json encoding/decoding
    }

    public User(String username,  String email, int requestsPerDayQuota) {
        this.username = username;
        this.password = null;
        this.email = email;
        this.requestsPerDayQuota = requestsPerDayQuota ;
    }
    /*
    public User(String username,  String email, Integer requestsPerDayQuota) {
        this.username = username;
        this.password = null;
        this.email = email;
        this.requestsPerDayQuota = requestsPerDayQuota ;
    }

    public User(String username,  String email, String requestsPerDayQuota) {
        this.username = username;
        this.password = null;
        this.email = email;
        this.requestsPerDayQuota = Integer.parseInt(requestsPerDayQuota) ;
    }
    public User(String username, String password, String email, String requestsPerDayQuota) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.requestsPerDayQuota = Integer.parseInt(requestsPerDayQuota) ;
    }
    
    public User(String username, int requestsPerDayQuota, String email, String token ) {
        this.username = username;
        this.token = token;
        this.email = email;
        this.requestsPerDayQuota = requestsPerDayQuota;
    }
    */
    public User(String username, String password, String email, int requestsPerDayQuota) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.requestsPerDayQuota = requestsPerDayQuota ;
    }


    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRequestsPerDayQuota(int requestsPerDayQuota) {
        this.requestsPerDayQuota = requestsPerDayQuota;
    }

    public int getRequestsPerDayQuota() {
        return requestsPerDayQuota;
    }

}
