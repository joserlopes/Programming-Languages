package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTInt implements ASTNode {
  int v;

  public ASTInt(int v) {
    this.v = v;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VInt(this.v);
  }
}
