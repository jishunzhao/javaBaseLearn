package cn.zhaojishun.javaBase.实践.反射模拟注入框架;

public class Config {

    @Bean
    public Customer customer(){
        return new Customer("zhangsan", "zhangsan@gamail.com");
    }
    @Bean
    public Address address(){
        return new Address("山东省", "37001");
    }
}
