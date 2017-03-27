package agent;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by HL on 3/21/17.
 */
public class MethodPrinter extends MethodVisitor implements Opcodes{
    String mName;
    String classname;

    public MethodPrinter(final MethodVisitor mv, String name, String className){
        super(Opcodes.ASM4, mv);
        this.mName = name;
        this.classname = className;
    }

    @Override
    public void visitCode() {
//        System.out.println("Visiting " + mName + "...");
        super.visitCode();
    } 

    @Override
    public void visitLineNumber(int i, Label label) {
    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn("line " + i + " executed");
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    	
        super.visitLineNumber(i, label);
    }

    @Override
    public void visitEnd() {
//        System.out.println(mName + " end visiting.");
        super.visitEnd();
    }
}
