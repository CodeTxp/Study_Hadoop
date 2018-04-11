package Tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends Thread
{
   public static void main(String [] args) throws IOException
   {
	 //创建一个ServerSocket，用于监听客户端Socket连接请求
       ServerSocket ss = new ServerSocket(8888);
       System.out.println("server start");
       //采用循环方式监听客户端的请求
       while(true)
       {
           //侦听并接受到此套接字的连接。此方法在连接传入之前一直阻塞。
           Socket socket = ss.accept();
           OutputStream os = socket.getOutputStream();
           PrintStream ps = new PrintStream(os);
           ps.print("您好，您收到了来自服务端的中秋祝福");
           ps.close();
           os.close();
           socket.close();
       }
   }
}
