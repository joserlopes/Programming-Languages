package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.util.List;

public class ASTFunDecl implements ASTNode {
  String param;
  List<ASTType> paramsTypes;
  ASTNode body;

  public ASTFunDecl(String param, ASTNode body) {
    this.param = param;
    this.body = body;
  }

  public void setBody(ASTNode body) {
    this.body = body;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    // Environment<ASTType> en;
    // en = e.beginScope();
    // // NOTE: Do we need to check that params and paramsTypes have the same lenght?
    // for (int i = 0; i < params.size(); i++) {
    //   en.assoc(this.params.get(i), this.paramsTypes.get(i));
    // }
    // ASTType tb = this.body.typecheck(en);
    return new ASTTArrow(null, null);
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VClos(this.param, this.body, e);
  }
}
