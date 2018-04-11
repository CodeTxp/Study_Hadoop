package 三个售票窗口同时出售20张票;

public class PersonB extends Thread {
	Bank bank;

	public PersonB(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void run() {

		while(true){
			bank.ATM(100);
		}
	}
}
