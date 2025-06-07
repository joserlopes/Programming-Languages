package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.util.HashMap;
import java.util.Map;

public class ASTTypeDef implements ASTNode {
  HashMap<String, ASTType> ltd;
  ASTNode body;

  public ASTTypeDef(HashMap<String, ASTType> ltdp, ASTNode body) {
    this.ltd = ltdp;
    this.body = body;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    Environment<ASTType> en = e.beginScope();

    for (Map.Entry<String, ASTType> entry : this.ltd.entrySet()) {
      en.assoc(entry.getKey(), entry.getValue());
    }

    // Pass again through all the types to check for ASTTId's
    for (Map.Entry<String, ASTType> entry : this.ltd.entrySet()) {
      String name = entry.getKey();
      ASTType type = entry.getValue();
      ASTType unrolledType = this.unrollTypes(en, type);
      en.update(name, unrolledType);
    }

    return this.body.typecheck(en);
  }

  // BUG: This produces a stack overflow when analysing recursive types!!!!!
  private ASTType unrollTypes(Environment<ASTType> e, ASTType type) throws InterpreterError {
    if (type instanceof ASTTId) {
      return this.unrollId(e, (ASTTId) type);
    } else if (type instanceof ASTTRecord) {
      return this.unrollRecord(e, (ASTTRecord) type);
    } else if (type instanceof ASTTUnion) {
      return this.unrollUnion(e, (ASTTUnion) type);
    }

    return type;
  }

  // TODO: This is where it can stack overflow!!
  private ASTType unrollId(Environment<ASTType> e, ASTTId type) throws InterpreterError {
    return unrollTypes(e, e.find(type.toStr()));
  }

  private ASTType unrollRecord(Environment<ASTType> e, ASTTRecord type) throws InterpreterError {
    HashMap<String, ASTType> lbl = new HashMap<String, ASTType>();
    for (Map.Entry<String, ASTType> entry : type.getBinds().getTbl().entrySet()) {
      lbl.put(entry.getKey(), unrollTypes(e, entry.getValue()));
    }

    return new ASTTRecord(new TypeBindList(lbl));
  }

  private ASTType unrollUnion(Environment<ASTType> e, ASTTUnion type) throws InterpreterError {
    HashMap<String, ASTType> lbl = new HashMap<String, ASTType>();
    for (Map.Entry<String, ASTType> entry : type.getBinds().getTbl().entrySet()) {
      lbl.put(entry.getKey(), unrollTypes(e, entry.getValue()));
    }

    return new ASTTUnion(new TypeBindList(lbl));
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return body.eval(e);
  }
}
