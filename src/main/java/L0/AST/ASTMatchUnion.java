package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import L0.UnionBind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ASTMatchUnion implements ASTNode {
  ASTNode matchedValue;
  List<UnionBind> decls;

  public ASTMatchUnion(ASTNode matchedValue, List<UnionBind> decls) {
    this.matchedValue = matchedValue;
    this.decls = decls;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.matchedValue.typecheck(e);

    if (t1 instanceof ASTTId) {
      t1 = e.unfoldTypes(t1);
    }

    if (t1 instanceof ASTTUnion) {
      int matched = 0;

      ASTTUnion u1 = (ASTTUnion) t1;
      HashMap<String, ASTType> matchableLabels = u1.getBinds().getTbl();

      List<ASTType> foundTypes = new ArrayList<ASTType>();

      Environment<ASTType> en = e.beginScope();

      for (UnionBind bind : this.decls) {
        String label = bind.getLabel();
        if (matchableLabels.containsKey(label)) {
          matched++;

          ASTType labelType = matchableLabels.get(label);
          String name = bind.getName();

          if (name != null) {
            labelType = en.unfoldTypes(labelType);
            en.assoc(name, labelType);
          } else {
            if (!(labelType instanceof ASTTUnit)) {
              throw new TypeCheckError("Only unit types can be ignored inside match branches.");
            }
          }

          ASTType foundType = bind.getExp().typecheck(en);

          for (ASTType type : foundTypes) {
            if (!(type.isSubtype(foundType, en) && foundType.isSubtype(type, en))) {
              throw new TypeCheckError(
                  "illegal type to match branch. All branches need to have the same type. Expected:"
                      + " "
                      + type.toStr()
                      + ", got: "
                      + foundType.toStr());
            }
          }

          foundTypes.add(foundType);
          en = en.endScope();
        }
      }

      if (matched != matchableLabels.size()) {
        throw new TypeCheckError("matching on unions needs to be exhaustive on possible labels.");
      }

      if (foundTypes.size() == 0) {
        return new ASTTUnit();
      } else {
        return foundTypes.get(0);
      }
    }

    throw new TypeCheckError("illegal type to match operator: " + t1.toStr());
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.matchedValue.eval(e);

    if (v1 instanceof VUnion) {
      VUnion u1 = (VUnion) v1;

      Environment<IValue> en = e.beginScope();

      for (UnionBind bind : this.decls) {
        String bindLabel = bind.getLabel();
        if (bindLabel.equals(u1.getLabel())) {
          String name = bind.getName();

          if (name != null) {
            en.assoc(name, u1.getValue());
          }

          return bind.getExp().eval(en);
        }
      }

      throw new InterpreterError("name not found");
    }

    throw new InterpreterError("illegal type to match.");
  }
}
