package Tcp1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server2 extends Thread{
	// 用来存放连接上的用户的socket对象的值  
    List<Socket> list = new ArrayList<>();  
  
    // 定义服务器接口ServerSocket  
    ServerSocket server = null;  
  
    // 定义一个服务器，定义端口  
    public Server2(int port) {  
        try {  
            server = new ServerSocket(port);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 发送消息的线程  
    @Override  
    public void run() {  
        super.run();  
        try {  
  
            while (true) {  
                // 建立socket接口，accept方法是一个阻塞进程,等到有用户连接才往下走  
                // 定义Socket类  
                Socket socket = server.accept();  
                OutputStream os=socket.getOutputStream();
                os.write("好好".getBytes());
                // 在服务器显示连接的上的电脑、  
                String message = socket.getInetAddress().getHostAddress().toString();  
                System.out.println(message+"连接上了");  
                // 向用户发送消息  
                SendMessageToAllUser(message);  
                // 把连接上的用户添加到集合；里面去  
                list.add(socket);  
                //开始新连接用户的线程，用于该可以一直读取数据  
                new readerThread(socket).start();;  
            }  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 把消息发送给每一个用户，涉及到写的操作OutStream  
    private void SendMessageToAllUser(String message) {  
        // 拿到每一个用户的socket对象，对其进行写入数据  
        for (Socket socket : list) {  
            // 判读之前保存的连接是否还在  
            if (socket != null && socket.isConnected()) {  
                try {  
                    OutputStream os = socket.getOutputStream();  
                    os.write(message.getBytes());  
                    os.flush();// 刷新一下写入的数据，很有必要  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    // 读取客户端的信息  
    class readerThread extends Thread {  
        InputStream is = null;  
  
        // 这里传入一个socket对象，因为每一个用户都要用一个不同的线程存放的socket对象来进行一直读取数据  
        public readerThread(Socket socket) {  
            try {  
                // 获取输入流  
                is = socket.getInputStream();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
  
        @Override  
        public void run() {  
            super.run();  
            try {  
                int len = 0;  
                byte[] buf = new byte[1024];  
                while ((len = is.read(buf)) != -1) {  
                    // 把读取到的数据发送给其他用户  
                    SendMessageToAllUser(new String(buf, 0, len));  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
