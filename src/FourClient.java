import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FourClient {

    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public FourClient(String serverAddress) throws Exception {

        System.out.println("KLient i metod");
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        GameBoard g = new GameBoard();
        g.drawGameBoard();
    }

    /**
     * The main thread of the client will listen for messages
     * from the server. The first message will be a "WELCOME"
     * message in which we receive our mark. Then we go into a
     * loop listening for "VALID_MOVE", "OPPONENT_MOVED", "VICTORY",
     * "DEFEAT", "TIE", "OPPONENT_QUIT or "MESSAGE" messages,
     * and handling each message appropriately. The "VICTORY",
     * "DEFEAT" and "TIE" ask the user whether or not to play
     * another game. If the answer is no, the loop is exited and
     * the server is sent a "QUIT" message. If an OPPONENT_QUIT
     * message is recevied then the loop will exit and the server
     * will be sent a "QUIT" message also.
     */
    public void play() throws Exception {
        String response;
        try {
            response = in.readLine();
            if (response.startsWith("WELCOME")) {
                System.out.println("Startar spelet");
            }
            while (true) {
                response = in.readLine();
            }
        }
        finally {
            socket.close();
        }
    }



    public static void main(String[] args) throws Exception {
        System.out.println("klient");
        while (true) {
            String serverAddress = "127.0.0.1";
            FourClient client = new FourClient(serverAddress);
            client.play();
        }
    }

}