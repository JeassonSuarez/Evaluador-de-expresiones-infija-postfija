import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class Main {
  public static void main(String[] args) {
    new Main();
  }

  int valJerarquiaCola;
  int valJerarquiaPila;
  int x=0;

  public static LinkedList<String> colaExpresion = new LinkedList<>();
  public static LinkedList<String> expPos = new LinkedList<>();

  public Main() {
    Scanner n = new Scanner(System.in);
    separarExpresion(n.nextLine());
    Stack<String> pilaOperaciones = new Stack<>();
    // while (!colaExpresion.isEmpty()) {
    //   System.out.println(colaExpresion.poll());
    // }
    while (!colaExpresion.isEmpty()) {
      String elemento = colaExpresion.poll();
      if(elemento == "%"){
        valJerarquiaCola = 4;
      }else if (elemento == "p") {
        valJerarquiaCola = 3;
      }else if (elemento == "*" || elemento == "/") {
        valJerarquiaCola = 2;
      }else if (elemento == "+" || elemento == "-") {
        valJerarquiaCola = 1;
      }
      
      if ("%p*+-/".contains(elemento)) {
        if (pilaOperaciones.empty()){
          pilaOperaciones.push(elemento);
        }else{
          if(pilaOperaciones.peek() == "%"){
            valJerarquiaPila = 4;
          }else if (pilaOperaciones.peek() == "p") {
            valJerarquiaPila = 3;
          }else if (pilaOperaciones.peek() == "*" || elemento == "/") {
            valJerarquiaPila = 2;
          }else if (pilaOperaciones.peek() == "+" || elemento == "-") {
            valJerarquiaPila = 1;
          }

          if (valJerarquiaCola == valJerarquiaPila) {
            System.out.print(pilaOperaciones.pop() + " = " + elemento);
            pilaOperaciones.push(elemento);
          }

          if (valJerarquiaCola > valJerarquiaPila) {
            pilaOperaciones.push(elemento);
          }

          if (valJerarquiaCola < valJerarquiaPila) {
            while (!pilaOperaciones.empty()) {
              System.out.print(pilaOperaciones.pop());
            }
            pilaOperaciones.push(elemento);
          }

        } 
      }else{
        if (!elemento.equals(")") && !elemento.equals("(")) {
          System.out.print(elemento);
        }
      }
      if (elemento.equals(")")) {
        while (!pilaOperaciones.empty()) {
          System.out.print(pilaOperaciones.pop());
        }
      }
      //System.out.println(pilaOperaciones.peek());
      // else if (elemento.equals(")")) {
      //   while (pilaOperaciones.peek().equals("(")) {
      //     expPos.offer(pilaOperaciones.pop());
      //   }
      //   pilaOperaciones.pop();
      // }
    }
  }

  public void separarExpresion(String exp) {
    String numero = "";
    String operaciones = "()+-*/p%";
    String cNumeros = "0123456789.,";
    boolean termino = true;
    
    for (int i = 0; i < exp.length(); i++) {
      char c = exp.charAt(i);
      
      if (operaciones.contains(c + "")) {
        if (termino == false) {
          colaExpresion.offer(numero);
          termino = true;
          numero = "";
        }
        colaExpresion.offer(c+"");
      } else if (cNumeros.contains(c + "")) {
        if (termino && ".,".contains(c + "")){
          numero += "0";
        }
        numero += c;
        numero = numero.replace(',', '.');
        termino = false;
      }
    }
    if (termino == false) {
      colaExpresion.offer(numero);
      termino = true;
      numero = "";
    }
  }

}