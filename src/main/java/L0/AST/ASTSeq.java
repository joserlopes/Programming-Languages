package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;
import java.util.List;

public class ASTSeq implements ASTNode {
  List<ASTNode> exps;

  public ASTSeq(List<ASTNode> exps) {
    this.exps = exps;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue val = null;

    for (ASTNode exp : this.exps) {
      val = exp.eval(e);
    }

    return val;
  }
}
