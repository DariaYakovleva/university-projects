package ru.ifmo.neerc.java.AA;
import ch.melnikov.test.*;   //куча вложенных папочек
import static ru.ifmo.neerc.java.AA.PI;

public class AA extends B {
    ch.melnikov.test.AA a;
    ru.ifmo.neerc.java.AA a2;
    AA c;                 //заюзаетс€ тот, который без звездочки, т. е. указан €вно

    public int x1;
    private int x2;
    protected int x3;
    /*package local*/int x3; //виден только в своем пакете или наследникам в другом

    //анонимный класс
    //статик пол€ общие дл€ всех экземпл€ров класса. статик методы не получают экземпл€р класса.
    final int x = 0;
    void run() {
        new If() {
             public void run() {
            }
        }.run();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println(x); //если   x внешн€€ и final, то мы можем ее использовать
            }
        })
    }

     //вложенные классы
// в B вложен A. «начит у каждого экземпл€ра B есть свой A. Ќапример, если выводит в A x из B, то, 
//дл€ разных B и, соответственно, разных x, будет разный вывод.

public class B extnds AA {
	static class C {
		int y;
		void f() {
			System.err.println(x);
		}
	}
	ru.ifmo.neerc.java.B.C c = new C();
	int x;
	void r() {
		B b = new B(1);
		b.C.y = 10;
	}

	
	public B(nt x) {
		this.x = x;
	}


	int  x = 13;
        class A {
//тут не может быть статик полей
        	int x = 10;
		void a() {
			System.out.println(x);    //10
			System.err.println(this.x);   //10
			System.out.println(B.this.x);    // 13
			System.err.println(((AA)B.this).x);   //11
			System.err.println(B.super.x);            //11		
		}
        }

	//у ј предок B. A всегда  создаетс€ из B

	void run() {
		A a = new A();

	}



 
}