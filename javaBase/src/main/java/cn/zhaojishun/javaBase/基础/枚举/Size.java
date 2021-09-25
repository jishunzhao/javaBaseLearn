package cn.zhaojishun.javaBase.基础.枚举;

public enum Size {

    SAMIL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL");

    //枚举的构造方法 在构造枚举类时被调用
    Size(String abbreviation) { this.abbreviation = abbreviation; }

    //枚举的成员变量
    private String abbreviation;

    //成员方法
    public String getAbbreviation(){return abbreviation;}

}
