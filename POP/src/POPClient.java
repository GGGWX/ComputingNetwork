import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class POPClient {
    private Socket socket;
    private Scanner socketReader;
    private PrintWriter socketWriter;

    public POPClient(String server, int port) throws Exception {
        try {
            socket = new Socket(server, port);
            socketReader = new Scanner(socket.getInputStream());
            socketWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new IOException("the url of the server is wrong");
        }
    }

    //send command to the server
    public void sendCommand(String command) {
        socketWriter.println(command);
    }

    //get answer from the server
    public String getAnswer() {
        return socketReader.nextLine();
    }

    //login the mailbox
    public void login(String userName, String password) throws Exception{
        sendCommand("USER " + userName);
        //if username is wrong,return false
        if (!getAnswer().startsWith("+OK")) {
            throw new Exception("Wrong user name");
        }
        //if the username is right then send the password
        sendCommand("PASS " + password);
        if (!getAnswer().startsWith("+OK")) {
            throw new Exception("Wrong password");
        }
    }

    //the content of the mail finished with a "."
    public String getContent() {
        String content = socketReader.nextLine();
        String line = socketReader.nextLine();
        while (!line.equals(".")) {
            content += line + "\r\n";//don't forget \r\n
            line = socketReader.nextLine();
        }
        return content;
    }
}