package com.java.flink.config.redis;

/**
 * Created by Live.InPast on 2019-08-27.
 */
public class RedisProperty {

    /**
     * redis uri
     */
    private String uri;

    /**
     * 数据库
     */
    private Integer db;

    /**
     * 数据库密码
     */
    private String auth;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
