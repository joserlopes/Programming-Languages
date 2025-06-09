package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTDot implements ASTNode {
  ASTNode struct;
  String field;

  public ASTDot(ASTNode struct, String field) {
    this.struct = struct;
    this.field = field;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.struct.typecheck(e);
    if (t1 instanceof ASTTStruct) {
      ASTTStruct s1 = (ASTTStruct) t1;
      ASTType foundType = s1.getBinds().getType(this.field);
      if (foundType == null) {
        throw new TypeCheckError("variant " + this.field + " not found on struct " + s1.toStr());
      }
      return s1.getBinds().getType(this.field);
    } else {
      throw new TypeCheckError("illegal type to . operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.struct.eval(e);
    if (v1 instanceof VStruct) {
      VStruct s1 = (VStruct) v1;
      return s1.getValue(this.field);
    }

    throw new InterpreterError("illegal type to . operator");
  }
}
