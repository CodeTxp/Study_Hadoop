package Adress;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class Adress {

	public static void main(String[] args) throws Exception {
		/*InetAddress ip;
		//实例化对象
		//ip=InetAddress.getLocalHost();
		URI url=new URI("http://www.baidu.com");
		String localname=ip.getHostName();
		String localIp=ip.getHostAddress();
		System.out.println(localname+" "+localIp);*/
		
		/*URL url = new URL("http://www.baidu.com");
        String file = url.getFile();
        System.out.println(file);
        String host = url.getHost();
        System.out.println(host);
        int port = url.getPort();
        System.out.println(port);
        String query = url.getQuery();
        System.out.println(query);
        String protocol = url.getProtocol();
        System.out.println(protocol);*/
		
		/*URL url = new URL("http://www.baidu.com");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        OutputStream os = new FileOutputStream("c:/data.txt");
        byte[] buffer = new byte[1024];
        int flag = 0;
        while (-1 != (flag = is.read(buffer, 0, buffer.length)))
        {
            os.write(buffer, 0, flag);
        }
        os.close();
        is.close();*/
		InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);
        address = InetAddress.getByName("www.baidu.com");
        System.out.println(address);
	}
}
