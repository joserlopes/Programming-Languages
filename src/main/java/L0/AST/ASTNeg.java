package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTNeg implements ASTNode {
  ASTNode exp;

  public ASTNeg(ASTNode e) {
    this.exp = e;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v0 = this.exp.eval(e);
    if (v0 instanceof VInt) {
      int i0 = ((VInt) v0).getVal();
      return new VInt(-i0);
    }

    throw new InterpreterError("illegal types to neg operator");
  }
}
