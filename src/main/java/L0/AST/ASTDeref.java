package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTDeref implements ASTNode {
  ASTNode exp;

  public ASTDeref(ASTNode exp) {
    this.exp = exp;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.exp.eval(e);
    if (v1 instanceof VBox) {
      return ((VBox) v1).deref();
    }

    throw new InterpreterError("illegal type to be dereferenced");
  }
}
