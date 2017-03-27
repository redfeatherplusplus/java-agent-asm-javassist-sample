package agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {


    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

//                if (s.startsWith("org/apache/commons/dbutils")) {
                if ("other/Stuff".equals(s)) {

            		ClassReader cr = new ClassReader(bytes);
            		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            		ClassTransformVisitor ca = new ClassTransformVisitor(cw);
            		cr.accept(ca, 0);
                    return cw.toByteArray();
                }

                return bytes;
            }
        });
    }
}

