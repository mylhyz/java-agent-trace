package io.mylhyz.viper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassLoggerTransformer implements ClassFileTransformer {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) throws IllegalClassFormatException {
        logger.info("visit {}", className);
        return classFileBuffer;
    }
}