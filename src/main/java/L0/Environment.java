package L0;

import L0.ASTType.*;
import L0.Errors.InterpreterError;
import java.util.HashMap;
import java.util.Map;

public class Environment<E> {
  Environment<E> anc;
  Map<String, E> bindings;
  Map<String, ASTType> alreadyUnrolled = new HashMap<String, ASTType>();

  public Environment() {
    this.anc = null;
    this.bindings = new HashMap<String, E>();
  }

  public Environment(Environment<E> ancestor) {
    this.anc = ancestor;
    this.bindings = new HashMap<String, E>();
  }

  public Environment<E> beginScope() {
    return new Environment<E>(this);
  }

  public Environment<E> endScope() {
    return anc;
  }

  public void assoc(String id, E bind) throws InterpreterError {
    // A binding for the same name cannot happen more that once in an environment
    if (this.bindings.get(id) != null) {
      throw new InterpreterError(
          "Binding for " + id + " already exists in this the current environment");
    } else {
      this.bindings.put(id, bind);
    }
  }

  public void update(String id, E bind) {
    this.bindings.replace(id, bind);
  }

  // This is just a function for debugging purposes
  public void printBindings() {
    Environment<E> current_env = this;
    while (current_env != null) {
      System.out.println(current_env.bindings);
      for (String bind : current_env.bindings.keySet()) {
        E a = current_env.bindings.get(bind);
        if (a instanceof ASTType) {
          ASTType a1 = (ASTType) a;
          // if (a1 instanceof ASTTArrow) {
          //   ASTTArrow arrow1 = (ASTTArrow) a1;
          //   System.out.println(
          //       bind
          //           + " is a function with domain: "
          //           + arrow1.getDomain().toStr()
          //           + " and codomain: "
          //           + arrow1.getCoDomain().toStr());
          // }
          System.out.println(bind + ": " + a1.toStr());
        }
      }
      current_env = current_env.anc;
    }
  }

  public E find(String id) throws InterpreterError {
    Environment<E> current_env = this;
    while (current_env != null) {
      E binding = current_env.bindings.get(id);
      if (binding != null) {
        return binding;
      }
      current_env = current_env.anc;
    }

    throw new InterpreterError("Binding for " + "\"" + id + "\"" + " not found");
  }

  public ASTType unrollTypes(ASTType type) throws InterpreterError {
    if (type instanceof ASTTId) {
      return this.unrollId((ASTTId) type);
    } else if (type instanceof ASTTArrow) {
      return this.unrollArrow((ASTTArrow) type);
    } else if (type instanceof ASTTBox) {
      return this.unrollBox((ASTTBox) type);
    } else if (type instanceof ASTTStruct) {
      return this.unrollStruct((ASTTStruct) type);
    } else if (type instanceof ASTTUnion) {
      return this.unrollUnion((ASTTUnion) type);
    }

    return type;
  }

  private ASTType unrollId(ASTTId type) throws InterpreterError {
    String typeName = type.toStr();

    if (this.alreadyUnrolled.containsKey(typeName)) {
      return this.alreadyUnrolled.get(typeName);
    }

    this.alreadyUnrolled.put(typeName, type);

    return this.unrollTypes((ASTType) this.find(type.toStr()));
  }

  private ASTType unrollArrow(ASTTArrow type) throws InterpreterError {
    ASTType newDom = this.unrollTypes(type.getDomain());
    ASTType newCoDom = this.unrollTypes(type.getCoDomain());

    return new ASTTArrow(newDom, newCoDom);
  }

  private ASTType unrollBox(ASTTBox type) throws InterpreterError {
    ASTType newType = this.unrollTypes(type.getType());

    return new ASTTBox(newType);
  }

  private ASTType unrollStruct(ASTTStruct type) throws InterpreterError {
    HashMap<String, ASTType> lbl = new HashMap<String, ASTType>();
    for (Map.Entry<String, ASTType> entry : type.getBinds().getTbl().entrySet()) {
      lbl.put(entry.getKey(), (ASTType) this.unrollTypes(entry.getValue()));
    }

    return new ASTTStruct(new TypeBindList(lbl));
  }

  private ASTType unrollUnion(ASTTUnion type) throws InterpreterError {
    HashMap<String, ASTType> lbl = new HashMap<String, ASTType>();
    for (Map.Entry<String, ASTType> entry : type.getBinds().getTbl().entrySet()) {
      lbl.put(entry.getKey(), (ASTType) this.unrollTypes(entry.getValue()));
    }

    return new ASTTUnion(new TypeBindList(lbl));
  }
}
