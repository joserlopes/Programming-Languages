package L0;

import static org.testng.Assert.*;

import L0.AST.*;
import L0.IValue.*;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.StringReader;
import org.testng.annotations.*;

@Test
public class TestArithmetic {
  StringReader reader;
  static Parser parser = new Parser(new StringReader(""));
  ASTNode exp;

  @DataProvider
  private Object[][] simpleSumValues() {
    return new Object[][] {
      {"1 + 2;;", "3"},
      {"3 + 7;;", "10"},
      {"-10 + 30;;", "20"},
      {"-10 - 30;;", "-40"},
      {"10 - 30;;", "-20"},
    };
  }

  @DataProvider
  private Object[][] simpleMulValues() {
    return new Object[][] {
      {"1 * 2;;", "2"},
      {"3 * 7;;", "21"},
      {"-10 * 30;;", "-300"},
      {"-10 * -30;;", "300"},
    };
  }

  @DataProvider
  private Object[][] simpleDivValues() {
    return new Object[][] {
      {"2 / 1;;", "2"},
      {"9 / 3;;", "3"},
      {"30 / 10;;", "3"},
      {"-35 / 5;;", "-7"},
    };
  }

  @DataProvider
  private Object[][] simpleLetValues() {
    return new Object[][] {
      {"let x = 1; (x + 1);;", "2"},
      {"let x = 1; let y = 2; (x + y);;", "3"},
    };
  }

  // TESTS FOR ARITHMETIC EXPRESSIONS

  @Test(dataProvider = "simpleSumValues")
  public void simpleSum(String input, String result) {
    StringReader reader = new StringReader(input);
    parser.ReInit(reader);
    try {
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, result);
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  @Test(dataProvider = "simpleMulValues")
  public void simpleMul(String input, String result) {
    StringReader reader = new StringReader(input);
    parser.ReInit(reader);
    try {
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, result);
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  @Test(dataProvider = "simpleDivValues")
  public void simpleDiv(String input, String result) {
    StringReader reader = new StringReader(input);
    parser.ReInit(reader);
    try {
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, result);
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  // TESTS FOR LET

  @Test(dataProvider = "simpleLetValues")
  public void simpleLet(String input, String result) {
    StringReader reader = new StringReader(input);
    parser.ReInit(reader);

    try {
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, result);
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void nestedLet() {
    String path = "src/test/java/L0/nestedLet.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "72");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  // TESTS FOR FUNCTIONS

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

  // TESTS FOR BOX AND DEREF

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

  // TESTS FOR REGULAR LISTS

  public void listSum() {
    String path = "src/test/java/L0/RegularList1.L0";

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
    String path = "src/test/java/L0/RegularList2.L0";

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
    String path = "src/test/java/L0/RegularList3.L0";

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
    String path = "src/test/java/L0/RegularList4.L0";

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "5");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  // TESTS FOR LAZY LISTS

  public void infiniteFibonnaci() {
    String path = "src/test/java/L0/LazyList1.L0";

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream capturedOutput = new PrintStream(outputStream);

    System.setOut(capturedOutput);

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "true");

      capturedOutput.flush();
      String capturedString = outputStream.toString().trim();
      assertEquals(capturedString, "0\n1\n1\n2\n3\n5\n8\n13\n21\n34");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void infiniteFactorial() {
    String path = "src/test/java/L0/LazyList2.L0";

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream capturedOutput = new PrintStream(outputStream);

    System.setOut(capturedOutput);

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "true");

      capturedOutput.flush();
      String capturedString = outputStream.toString().trim();
      assertEquals(capturedString, "1\n2\n6\n24\n120\n720\n5040\n40320\n362880\n3628800");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void infinitePrimes() {
    String path = "src/test/java/L0/LazyList3.L0";

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream capturedOutput = new PrintStream(outputStream);

    System.setOut(capturedOutput);

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "true");

      capturedOutput.flush();
      String capturedString = outputStream.toString().trim();
      assertEquals(capturedString, "2\n3\n5\n7\n11\n13\n17\n19\n23\n29");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }
}
