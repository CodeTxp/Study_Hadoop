package Tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient{

	public static void main(String [] args) throws UnknownHostException, IOException
	   {
		Socket socket = new Socket("localhost",8888);
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = br.readLine();
        System.out.println(str);
        br.close();
        is.close();
        socket.close();
	   }
}
