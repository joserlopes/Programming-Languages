package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTDot implements ASTNode {
  ASTNode record;
  String field;

  public ASTDot(ASTNode record, String field) {
    this.record = record;
    this.field = field;
  }

  // public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
  //   ASTType t1 = this.lhs.typecheck(e);
  //   if (t1 instanceof ASTTbool) {
  //     ASTType t2 = this.rhs.typecheck(e);
  //     if (t2 instanceof ASTTbool) {
  //       return t1;
  //     } else {
  //       throw new TypeCheckError("illegal type to && operator " + t2.toStr());
  //     }
  //   } else {
  //     throw new TypeCheckError("illegal type to && operator " + t1.toStr());
  //   }
  // }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.record.eval(e);
    if (v1 instanceof VRecord) {
      VRecord r1 = (VRecord) v1;
      return r1.getValue(this.field);
    }

    throw new InterpreterError("illegal types to && operator");
  }
}
