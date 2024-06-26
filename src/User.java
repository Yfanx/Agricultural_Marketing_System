import java.sql.Timestamp;

public class User {
    private int uid;             //用户id
    private String uname;        //用户名
    private String ps;           //密码
    private String role;         //角色
    private Timestamp createdAt; //创建时间


    public User(){}

    public User(int uid, String uname, String ps, String role, Timestamp createdAt) {
        this.uid = uid;
        this.uname = uname;
        this.ps = ps;
        this.role = role;
        this.createdAt = createdAt;
    }

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPs() {
        return ps;
    }
    public void setPs(String ps) {
        this.ps = ps;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", ps='" + ps + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

}
