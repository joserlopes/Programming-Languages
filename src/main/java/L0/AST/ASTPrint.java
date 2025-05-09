package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTPrint implements ASTNode {

  ASTNode exp;
  boolean insertNewLine = false;

  public ASTPrint(ASTNode exp) {
    this.exp = exp;
  }

  public ASTPrint(ASTNode exp, boolean insertNewLine) {
    this.exp = exp;
    this.insertNewLine = insertNewLine;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    // Evaluate the condition
    IValue v1 = this.exp.eval(e);
    if (v1 instanceof VInt) {
      int i1 = ((VInt) v1).getVal();
      // If it is true evaluate the trueBody
      if (this.insertNewLine) {
        System.out.println(i1);
      } else {
        System.out.print(i1);
      }
      return new VBool(true);
    }
    throw new InterpreterError("illegal types to print");
  }
}
