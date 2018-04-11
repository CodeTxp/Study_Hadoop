package 三个售票窗口同时出售20张票;

public class Station extends Thread{

	private String name;
	private static int tick=20;
	public Station(String name) {
		super(name);
		//this.name = name;
	}
	private static Object ob="aa";//任意的
	
	@Override
	public void run() {
		while(tick>0){
			synchronized (ob) {
				if(tick>0){
					System.err.println(getName()+"卖出了第："+tick+"张票");
					tick--;
				}else{
					System.err.println("票卖完了");
				}
			}
			try{
				sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	
}
