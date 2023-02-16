import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleHTTPserve{
    public static void main(String[] args) throws Exception{

        // class in java - this is enough to create a web server in java
        // our server is ready and listening for incoming connection on port 808
        final ServerSocket server = new ServerSocket(8080);
        System.out.print("Listening for connection on port 8080 ...");

        /*
         * This is a blocking method and blocks until a client connects to
         *  the server. As soon as a client connect it returns 
         * the Socket object which can be used to read client request and 
         * send response to client. Once you are done with a client you 
         * should close this socket and get ready to accept the new 
         * incoming connection by calling accept() again. So basically, 
         * our HTTP server should work like this:
        */
        while (true){
            Socket client1 = server.accept();

            // You can read the content of the request using InputStream opened from the client socket
            InputStreamReader isr = new InputStreamReader(client1.getInputStream());

            // It's better to use BufferedReader because the browser will send multiple lines
            BufferedReader reader1 = new BufferedReader(isr);

            String line = reader1.readLine();
            while (!line.isEmpty()){
                System.out.print(line);
                line = reader1.readLine();
            }

            /*Which is today's date. It means our HTTP Server is working properly, 
            it is listening on port 8080, accepting connections, reading requests, 
            and sending responses. By using the try-with-resource statement of Java 7,
             we have also simplified our code, because the socket will automatically be 
             closed by Java once you are done with the response. The only limitation of 
             this server is that it can serve one client at a time.
             */
            
            try (Socket socket1 = server.accept()){
                Date today = new Date();
                String http_response = "HTTP/1.1 200 OK\r\n\r\n"+today;
                socket1.getOutputStream().write(http_response.getBytes("UTF-8"));
            }
        }
        
    }
}