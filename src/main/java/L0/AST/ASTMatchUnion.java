package L0.AST;

import L0.ASTType.ASTType;
import L0.Bind;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.util.List;

public class ASTMatchUnion implements ASTNode {
  ASTNode matchedValue;
  List<Bind> decls;

  public ASTMatchUnion(ASTNode matchedValue, List<Bind> decls) {
    this.matchedValue = matchedValue;
    this.decls = decls;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    throw new TypeCheckError("ASTMatchUnion type checker: Not implemented");
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    throw new InterpreterError("ASTMatchUnion evaluator : Not implemented");
  }
}
