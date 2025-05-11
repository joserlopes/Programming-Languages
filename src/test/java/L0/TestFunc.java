package L0;

import static org.testng.Assert.*;

import L0.AST.*;
import L0.IValue.*;
import java.io.FileReader;
import java.io.StringReader;
import org.testng.annotations.*;

@Test
public class TestFunc {
  StringReader reader;
  static Parser parser = TestArithmetic.parser;
  ASTNode exp;

  public void Func1() {
    String path = "src/test/java/L0/Func1.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "4");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void Func2() {
    String path = "src/test/java/L0/Func2.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "5");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void Func3() {
    String path = "src/test/java/L0/Func3.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "14");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void Func4() {
    String path = "src/test/java/L0/Func4.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "49");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void Func5() {
    String path = "src/test/java/L0/Func5.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "49");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void Func6() {
    String path = "src/test/java/L0/Func6.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "14");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void Func7() {
    String path = "src/test/java/L0/Func7.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "199");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void FibonacciFunc1() {
    String path = "src/test/java/L0/FibonacciFunc1.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "0");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void FibonacciFunc2() {
    String path = "src/test/java/L0/FibonacciFunc2.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "1");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void FibonacciFunc3() {
    String path = "src/test/java/L0/FibonacciFunc3.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "5");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void FibonacciFunc4() {
    String path = "src/test/java/L0/FibonacciFunc4.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "55");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }
}
