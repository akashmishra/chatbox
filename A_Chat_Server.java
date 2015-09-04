package chatbox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class A_Chat_Server {
    
    //global variable
    //they are used when a message is send by a client and we have to show it to all other client
    public static ArrayList<Socket> ConnectionArray=new ArrayList<Socket>();
    public static ArrayList<String> CurrentUsers =new ArrayList<String>();
    
    public static void main(String[] args) 
    {
      try
      {   //A socket is associated with a port and there can be multiple sockets associated with a port
          //http://programmers.stackexchange.com/questions/171734/difference-between-a-socket-and-a-port
          final int PORT=444;
          ServerSocket SERVER=new ServerSocket(PORT);//creating socket on port no 444 on server side
          System.out.println("waiting for client");
          
          while(true)
          {
              Socket SOCK=SERVER.accept();//whenever a new client req comes on this 444 a new socket is created and added to vector
              ConnectionArray.add(SOCK);
              
              System.out.println("client connected from"+SOCK.getLocalAddress().getHostName());
              //http://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
              //Gets the local address to which the socket is bound.
              
              AddUserName(SOCK);
              
              A_Chat_Server_Return CHAT =new  A_Chat_Server_Return(SOCK);
              Thread X=new Thread(CHAT);
              X.start();//for listening client req a new thread starts running
                      
          
          }
      
      }
      catch(Exception X){ System.out.println(X);}
    }
    
    public static void AddUserName(Socket X) throws Exception
    {
        Scanner INPUT=new Scanner(X.getInputStream());
        String UserName=INPUT.nextLine();
        CurrentUsers.add(UserName);
       // when new client added until now only server knknows it and now using for loop all client came to know it
        for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
        {
            Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
            PrintWriter OUT=new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("#?!"+CurrentUsers);
            OUT.flush();
        }
    
    }
}
