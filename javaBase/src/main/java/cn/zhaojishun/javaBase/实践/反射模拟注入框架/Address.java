package cn.zhaojishun.javaBase.实践.反射模拟注入框架;

public class Address {

    private String street;

    private String postCode;

    public Address(String street, String postCode) {
        this.street = street;
        this.postCode = postCode;
    }

    public void printStreet(){
        System.out.println("address street: " + street);
    }

    public void printPostCode(){
        System.out.println("address PostCode: " + postCode);
    }

    @Autowired
    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
