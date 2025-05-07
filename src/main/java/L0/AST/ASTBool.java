package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTBool implements ASTNode {
  boolean v;

  public ASTBool(boolean v) {
    this.v = v;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VBool(this.v);
  }
}
