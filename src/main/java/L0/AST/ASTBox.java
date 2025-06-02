package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTBox implements ASTNode {
  ASTNode exp;

  public ASTBox(ASTNode exp) {
    this.exp = exp;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    return new ASTTBox(this.exp.typecheck(e));
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VBox(this.exp.eval(e));
  }
}
