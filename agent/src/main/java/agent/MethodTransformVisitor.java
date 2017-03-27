package agent;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	protected String mName;
	protected boolean isGOTOLabel;
	protected int lastVisitedLine;
	protected String className;
	
    public MethodTransformVisitor(final MethodVisitor mv, String name, String className) {
        super(ASM5, mv);
        this.mName=name;
        this.className=className;
        isGOTOLabel = false;
    }

//    @Override
//    public void visitCode(){
//    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//    	mv.visitLdcInsn(mName+" executed");
//    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//    	super.visitCode();
//    }
    
	@Override
	public void visitLineNumber(int line, Label start) {
    	lastVisitedLine = line;
    	isGOTOLabel = false;
    	
	    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn(className + " : " + line);
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	
    	super.visitLineNumber(line, start);
	}
	
    // label visiting after branching statement
	@Override
	public void visitLabel(Label label) {
		
    	if (isGOTOLabel) {
    	    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("line "+ lastVisitedLine +" executed from label: " + label.toString());
        	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	}
    	
    	super.visitLabel(label);
	}
    // label visiting after jump statement
	@Override
	public void visitJumpInsn(int opcode, Label label) {
    	isGOTOLabel = GOTO == opcode ? true : false;
    	super.visitJumpInsn(opcode, label);
	}
}