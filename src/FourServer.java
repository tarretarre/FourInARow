import java.io.IOException;
import java.net.ServerSocket;

public class FourServer {

    public FourServer() {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Four in a row Server is Running");
        try {
            while (true) {
                Player player1 = new Player(listener.accept(), "Player1");
                System.out.println("Player1");
                Player player2 = new Player(listener.accept(), "Player2");
                System.out.println("Player2");
                ServerSideGameThreadLess game = new ServerSideGameThreadLess(player1, player2);
                game.doGame();

            }
        } finally {
            listener.close();
        }
    }
}
