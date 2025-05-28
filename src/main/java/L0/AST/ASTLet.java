package L0.AST;

import L0.Bind;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;
import java.util.List;

public class ASTLet implements ASTNode {
  List<Bind> decls;
  ASTNode body;

  public ASTLet(List<Bind> decls, ASTNode body) {
    this.decls = decls;
    this.body = body;
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
