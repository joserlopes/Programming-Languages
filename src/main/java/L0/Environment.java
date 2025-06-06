package L0;

import L0.ASTType.*;
import L0.Errors.InterpreterError;
import java.util.*;

public class Environment<E> {
  Environment<E> anc;
  public Map<String, E> bindings;

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
          if (a1 instanceof ASTTArrow) {
            ASTTArrow arrow1 = (ASTTArrow) a1;
            System.out.println(
                bind
                    + " is a function with domain: "
                    + arrow1.getDomain().toStr()
                    + " and codomain: "
                    + arrow1.getCoDomain().toStr());
          }
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
}
