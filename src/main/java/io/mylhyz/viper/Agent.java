package io.mylhyz.viper;

import java.lang.instrument.Instrumentation;

public class Agent {
    /**
     * 在java程序运行前注入
     *
     * @param args
     * @param instrumentation
     */
    public static void premain(String args, Instrumentation instrumentation) {
        // Instrumentation 用于修改字节码
        RuntimeTransformer transformer = new RuntimeTransformer();
        instrumentation.addTransformer(transformer);
    }

    /**
     * 本agent当做独立程序运行时
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("run agent standalone main");
    }

    /**
     * 在java程序运行后attach
     *
     * @param args
     * @param instrumentation
     */
    public static void agentmain(String args, Instrumentation instrumentation) {

    }

}