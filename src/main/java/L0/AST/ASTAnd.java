package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTAnd implements ASTNode {
  ASTNode lhs, rhs;

  public ASTAnd(ASTNode lhs, ASTNode rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.lhs.eval(e);
    if (v1 instanceof VBool) {
      IValue v2 = this.rhs.eval(e);
      if (v2 instanceof VBool) {
        boolean b1 = ((VBool) v1).getVal();
        boolean b2 = ((VBool) v2).getVal();
        return new VBool(b1 && b2);
      }
    }

    throw new InterpreterError("illegal types to && operator");
  }
}
