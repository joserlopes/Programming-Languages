package L0.AST;

import L0.ASTType.*;
import L0.Bind;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.util.HashMap;
import java.util.List;

public class ASTUnion implements ASTNode {
  List<Bind> decls;

  public ASTUnion(List<Bind> decls) {
    this.decls = decls;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    HashMap<String, ASTType> tbl = new HashMap<>();

    for (Bind p : this.decls) {
      String id = p.getId();
      ASTNode exp = p.getExp();
      if (exp == null) {
        tbl.put(id, new ASTTUnit());
      } else {
        tbl.put(id, exp.typecheck(e));
      }
    }

    return new ASTTUnion(new TypeBindList(tbl));
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    Bind bind = this.decls.get(0);
    String id = bind.getId();
    ASTNode exp = bind.getExp();
    if (exp == null) {
      return new VUnion(id, new VUnit());
    } else {
      return new VUnion(id, exp.eval(e));
    }
  }
}
