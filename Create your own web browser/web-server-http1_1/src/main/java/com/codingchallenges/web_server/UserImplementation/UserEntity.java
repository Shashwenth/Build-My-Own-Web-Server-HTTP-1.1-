package com.codingchallenges.web_server.UserImplementation;

public class UserEntity{

    private String UserName;

    private String password;

    private String email;

    private int age;

    public UserEntity(){
        super();
    }

    public UserEntity(String userName, String password, String email, int age) {
        super();
        UserName = userName;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserEntity{");
        sb.append("UserName=").append(UserName);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }


    



}   
