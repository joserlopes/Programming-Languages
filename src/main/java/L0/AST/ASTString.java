package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTString implements ASTNode {
  String value;

  public ASTString(String value) {
    this.value = value;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VString(this.value);
  }
}
