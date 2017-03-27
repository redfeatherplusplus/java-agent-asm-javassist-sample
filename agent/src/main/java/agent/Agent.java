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
                	
//                    // ASM Code
//                    ClassReader reader = new ClassReader(bytes);
//                    ClassWriter writer = new ClassWriter(reader, 0);
//                    ClassPrinter visitor = new ClassPrinter(writer);
//                    reader.accept(visitor, 0);
//                    return writer.toByteArray();

            		ClassReader cr = new ClassReader(bytes);
            		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            		ClassTransformVisitor ca = new ClassTransformVisitor(cw);
            		cr.accept(ca, 0);
                    return cw.toByteArray();
                }

                return null;
            }
        });
    }
    
    public static void agentmain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

//                if (s.startsWith("org/apache/commons/dbutils")) {
                if ("other/Stuff".equals(s)) {
                    // ASM Code
                    System.out.println("    Accessing!" +
                            "\n   ClassLoader: " + classLoader.toString() +
                            "\n   String: " + s +
                            "\n   ProtectionDomain: " + protectionDomain.toString() +
                            "\n   bytes: " + bytes.toString()
                    );
                    ClassReader reader = new ClassReader(bytes);
                    ClassWriter writer = new ClassWriter(reader, 0);
                    ClassPrinter visitor = new ClassPrinter(writer);
                    reader.accept(visitor, 0);
                    return writer.toByteArray();
                }

                return bytes;
            }
        });
    }
}

