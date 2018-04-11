package 龟兔赛跑;

public class Rabbit extends Animal {

	public Rabbit() {
		setName("兔子");
	}

	@Override
	public void runing() {
		// 跑的距离
		double dis = 0.5;
		length -= dis;// 跑完后距离减少
		if (length <= 0) {
			length = 0;
			System.out.println("兔子获得了胜利");
			// 给回调对象赋值，让乌龟不要再跑了
			if (calltoback != null) {
				calltoback.win();
			}
		}
	}
}
