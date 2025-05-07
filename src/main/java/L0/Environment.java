package L0;

import java.util.*;

public class Environment<E> {
  Environment<E> anc;
  Map<String, E> bindings;

  public Environment() {
    anc = null;
    bindings = new HashMap<String, E>();
  }

  public Environment(Environment<E> ancestor) {
    // code missing
  }

  public Environment<E> beginScope() {
    return new Environment<E>(this);
  }

  public Environment<E> endScope() {
    return anc;
  }

  public void assoc(String id, E bind) throws InterpreterError {
    // code missing
  }

  public void update(String id, E bind) {
    // code missing
  }

  public E find(String id) throws InterpreterError {
    return this.bindings.get(id);
  }
}
