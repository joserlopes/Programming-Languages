package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;
import java.util.List;

public class ASTFunDecl implements ASTNode {
  List<String> params;
  ASTNode body;

  public ASTFunDecl(List<String> params, ASTNode body) {
    this.params = params;
    this.body = body;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    // I mean, do we evaluate anything here, yet??
    return new VClos(this.params, this.body, e);
  }
}
