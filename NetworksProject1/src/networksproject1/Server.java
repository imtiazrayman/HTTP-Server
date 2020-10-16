package networksproject1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
    public static void main(String args[]) throws IOException {
        // accept input from th client and then return if to the client
        // we need the socket in the server
        // the socket is different from the client
        boolean connectionStatus = false;
        Scanner scan = new Scanner(System.in);
        int portnumber = scan.nextInt();
        System.out.println("Server has been activated. Connection Status : " + connectionStatus);
        // Server socket is the connection at which port
        ServerSocket serversocket = new ServerSocket(portnumber); // this specified the port number so we know which port the process is actually running
        int i = 1;
        while(true) { //open the connection then get a responce and look for a new connection
            Socket socket = serversocket.accept();// this kind of accespt the request that the client is senign what thwe accept is going to tdo is to accept what is comming in to the server socket // we need a simple socket
            // accept means you want some one to connect to this port Accepts the client
            //waits

            //server socket accepts a conenction and returns a socket from this point on we then use the socket.
            connectionStatus = true;
            System.out.println("Connection Status " + connectionStatus + " " + i++ + " client" + socket.getRemoteSocketAddress()); // not gonna happen till we are connected to the client
            InputStream inputstream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //SEND ME THE REQUEST +
            // WWW.GOOGLE.COM
            //GET REQUEST
            //IF I GET IT OR CANT: 404  NOT FOUND
            String html = "<html>" +
                    "<head>" +
                    "<title>Simple java http</title></head>" +
                    "<h1> This page was " +
                    "served using a simple http server.<body> </body> </html>";
            //


            String crtlf = "\n\r"; // 13 and 10 ascii
            String response = "HTTP/1.1 200 OK" + crtlf
                    + "" + "Content-Length" + html.getBytes().length + crtlf
                    + crtlf
                    + html
                    + crtlf + crtlf;//header

            //Status line has the http version, response code, response message
            //2 special char the character return and the line feed
            //content lenght is size of the message want to send, after the lengfht the character return line feel
            //to signify we are finished send another crlf
            outputStream.write(response.getBytes()); // this is writing to the server

          /*
            PrintWriter printwriter = new PrintWriter(outputStream); // this send out strings
            printwriter.println("HTTP/1.0 200 OK");
            printwriter.println("Content-Length: 12");
            printwriter.println("");
            printwriter.println("Hello World!");
            printwriter.flush();// what it does is it waits till all the data has been sent
            System.out.println("Sent.");
            */

            /*
            inputstream.close();
            outputStream.close();
            socket.close();
            serversocket.close();
            */

            socket.close();// this may take some time to handle a sure
            // it will be slower and slower.
        }

        //Scanner sc = new Scanner(ss.getInputStream()); // In t what this does is accept the number which the client would like to pass, the client has taken the number from the user and now it needs to pass it tot th eserver
        //number = sc = sc.nextInt();
    }
}
