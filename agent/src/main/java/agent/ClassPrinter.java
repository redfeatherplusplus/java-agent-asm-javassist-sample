package agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassPrinter extends ClassVisitor {
    String className;

    public ClassPrinter(ClassWriter writer) {
        super(Opcodes.ASM4, writer);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        System.out.println("\n\nVisiting Class...");
        this.className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        System.out.println("\n\nVisiting Method...");
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new MethodPrinter(mv, name, className);
    }

    @Override
    public void visitEnd() {
//        System.out.println("\n\nEnd visiting...");
        super.visitEnd();
    }
}