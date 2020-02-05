package socket.test1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler {

    private Socket serverSocket;
    private final int MAX_LEN = 1024 * 1024;
    ClientHandler(Socket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void start(){
        new Thread(){
            @Override
            public void run() {
//                super.run();

                try(InputStream in = serverSocket.getInputStream();)
                {
                    for(;;) {
                        byte[] bytes = new byte[MAX_LEN];
                        int len = -1;
                        while ((len = in.read(bytes)) != -1) {
                            String message = new String(bytes, 0, len);
                            System.out.println("服务端接收：" + message);
                            serverSocket.getOutputStream().write(bytes, 0, len);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
