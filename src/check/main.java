package check;
class A { int x = 1; int getX(){ return x; } }
class B extends A { int x = 2; int getX(){ return x; } }

public class main{
    public static void main(String[] args){
        A v = new B();
        System.out.print(v.x + " ");
        System.out.println(v.getX());
    }
}