package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTCons implements ASTNode {
  ASTNode head, tail;

  public ASTCons(ASTNode head, ASTNode tail) {
    this.head = head;
    this.tail = tail;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.head.typecheck(e);
    ASTType t2 = this.tail.typecheck(e);

    if (t2 instanceof ASTTList) {
      ASTTList l1 = (ASTTList) t2;
      if (l1.getType().getClass() == ASTTUnit.class) {
        return new ASTTList(t1);
      }

      if (t1.toStr().equals(l1.getType().toStr())) {
        return l1;
      } else {
        throw new TypeCheckError("illegal list type " + t1.toStr() + " " + l1.toStr());
      }
    } else {
      throw new TypeCheckError("illegal list type " + t1.toStr() + " " + t2.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.head.eval(e);
    IValue v2 = this.tail.eval(e);

    return new VCons(v1, v2);
  }
}
