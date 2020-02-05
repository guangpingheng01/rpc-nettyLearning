package socket.test1;

public class ServerBoot {
    private static int port = 8999;
    public static void main(String[] args) {
         new Server(port).start();
    }
}
