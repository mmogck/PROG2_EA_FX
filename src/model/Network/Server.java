package model.Network;

import control.gamemanagement.ThreadController;
import resources.gameconstants.INetworkConstants;
import control.IOController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread
{
    private int server_port;
    private int max_connections_allowed;
    private int counter;

    Socket client;
    ServerSocket serverSocket;

    ThreadController tControl;

    //Assume that tControl is already an instance
    public Server(ThreadController tControl)
    {
        this.server_port = INetworkConstants.RECV_PORT;
        this.max_connections_allowed = INetworkConstants.MAX_CONNECTIONS;
        this.counter = 0;

        this.tControl = tControl;
        try
        {
            this.serverSocket = new ServerSocket(server_port);
        }
        catch (IOException e)
        {
            IOController.printMessage(e.getMessage());
        }
    }

    public void run() {runServer();} //ThreadController needs that to run the server.
    public void interrupt() {stopServer();}

    //Listen for new connections and start a new client thread when a connection is available
    public void runServer()
    {
        for (int i = 0; i < max_connections_allowed; i++)
        {
            try
            {
                client = serverSocket.accept();
            }
            catch (IOException e)
            {
                IOController.printMessage(e.getMessage());
            }
        }

        this.tControl.addThread("ClientThread_"+ counter, new ClientHandler(client));
        this.counter++;
    }

    public void stopServer()
    {
        try
        {
            //Stop all client threads
            for (int i = 0; i < counter; i++)
            {
                this.tControl.removeThread("ClientThread_" + i);
            }
            //Stop server socket
            serverSocket.close();
        }
        catch (IOException e)
        {
            IOController.printMessage(e.getMessage());
        }
    }
}
