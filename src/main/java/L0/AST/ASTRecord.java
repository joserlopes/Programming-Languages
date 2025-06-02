package L0.AST;

import L0.Bind;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;
import java.util.ArrayList;
import java.util.List;

public class ASTRecord implements ASTNode {
  List<Bind> decls;

  public ASTRecord(List<Bind> decls, ASTNode body) {
    this.decls = decls;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    Environment<IValue> en = e.beginScope();

    for (Bind p : this.decls) {
      String id = p.getId();
      ASTNode exp = p.getExp();
      en.assoc(id, exp.eval(en));
    }
    return new VRecord(new ArrayList<>());
  }
}
