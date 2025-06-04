package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTFunDecl implements ASTNode {
  String param;
  ASTType paramType;
  ASTType returnType;
  ASTNode body;

  public ASTFunDecl(String param, ASTType paramType, ASTNode body, ASTType returnType) {
    this.param = param;
    this.paramType = paramType;
    this.body = body;
    this.returnType = returnType;
  }

  public void setBody(ASTNode body) {
    this.body = body;
  }

  public void setReturnType(ASTType returnType) {
    this.returnType = returnType;
  }

  public ASTType getParamType() {
    return this.paramType;
  }

  public String getParam() {
    return this.param;
  }

  public ASTType getReturnType() {
    return this.returnType;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    Environment<ASTType> en;
    en = e.beginScope();
    en.assoc(param, paramType);
    ASTType tb = this.body.typecheck(en);
    return new ASTTArrow(paramType, tb);
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VClos(this.param, this.body, e);
  }
}
