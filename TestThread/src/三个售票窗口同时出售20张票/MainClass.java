package 三个售票窗口同时出售20张票;

public class MainClass {

	public static void main(String[] args) {
		/*Station station1=new Station("窗口1");
		Station station2=new Station("窗口2");
		Station station3=new Station("窗口3");
		
		station1.start();
		station2.start();
		station3.start();*/
		

			Bank bank=new Bank();
			PersonA pa=new PersonA(bank);
			PersonB pb=new PersonB(bank);
			pa.start();
			pb.start();
		
		
	}
}
