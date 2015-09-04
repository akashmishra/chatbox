package chatbox;

import java.io.*;
import java.net.*;
public class SOK_1_SERVER 
{

     public static void main(String[] args) throws Exception
     {
        SOK_1_SERVER SERVER=new SOK_1_SERVER();
        SERVER.run();
     }
     public void run() throws Exception
     {
         ServerSocket SRVSOCK = new ServerSocket(444);//The java.net.ServerSocket class is used by server applications 
       //  to obtain a port and listen for client requests
        /*
         public ServerSocket(int port) throws IOException
         Attempts to create a server socket bound to the specified port. An exception occurs if the port is already bound by another applicatio
         */
         Socket SOCK =SRVSOCK.accept();
         /*
         The accept method waits until a client starts up and requests a connection on the host and port of this server. 
         (Let's assume that you
         ran the server program KnockKnockServer on the computer named knockknockserver.example.com.) 
         In this example, the server is running on the port number
         specified by the first command-line argument. When a connection is requested and successfully established, the 
         accept method returns a new
         Socket object which is bound to the same local port and has its remote address and remote port set to 
         that of the client. The server
         can communicate with the client over this new Socket and continue to listen for client 
         connection requests on the original ServerSocket
         */
         
         System.out.println(SOCK.getLocalAddress().getHostName()+" sock");
         InputStreamReader IR=new InputStreamReader(SOCK.getInputStream());
         /*
         getinputstream()
         Returns:
a        n input stream for reading bytes from this socket
         */
         /*
          inputstreamreader
         http://docs.oracle.com/javase/7/docs/api/java/io/InputStreamReader.html
         */
         BufferedReader BR=new BufferedReader(IR);//http://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html
         
         String message=BR.readLine();
         System.out.println(message);//going to print on consoleside
         
         if(message!=null)
         {
             PrintStream PS =new PrintStream(SOCK.getOutputStream());
             PS.println("message received");//print on client computer
         }
         }
         
     
     }
     
