package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTPrint implements ASTNode {
  ASTNode exp;
  boolean insertNewLine = false;

  public ASTPrint(ASTNode exp) {
    this.exp = exp;
  }

  public ASTPrint(ASTNode exp, boolean insertNewLine) {
    this.exp = exp;
    this.insertNewLine = insertNewLine;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    return this.exp.typecheck(e);
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.exp.eval(e);
    if (this.insertNewLine) {
      System.out.println(v1.toStr());
    } else {
      System.out.print(v1.toStr());
    }
    return v1;
  }
}
