package 三个售票窗口同时出售20张票;

public class PersonA extends Thread {
	Bank bank;

	public PersonA(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void run() {

		while(true){
			bank.Counter(300);
		}
	}

}
