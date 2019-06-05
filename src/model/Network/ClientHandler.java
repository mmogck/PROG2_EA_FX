package model.Network;

import java.net.Socket;

public class ClientHandler extends Thread
{
    private Socket socket;
    public ClientHandler(Socket clientSocket)
    {
        this.socket = clientSocket;
    }

    public void run()
    {

    }
}
