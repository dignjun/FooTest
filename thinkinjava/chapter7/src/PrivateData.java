/**
 * private关键字修饰的方法被"重写"
 * Created by DINGJUN on 2018/6/2.
 */
class Data{
    private void f() {
        System.out.println("father method");
    }

    public static void main(String[] args) {
        Data data = new Data();
        data.f();
    }
}
public class PrivateData extends Data{
    void f() {
        System.out.println("son method");
    }

    public static void main(String[] args) {
        Data father = new Data();
        PrivateData son = new PrivateData();
        son.f();
    }
}
