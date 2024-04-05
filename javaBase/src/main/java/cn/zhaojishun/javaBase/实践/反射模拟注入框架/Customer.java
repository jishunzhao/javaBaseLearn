package cn.zhaojishun.javaBase.实践.反射模拟注入框架;

import java.util.StringJoiner;

public class Customer {

    private String name;

    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void printname(){
        System.out.println("customer name: " + name);
    }

    public void printemail(){
        System.out.println("customer email: " + email);
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .toString();
    }
}
