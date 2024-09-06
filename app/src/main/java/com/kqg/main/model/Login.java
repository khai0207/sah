package com.kqg.main.model;

/* loaded from: classes.dex */
public class Login extends CommonEntity {
    private User data;

    public User getData() {
        return this.data;
    }

    public void setData(User user) {
        this.data = user;
    }

    @Override // com.kqg.main.model.CommonEntity
    public String toString() {
        return "Login [data=" + this.data + ", toString()=" + super.toString() + "]";
    }
}
