package com.kqg.main.model;

import com.android.pc.ioc.db.sqlite.DbUtils;
import com.android.pc.ioc.db.sqlite.Selector;
import com.android.pc.ioc.db.sqlite.WhereBuilder;
import com.iflytek.cloud.SpeechEvent;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.constant.KV;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UserManager {
    private static UserManager manager;
    private User curUser;
    private DbUtils dbUtils;
    private boolean isLogin = false;
    private List<User> users;

    private UserManager() {
        DbUtils.DaoConfig daoConfig = new DbUtils.DaoConfig(KaiQiGuSdk.getInstance().getCtx());
        daoConfig.setDbName(KV.DB_NAME);
        DbUtils create = DbUtils.create(daoConfig);
        this.dbUtils = create;
        create.configDebug(KaiQiGuSdk.getInstance().isDebug());
    }

    public static UserManager getInstance() {
        if (manager == null) {
            UserManager userManager = new UserManager();
            manager = userManager;
            userManager.initUsers();
        }
        return manager;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void saveUser(User user) {
        if (getUserById(user.getUid()) != null) {
            updateUser(user);
        } else {
            this.dbUtils.save(user);
        }
    }

    public void initUsers() {
        List<User> findAll = this.dbUtils.findAll(Selector.from(User.class).orderBy("guest", true));
        this.users = findAll;
        if (findAll == null) {
            this.users = new ArrayList();
        }
    }

    public List<User> getUsers() {
        initUsers();
        return this.users;
    }

    public User getFirstUser() {
        return (User) this.dbUtils.findFirst(Selector.from(User.class));
    }

    public boolean hasNativeUserData() {
        return getFirstUser() != null;
    }

    public User getUserByJsonData(JSONObject jSONObject, int i) {
        System.out.println(jSONObject.toString());
        try {
            String string = jSONObject.getString("username");
            String string2 = jSONObject.has("password") ? jSONObject.getString("password") : "";
            String string3 = jSONObject.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            String string4 = jSONObject.getString("uid");
            User user = new User(string, string2, jSONObject.has("age") ? jSONObject.getString("age") : "", i, true);
            user.setSession_id(string3);
            user.setUid(string4);
            if (jSONObject.has("need_real_name")) {
                user.setGuest(jSONObject.getInt("need_real_name"));
            }
            return user;
        } catch (Exception unused) {
            return null;
        }
    }

    public void setFirstUser(User user) {
        removeUser(user);
        saveUser(user);
    }

    public void saveGuest(User user) {
        if (((User) this.dbUtils.findFirst(Selector.from(User.class).where("guest", "=", 1))) == null) {
            saveUser(user);
        }
    }

    public void removeUser(User user) {
        this.dbUtils.delete(user);
    }

    public User getUserById(String str) {
        return (User) this.dbUtils.findById(User.class, str);
    }

    public void deleteGuestUser() {
        this.dbUtils.delete(User.class, WhereBuilder.b("guest", "=", 1));
    }

    public void updateUser(User user) {
        this.dbUtils.update(user);
    }

    public void setCurrentUser(User user) {
        this.curUser = user;
    }

    public User getCurrentUser() {
        return this.curUser;
    }
}
