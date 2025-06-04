package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTUnion implements ASTNode {
  String lbl;
  ASTNode exp;

  public ASTUnion(String lbl, ASTNode exp) {
    this.lbl = lbl;
    this.exp = exp;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    return new ASTTUnit();
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VUnion(this.lbl, this.exp.eval(e));
  }
}
