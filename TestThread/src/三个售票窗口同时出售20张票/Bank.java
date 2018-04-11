package 三个售票窗口同时出售20张票;

public class Bank {

	// 假定账户的钱是一定的
	static int money = 1000;

	// 在柜台取钱
	public void Counter(int money) {
		synchronized (this) {
			try{
				while(Bank.money>=money){
					Bank.money-=money;
					///this.notifyAll();
					System.out.println("A在柜台取走了:" + money + "还剩下" + Bank.money);
					this.wait(100);
					if(Bank.money<money){
						this.wait();
					}
				}
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			
		}
		
	}

	public void ATM(int money) {
		synchronized (this) {
			try {
				while(Bank.money>=money){
					Bank.money-=money;
					//this.notifyAll();
					System.out.println("B在ATM取走了：" + money + "还剩下：" + Bank.money);
					this.wait(100);
					if(Bank.money<money){
						this.wait();
					}
				}	
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		}
	}
}
