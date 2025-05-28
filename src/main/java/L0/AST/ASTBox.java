package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTBox implements ASTNode {
  ASTNode exp;

  public ASTBox(ASTNode exp) {
    this.exp = exp;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VBox(this.exp.eval(e));
  }
}
