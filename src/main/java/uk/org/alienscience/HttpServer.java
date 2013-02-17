package uk.org.alienscience;

import uk.org.alienscience.routes.DecisionTree;
import uk.org.alienscience.threadpool.ThreadPool;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A HTTP Server
 */
public class HttpServer {

    /**
     * Parameters that configure the setup of the HTTP server
     */
    public static class Parameters {
        // Default values
        private int port = 8090;
        private int maxThreads = 32;

        public Parameters setPort(int port) {
            this.port = port;
            return this;
        }

        public Parameters setMaxThreads(int maxThreads) {
            this.maxThreads = maxThreads;
            return this;
        }
    }

    //---------------------------------------------------

    final TcpServer server;
    final HttpRoutes routes;

    /**
     * Construct a default HttpServer
     */
    public HttpServer() {
        this(new Parameters());
    }

    /**
     * Construct a HttpServer with customized parameters
     * @param parameters Configuration
     */
    public HttpServer(Parameters parameters) {
        // TODO: create non-blocking executor and pass to HttpConnectionFactory
    	// TODO: dependency injection
        this.routes = new DecisionTree();
        this.server = new TcpServer(
                parameters.port,
                new HttpConnectionFactory(routes),
                ThreadPool.newCachedThreadPool(parameters.maxThreads));
    }

    /**
     * Start a HttpServer
     */
    public void start() throws IOException {
        // Start a timer to update header dates
        startHeaderDateTimer();
        
        // Start the server
        server.start();
    }

    /**
     * Add a route to the HttpServer
     */
    public void route(String url, HttpHandler handler) {
        routes.add(url, handler);
    }

    /**
     * Add a route to the HttpServer that is handled without blocking
     */
    public void route(String url, SocketEventHandler handler) {
        routes.add(url, handler);
    }
    
    // Start a timer to update header dates
    private static void startHeaderDateTimer() {
       // Initialise the header date
       HttpHeader.updateDate();
       
       // Update the header date every 500ms
       Timer timer = new Timer();
       timer.scheduleAtFixedRate(new TimerTask() {
           @Override
           public void run() {
               HttpHeader.updateDate();
           }
       }, 500, 500);
    }
}
