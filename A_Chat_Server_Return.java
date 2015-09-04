package chatbox;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class A_Chat_Server_Return implements Runnable {
    //globals
    Socket SOCK;
    private Scanner INPUT;
    private PrintWriter OUT;
    String MESSAGE="";
    
    public A_Chat_Server_Return(Socket X)
    {
        this.SOCK=X;
    }
    public void CheckConnection() throws IOException
    {
        if(!SOCK.isConnected())//removing that socket if not connected
        {
             for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
             {
                 if(A_Chat_Server.ConnectionArray.get(i-1)==SOCK)
                     A_Chat_Server.ConnectionArray.remove(i-1);
             }
             //telling all other client
             for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
             {
                 Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
                 PrintWriter TEMP_OUT=new PrintWriter(TEMP_SOCK.getOutputStream());
                 TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName()+"disconnected");
                 TEMP_OUT.flush();
                 System.out.println(TEMP_SOCK.getLocalAddress().getHostName()+"disconnected");
             }
        }
    }
    
    public void run()
    {
        try
        {
            try
            {
                INPUT=new Scanner(SOCK.getInputStream());
                OUT=new PrintWriter(SOCK.getOutputStream());
                while(true)
                {
                    CheckConnection();
                    if(!INPUT.hasNext())
                        return;
                    MESSAGE=INPUT.nextLine();
                    System.out.println("client said"+MESSAGE);
                    for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)//on each socket sending message
                    {
                        Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
                        PrintWriter TEMP_OUT=new PrintWriter(TEMP_SOCK.getOutputStream());
                        TEMP_OUT.println(MESSAGE);
                        TEMP_OUT.flush();
                        System.out.println("sendtxt"+TEMP_SOCK.getLocalAddress().getHostName());
                    }         
                
                }
            }
            finally
            {
                SOCK.close();
            }
        }
        catch(Exception X)
        {
            System.out.println(X);
        }
    
    }
    
    
}
