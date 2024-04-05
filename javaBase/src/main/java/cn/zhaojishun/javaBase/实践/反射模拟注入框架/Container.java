package cn.zhaojishun.javaBase.实践.反射模拟注入框架;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Container {

    //存放配置类中的方法 也就是已经定义的bean
    public Map<Class<?>, Method> methods;

    private Object config;

    //存放已经初始化的bean
    private Map<Class<?>, Object> beans;

    public void init() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.methods = new HashMap<>();
        this.beans = new HashMap<>();
        Class<?> clazz = Class.forName("cn.zhaojishun.javaBase.实践.反射模拟注入框架.Config");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getDeclaredAnnotation(Bean.class) != null) {
                Class<?> returnType = method.getReturnType();
                this.methods.put(returnType, method);
            }
        }
        this.config = clazz.getConstructor().newInstance();
    }

    public Object getBean(Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        if (this.beans.containsKey(clazz)){
            return this.beans.get(clazz);
        }
        if (this.methods.containsKey(clazz)){
            Method method = this.methods.get(clazz);
            Object invoke = method.invoke(this.config);
            //将创建好的bean放入容器 这样就不必每次都去创建bean
            this.beans.put(clazz, invoke);
            return invoke;
        }
        return null;
    }

    public Object createInstance(Class<?> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.getDeclaredAnnotation(Autowired.class) != null){
                //获取所有构造方法参数类型
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                //将构造方法参数类型放入数组中
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    //通过本地容器获取存在的bean
                    Object bean = this.getBean(parameterType);
                    parameters[i] = bean;
                }
                return constructor.newInstance(parameters);
            }
        }
        //如果没有Autowired注解就直接创建无参的bean
        return clazz.getConstructor().newInstance();
    }
}
