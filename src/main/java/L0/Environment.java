package L0;

import java.util.*;

public class Environment<E> {
  Environment<E> anc;
  public Map<String, E> bindings;

  public Environment() {
    anc = null;
    bindings = new HashMap<String, E>();
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
    // NOTE: Is it really just this?
    this.bindings.replace(id, bind);
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

    throw new InterpreterError("Binding for " + id + " not found");
  }
}
