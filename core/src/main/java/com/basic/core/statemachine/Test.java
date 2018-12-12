package com.basic.core.statemachine;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/11
 */
public class Test {
    public static void main(String[] args) {
        test(1,2,4);
    }

    public static void test(Object... params){
        if(params.length>0){
            System.out.println(params[0]);
        }else{
            System.out.println("没有参数");
        }
        if(params.length>1){
            System.out.println(params[1]);
        }
        if(params.length>2){
            System.out.println(params[2]);
        }
        if(params.length>3){
            System.out.println(params[3]);
        }
        if(params.length>4){
            System.out.println(params[4]);
        }

    }
}
