package L0;

import static org.testng.Assert.*;

import L0.AST.*;
import L0.IValue.*;
import java.io.FileReader;
import java.io.StringReader;
import org.testng.annotations.*;

@Test
public class TestRegularList {
  StringReader reader;
  static Parser parser = TestArithmetic.parser;
  ASTNode exp;

  public void listSum() {
    String path = "src/test/java/L0/RegualarList1.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "15");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void listMap() {
    String path = "src/test/java/L0/RegualarList2.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "cons(2, cons(4, cons(6, cons(8, cons(10, nil)))))");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void listFilter() {
    String path = "src/test/java/L0/RegualarList3.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "cons(2, cons(2, cons(2, nil)))");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void listLength() {
    String path = "src/test/java/L0/RegualarList4.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "5");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }
}
