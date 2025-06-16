package L0;

import L0.AST.*;
import L0.ASTType.ASTType;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class L0int {
  public static void main(String args[]) throws IOException {
    ASTNode exp;

    if (args.length == 1) {
      if (args[0].endsWith(".xpp")) {
        try {
          FileReader file = new FileReader(args[0]);
          Parser parser = new Parser(file);
          exp = parser.Start();
          if (exp == null) {
            file.close();
            System.exit(0);
          }
          ASTType type = exp.typecheck(new Environment<ASTType>());
          System.out.println(type.toStr());
          IValue v = exp.eval(new Environment<IValue>());
          System.out.println(v.toStr());
        } catch (FileNotFoundException e) {
          System.out.println("File " + args[0] + "not found");
        } catch (TypeCheckError e) {
          System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
          if (e.getMessage().equals("Struct Creation")) {
            System.out.println("Error: A struct cannot have fields with repeated names.");
          } else if (e.getMessage().equals("Type Creation")) {
            System.out.println(
                "Error: A struct or union type definition cannot have fields with repeated names.");
          } else {
            System.out.println("Syntax Error.");
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        System.out.println("Error: File must end in .xpp");
      }
    } else if (args.length == 0) {
      Parser parser = new Parser(System.in);
      System.out.println("Xpp interpreter PL MEIC 2024/25\n");
      while (true) {
        try {
          System.out.print("# ");
          exp = parser.Start();
          if (exp == null) System.exit(0);
          ASTType type = exp.typecheck(new Environment<ASTType>());
          System.out.println(type.toStr());
          IValue v = exp.eval(new Environment<IValue>());
          System.out.println(v.toStr());
        } catch (ParseException e) {
          if (e.getMessage().equals("Struct Creation")) {
            System.out.println("Error: A struct cannot have fields with repeated names.");
          } else if (e.getMessage().equals("Type Creation")) {
            System.out.println(
                "Error: A struct or union type definition cannot have fields with repeated names.");
          } else {
            System.out.println("Syntax Error.");
          }
          parser.ReInit(System.in);
        } catch (TypeCheckError e) {
          System.out.println("Error: " + e.getMessage());
          parser.ReInit(System.in);
        } catch (Exception e) {
          e.printStackTrace();
          parser.ReInit(System.in);
        }
      }
    } else {
      System.out.println("Usage: x++ <file.xpp>");
    }
  }
}
