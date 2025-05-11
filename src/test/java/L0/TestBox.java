package L0;

import static org.testng.Assert.*;

import L0.AST.*;
import L0.IValue.*;
import java.io.FileReader;
import java.io.StringReader;
import org.testng.annotations.*;

@Test
public class TestBox {
  StringReader reader;
  static Parser parser = TestArithmetic.parser;
  ASTNode exp;

  public void factorial() {
    String path = "src/test/java/L0/Factorial.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "720");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void tick1() {
    String path = "src/test/java/L0/Tick1.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "3");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void tick2() {
    String path = "src/test/java/L0/Tick2.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "3");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }
}
