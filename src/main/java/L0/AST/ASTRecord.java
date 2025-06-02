package L0.AST;

import L0.ASTType.*;
import L0.Bind;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.util.HashMap;
import java.util.List;

public class ASTRecord implements ASTNode {
  List<Bind> decls;

  public ASTRecord(List<Bind> decls) {
    this.decls = decls;
  }

  @Override
  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    // TODO: once the explicit types for records are defined come back here.
    return new ASTTRecord(null);
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    Environment<IValue> en = e.beginScope();
    HashMap<String, IValue> fields = new HashMap<>();

    for (Bind p : this.decls) {
      String id = p.getId();
      IValue expVal = p.getExp().eval(en);
      en.assoc(id, expVal);
      fields.put(id, expVal);
    }

    return new VRecord(fields, en);
  }
}
