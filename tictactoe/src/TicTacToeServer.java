import game.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeServer {

    private static final int DEFAULT_PORT = 4444;
    private static final int DEFAILT_SIZE = 3;
    private final ServerSocket serverSocket;
    private static Board b;
    public TicTacToeServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }

    public void serve() throws IOException{
        while(true){
            final Socket connection = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{handleRequest(connection);}
                    catch (IOException e){e.printStackTrace();}
                    finally {
                        try {
                            connection.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }
    }

    private void handleRequest(Socket socket) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Welcome");
        try{
            for(String line = in.readLine(); line != null; line = in.readLine()){
                String output = handleCommand(line);
                if(output != null){
                    out.println(output);
                }
            }
        }
        finally {
            in.close();
            out.close();
        }
    }

    private String handleCommand(String input){
        String[] tokens = input.split(" ");

        if(tokens[0].equals("look")){
            return b.toString();
        }
        int xCoord = Integer.parseInt(tokens[1]);
        int yCoord = Integer.parseInt(tokens[2]);

        if(tokens[0].equals("x")){
           return b.putX(xCoord, yCoord);
        }
        if(tokens[0].equals("o")){
            return b.putO(xCoord, yCoord);
        }

        throw  new UnsupportedOperationException();
    }

    public static void main(String[] args){
        int port = DEFAULT_PORT;
        int sizeX = 3;
        int sizeY = 3;
        try{runServer(sizeX, sizeY, port);}
        catch (IOException e){e.printStackTrace();}
    }

    public static void runServer(int sizeX, int sizeY, int port) throws IOException{
        b = new Board(sizeX, sizeY);
        TicTacToeServer server = new TicTacToeServer(4458);
        server.serve();

    }
}

