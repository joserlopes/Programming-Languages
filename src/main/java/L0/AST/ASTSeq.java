package L0.AST;

import L0.ASTType.ASTType;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.util.List;

public class ASTSeq implements ASTNode {
  List<ASTNode> exps;

  public ASTSeq(List<ASTNode> exps) {
    this.exps = exps;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType type = null;

    for (ASTNode t : this.exps) {
      type = t.typecheck(e);
    }

    return type;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue val = null;

    for (ASTNode exp : this.exps) {
      val = exp.eval(e);
    }

    return val;
  }
}
