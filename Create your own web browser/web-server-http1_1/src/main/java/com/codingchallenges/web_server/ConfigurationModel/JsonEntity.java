package com.codingchallenges.web_server.ConfigurationModel;

public class JsonEntity {

    private int PORT;
    private String webRoot;

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

    @Override
    public String toString() {
        return "JsonEntity{" +
                "PORT=" + PORT +
                ", webRoot='" + webRoot + '\'' +
                '}';
    }

    public JsonEntity(int PORT, String webRoot) {
        this.PORT = PORT;
        this.webRoot = webRoot;
    }

    public JsonEntity() {
        super();
    }

}
