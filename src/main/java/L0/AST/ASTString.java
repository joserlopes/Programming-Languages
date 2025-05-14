package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTString implements ASTNode {
  String v;

  public ASTString(String v) {
    this.v = v;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VString(this.v);
  }
}
