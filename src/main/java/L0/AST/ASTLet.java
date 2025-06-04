package L0.AST;

import L0.ASTType.*;
import L0.Bind;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.util.List;

public class ASTLet implements ASTNode {
  List<Bind> decls;
  ASTNode body;

  public ASTLet(List<Bind> decls, ASTNode body) {
    this.decls = decls;
    this.body = body;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    Environment<ASTType> en = e.beginScope();

    for (Bind p : this.decls) {
      String id = p.getId();
      ASTNode exp = p.getExp();
      if (exp instanceof ASTFunDecl) {
        ASTFunDecl funDecl = (ASTFunDecl) exp;
        ASTType paramType = funDecl.getParamType();
        ASTType returnType = funDecl.getReturnType();
        if (returnType != null) {
          ASTTArrow funType = new ASTTArrow(funDecl.getParamType(), new ASTTUnit());
          en.assoc(id, new ASTTArrow(paramType, returnType));
          en.assoc(funDecl.getParam(), funDecl.getParamType());
          return funType;
        } else {
          ASTType type = exp.typecheck(en);
          en.assoc(id, type);
        }
      } else {
        ASTType type = exp.typecheck(en);
        en.assoc(id, type);
      }
    }

    return this.body.typecheck(en);
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    Environment<IValue> en = e.beginScope();

    for (Bind p : this.decls) {
      String id = p.getId();
      ASTNode exp = p.getExp();
      en.assoc(id, exp.eval(en));
    }
    return this.body.eval(en);
  }
}
