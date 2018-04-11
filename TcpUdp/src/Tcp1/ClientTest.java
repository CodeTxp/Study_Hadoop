package Tcp1;

public class ClientTest {
	public static void main(String[] args) {
		Client client=new Client("localhost", 9999);
		client.start();
	}
}
