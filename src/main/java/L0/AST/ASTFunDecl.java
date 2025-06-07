package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTFunDecl implements ASTNode {
  String param;
  ASTType paramType;
  ASTNode body;

  public ASTFunDecl(String param, ASTType paramType, ASTNode body) {
    this.param = param;
    this.paramType = paramType;
    this.body = body;
  }

  public void setBody(ASTNode body) {
    this.body = body;
  }

  public ASTType getParamType() {
    return this.paramType;
  }

  public String getParam() {
    return this.param;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    Environment<ASTType> en;
    en = e.beginScope();
    ASTType actualParamType = null;

    if (this.paramType instanceof ASTTId) {
      actualParamType = en.unrollTypes(this.paramType);
      en.assoc(this.param, actualParamType);
    } else {
      en.assoc(this.param, this.paramType);
      actualParamType = this.paramType;
    }

    ASTType tb = this.body.typecheck(en);
    return new ASTTArrow(actualParamType, tb);
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VClos(this.param, this.body, e);
  }
}
