package 龟兔赛跑;

public abstract class Animal extends Thread{

	public double length=20;//比赛的长度  
    
    public abstract void runing();//抽象方法需要子类实现  
      
    //在父类重写run方法，在子类只要重写running方法就可以了  
    @Override  
    public void run() {  
        super.run();  
        while (length>0) {  
             runing();  
        }  
    }  
      
    //在需要回调数据的地方（两个子类需要），声明一个接口  
    public static interface Calltoback{  
        public void win();  
    }  
      
    //2.创建接口对象  
    public Calltoback calltoback;  
}
