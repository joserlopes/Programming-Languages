package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTLCons implements ASTNode {
  ASTNode head, tail;

  public ASTLCons(ASTNode head, ASTNode tail) {
    this.head = head;
    this.tail = tail;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VLCons(this.head, this.tail, e);
  }
}
