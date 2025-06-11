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

    // BUG: We actually don't unfold here, we do it only when needed

    // Pass again through all the types to unfold them
    // for (Map.Entry<String, ASTType> entry : this.ltd.entrySet()) {
    //   String name = entry.getKey();
    //   ASTType type = entry.getValue();
    //   ASTType unfoldedType = en.unfoldTypes(type);
    //   en.update(name, unfoldedType);
    // }

    return this.body.typecheck(en);
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return body.eval(e);
  }
}
