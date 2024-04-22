package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Mission Statement</a>
                <a href='page2A.html'>Country/Global Data</a>
                <a href='page2B.html'>City/State Data</a>
                <a href='page3A.html'>Temperature Comparison</a>
                <a href='page3B.html'>Similarity Search</a>
             """;

                
        html = html + """
                <a href='/' class='logo-link'>
                    <img src='ClimateTracker.png' alt='ClimateTracker logo' height='90'>
                </a>    
        """;

        html +=   "</div>";

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>
                    Homepage
                </h1>
            </div>
        """;
        // Add Div for page Content2
        html = html + "<div class='content2'>";
        html = html + "<div class='flexbox1'>";

        // Basic menu for site navigation
        html = html + """
            <button class="button2">Temperature Data</button>
            </div>
            <div class ='flexbox1'>
            <form action='page2A.html'>
            <button class="button1">Country/Global Data</button>
            </form>
            <form action='page3A.html'>
            <button class="button1">City/State Data</button>
            </form>
            </div>
            <div class='flexbox1'>
            <button class="button2">Composite Data</button>
            </div>
            <div class='flexbox1'>
            <form action='page3A.html'>
            <button class="button1">Temperature Comparison</button>
            </form>
            <form action='page3B.html'>
            <button class="button3">Similarity Search</button>
            </form>
            </div>
            """;

        // Additional 

        // Footer
        html = html + """
            <div class='footer'>
                <p>COSC2803 - Group 179 - ClimateTracker</p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
