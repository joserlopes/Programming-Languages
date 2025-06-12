package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTMatchList implements ASTNode {
  ASTNode matchedValue, nilCase, consCase;
  String headName, tailName;

  public ASTMatchList(
      ASTNode matchedValue, ASTNode nilCase, String headName, String tailName, ASTNode consCase) {
    this.matchedValue = matchedValue;
    this.nilCase = nilCase;
    this.consCase = consCase;
    this.headName = headName;
    this.tailName = tailName;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.matchedValue.typecheck(e);

    if (t1 instanceof ASTTId) {
      t1 = e.unfoldTypes(t1);
    }

    if (t1 instanceof ASTTList) {
      ASTTList l1 = (ASTTList) t1;

      ASTType t2 = this.nilCase.typecheck(e);

      Environment<ASTType> en = e.beginScope();

      en.assoc(this.headName, en.unfoldTypes(l1.getType()));
      en.assoc(this.tailName, en.unfoldTypes(l1));

      ASTType t3 = this.consCase.typecheck(en);

      if (t2 instanceof ASTTList && t3 instanceof ASTTList) {
        ASTTList l2 = (ASTTList) t2;
        if (l2.getType().getClass() == ASTTUnit.class) {
          return t3;
        }
      }

      if (t2.toStr().equals(t3.toStr())) {
        return t2;
      } else {
        throw new TypeCheckError("illegal type to match body " + t2.toStr() + ", " + t3.toStr());
      }
    } else {
      throw new TypeCheckError("illegal type to match operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.matchedValue.eval(e);

    if (v1 instanceof VCons) {
      VCons c1 = (VCons) v1;

      IValue v2 = c1.getHead();
      IValue v3 = c1.getTail();

      Environment<IValue> en = e.beginScope();
      en.assoc(headName, v2);
      en.assoc(tailName, v3);

      return consCase.eval(en);
    } else if (v1 instanceof VLCons) {
      VLCons lc1 = (VLCons) v1;

      IValue v2 = lc1.getHead();
      IValue v3 = lc1.getTail();
      Environment<IValue> en = e.beginScope();
      en.assoc(headName, v2);
      en.assoc(tailName, v3);

      return this.consCase.eval(en);
    } else if (v1 instanceof VNil) {
      return this.nilCase.eval(e);
    }

    throw new InterpreterError("invalid type to match");
  }
}
