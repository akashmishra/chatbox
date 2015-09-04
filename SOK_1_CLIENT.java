package chatbox;

import java.io.*;
import java.net.*;
public class SOK_1_CLIENT 
{

     public static void main(String[] args) throws Exception
     {
        SOK_1_CLIENT CLIENT=new SOK_1_CLIENT();
        CLIENT.run();
     }
     public void run() throws Exception
     {
       Socket SOCK=new Socket("localhost",444);//Creates a stream socket and connects it to the specified port number 
       //on the named host.
       //http://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
       
       PrintStream PS=new PrintStream (SOCK.getOutputStream());
       PS.println("hi server i am sending something");
       
       InputStreamReader IR=new InputStreamReader(SOCK.getInputStream());
       BufferedReader BR=new BufferedReader(IR);
       
       String msg=BR.readLine();
       System.out.println(msg);
     }
     
}
