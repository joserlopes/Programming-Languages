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
      tbl.put(id, exp.typecheck(e));
    }

    return new ASTTUnion(new TypeBindList(tbl));
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VUnion(this.decls.get(0).getId(), this.decls.get(0).getExp().eval(e));
  }
}
