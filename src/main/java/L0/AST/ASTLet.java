package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import L0.TypedBind;
import java.util.List;

public class ASTLet implements ASTNode {
  List<TypedBind> decls;
  ASTNode body;

  public ASTLet(List<TypedBind> decls, ASTNode body) {
    this.decls = decls;
    this.body = body;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    Environment<ASTType> en = e.beginScope();

    // If it has a typed bound to it, after the evaluation, check if they are the same

    // We need to do a first pass through in order to type check potentially mutually recursive
    // functions

    for (TypedBind p : this.decls) {
      String id = p.getId();
      ASTType type = p.getType();

      if (type != null) {
        type = en.unfoldTypes(type);
        en.assoc(id, type);
      }
    }

    for (TypedBind p : this.decls) {
      String id = p.getId();
      ASTNode exp = p.getExp();
      ASTType type = p.getType();

      ASTType expType = exp.typecheck(en);
      expType = en.unfoldTypes(expType);

      if (type != null) {
        type = en.unfoldTypes(type);
        if (!expType.isSubtype(type, en)) {
          throw new TypeCheckError(
              "illegal type to variable declaration. Variable declared as: "
                  + type.toStr()
                  + " or any of this subtypes"
                  + ", got: "
                  + expType.toStr());
        }
      } else {
        en.assoc(id, expType);
      }
    }

    return this.body.typecheck(en);
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    Environment<IValue> en = e.beginScope();

    for (TypedBind p : this.decls) {
      String id = p.getId();
      ASTNode exp = p.getExp();
      en.assoc(id, exp.eval(en));
    }
    return this.body.eval(en);
  }
}
