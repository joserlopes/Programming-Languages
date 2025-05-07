package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTInt implements ASTNode {
  int v;

  public ASTInt(int v0) {
    v = v0;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VInt(v);
  }
}
