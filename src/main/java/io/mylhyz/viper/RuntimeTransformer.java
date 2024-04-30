package io.mylhyz.viper;

import javassist.ClassPool;
import javassist.CtClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class RuntimeTransformer implements ClassFileTransformer {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private static boolean filter(String cls) {
        if (cls == null || cls.isEmpty()) return true;
        if (cls.startsWith("sun/")) return true;
        if (cls.startsWith("org/")) return true;
        if (cls.startsWith("jdk/")) return true;
        if (cls.startsWith("javax/")) return true;
        if (cls.startsWith("java/lang/")) return true;
        if (cls.startsWith("java/util/")) return true;
        if (cls.startsWith("java/security/")) return true;
        if (cls.startsWith("java/awt/")) return true;
        if (cls.startsWith("com/apple/")) return true;
        if (cls.startsWith("java/net/")) return true;
        return false;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) throws IllegalClassFormatException {
        do {
            try {
                if (filter(className)) break;
                logger.info("visit {}", className);
                String clsName = className.replace("/", ".");
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(clsName);
                return cc.toBytecode();
            } catch (Exception e) {
                logger.error("transform", e);
            }
        } while (false);
        return classFileBuffer;
    }
}
