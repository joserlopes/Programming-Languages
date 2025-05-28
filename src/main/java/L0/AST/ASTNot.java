package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTNot implements ASTNode {
  ASTNode exp;

  public ASTNot(ASTNode rhs) {
    this.exp = rhs;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v0 = this.exp.eval(e);
    if (v0 instanceof VBool) {
      boolean b0 = ((VBool) v0).getVal();
      return new VBool(!b0);
    }

    throw new InterpreterError("illegal types to ~ operator");
  }
}
