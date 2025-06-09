package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTDot implements ASTNode {
  ASTNode record;
  String field;

  public ASTDot(ASTNode record, String field) {
    this.record = record;
    this.field = field;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.record.typecheck(e);
    if (t1 instanceof ASTTRecord) {
      ASTTRecord r1 = (ASTTRecord) t1;
      ASTType foundType = r1.getBinds().getType(this.field);
      if (foundType == null) {
        throw new TypeCheckError("variant " + this.field + " not found on struct " + r1.toStr());
      }
      return r1.getBinds().getType(this.field);
    } else {
      throw new TypeCheckError("illegal type to . operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.record.eval(e);
    if (v1 instanceof VRecord) {
      VRecord r1 = (VRecord) v1;
      return r1.getValue(this.field);
    }

    throw new InterpreterError("illegal type to . operator");
  }
}
