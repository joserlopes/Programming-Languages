package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTBox implements ASTNode {
  ASTNode exp;

  public ASTBox(ASTNode exp) {
    this.exp = exp;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VBox(this.exp.eval(e));
  }
}
