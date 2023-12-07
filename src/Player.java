import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Player extends Thread {
    String mark;
    Player opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    public Player(Socket socket, String mark) {
        this.socket = socket;
        this.mark = mark;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String mess){
        output.println(mess);
    }

    public String receive()  {
        try {
            return input.readLine();
        } catch (IOException e) {
            System.out.println("Player "+mark+" could not receive data " + e);
            throw new RuntimeException(e);
        }
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }


}
