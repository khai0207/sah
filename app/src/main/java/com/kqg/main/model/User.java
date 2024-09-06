package com.kqg.main.model;

import com.android.pc.ioc.db.annotation.Id;
import com.kqg.main.utils.EncodeUtils;
import java.io.Serializable;

/* loaded from: classes.dex */
public class User implements Serializable {
    private static final long serialVersionUID = 7515205787216093098L;
    private String age;
    private int guest;
    private String password;
    private String session_id;

    @Id
    private String uid;
    private String username;
    private int what;

    public User() {
    }

    public int getWhat() {
        return this.what;
    }

    public void setWhat(int i) {
        this.what = i;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String str) {
        this.age = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public User(String str, String str2, String str3, int i, boolean z) {
        setUsername(str);
        setPassword(z ? str2 : EncodeUtils.encrypt("8dQnbuOO6Z3t2eRm", str2));
        setWhat(i);
        setAge(str3);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getSession_id() {
        return this.session_id;
    }

    public void setSession_id(String str) {
        this.session_id = str;
    }

    public int getGuest() {
        return this.guest;
    }

    public void setGuest(int i) {
        this.guest = i;
    }

    public String toString() {
        return "User{username='" + this.username + "', password='" + this.password + "', uid='" + this.uid + "', session_id='" + this.session_id + "', what=" + this.what + ", guest=" + this.guest + ", age='" + this.age + "'}";
    }
}
