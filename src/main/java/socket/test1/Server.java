package socket.test1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;

    private ServerSocket serverSocket;
    Server(int port){
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        new Thread(){
            @Override
            public void run() {
                System.out.println("服务端启动成功...");
                doStart();
            }
        }.start();
    }

    private void doStart(){
        for(;;){
            try {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
