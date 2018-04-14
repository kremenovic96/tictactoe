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
        out.println("Welcome to tic tac toe");
        try{
            for(String line = in.readLine(); line != null; line = in.readLine()){
                String output = handleCommand(line);
                if(output != null){
                    if(output.equals("X") || output.equals("O")){
                        out.println("WINNER: " + output);
                        serverSocket.close();
                        in.close();
                        out.close();
                    }
                    else if(output.equals("BYE")){
                        out.println(output);
                        serverSocket.close();
                        in.close();
                        out.close();
                    }
                    else {
                        out.println(output);
                    }
                }
            }
        }
        finally {
            in.close();
            out.close();
        }
    }

    private String handleCommand(String input) throws IOException{
        String[] tokens = input.split(" ");
        System.out.println(input);
        if(tokens[0].equals("look")){
            return b.toString();
        }
        if(tokens[0].equals("bye")){
            return "BYE";
        }

        if(tokens[0].equals("x")){
            int xCoord = Integer.parseInt(tokens[1]);
            int yCoord = Integer.parseInt(tokens[2]);
            return b.putX(xCoord, yCoord);
        }
        if(tokens[0].equals("o")){
            int xCoord = Integer.parseInt(tokens[1]);
            int yCoord = Integer.parseInt(tokens[2]);
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
        TicTacToeServer server = new TicTacToeServer(5555);
        server.serve();

    }
}

