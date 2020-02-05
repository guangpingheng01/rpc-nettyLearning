package socket.test1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    private static final String host = "127.0.0.1";
    private static final int port = 8999;
    public static void main(String[] args) {

        try(Socket socket = new Socket(host,port);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();)
        {
            String word = "hello world";
            byte[] bytes = new byte[1024 * 1024];
            int len = -1;
            for(;;){
                System.out.println("客户端发送 》》" + word);
                outputStream.write(word.getBytes());
                while ((len = inputStream.read(bytes)) != -1){
                    String resp = new String(bytes,0,len);
                    System.out.println(">>>>> resp from server :" + resp);
                }
                sleep(3000);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端异常...");
        }
    }

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
