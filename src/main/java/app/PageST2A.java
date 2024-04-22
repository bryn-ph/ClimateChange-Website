package app;

import java.util.ArrayList;
import java.util.List;
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

 public class PageST2A implements Handler {

     // URL of this page relative to http://localhost:7001/
     public static final String URL = "/page2A.html";
     private JDBCConnection jdbcconnection;
     
     public PageST2A(JDBCConnection connection) {
         this.jdbcconnection = connection;
     }
     
     @Override
     public void handle(Context context) throws Exception {
         String dataSort1 = context.queryParam("data_sort1", "default_value_1");
         String dataSort2 = context.queryParam("data_sort2", "default_value_2");
         Integer startYear = context.queryParam("startYear", Integer.class).getOrNull();
         Integer endYear = context.queryParam("endYear", Integer.class).getOrNull();
 
         if (startYear == null) {
             startYear = 1750; // default value
         }
         if (endYear == null) {
             endYear = 1750; // default value
         }
 
         List<Data> dataList = jdbcconnection.getData(dataSort1, dataSort2, startYear, endYear);
 
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 2.1</title>";

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

        html += "</div>";

        // Add header content block
        html = html + """
            <div class='header'>
                <h2>Country/Global Data</h2>
            </div>
            <h2>Sort by:</h2>
        """;
//Main selection box

        html += "<div class='datacontainer'>";

        html += "<div class='sortcontainer1'>";
        html += "<div id='id_data_sort'>";
        html += "<label><input type='radio' name='data_sort1' value='1'><span>Temperature</span></label>";
        html += "<label><input type='radio' name='data_sort1' value='2'><span>Population</span></label>";
        html += "</div>";
        html += "</div>";
        
        html += "<div class='sortcontainer1'>";
        html += "<div id='id_data_sort'>";
        html += "<label><input type='radio' name='data_sort2' value='1'><span>By country</span></label>";
        html += "<label><input type='radio' name='data_sort2' value='2'><span>Global</span></label>";
        html += "</div>";
        html += "</div>";
        
        html += "<div class='sortcontainer2'>";
        html += "<div class='form-container'>";
        html += "<form method='get' action='/page2A.html'>";
        
        html += "<div class='form-group'>";
        html += "<label for='startYear'>Starting Year:</label>";
        html += "<select name='startYear' id='startYear'>";
        for (int year = 1750; year <= 2013; year++) {
            html += "<option value='" + year + "'>" + year + "</option>";
        }
        html += "</select>";
        html += "</div>";
        
        html += "<div class='form-group'>";
        html += "<label for='endYear'>Ending Year:</label>";
        html += "<select name='endYear' id='endYear'>";
        for (int year = 1750; year <= 2013; year++) {
            html += "<option value='" + year + "'>" + year + "</option>";
        }
        html += "</select>";
        html += "</div>";
        
        html += "<div class='form-group'>";
        html += "<div class='submit-container'>";
        html += "<input type='submit' value='Submit' class='submit-button'>";
        html += "</div>";
        html += "</div>";
        
        html += "</form>";
        html += "</div>";
        html += "</div>";
        html += "<div class='datacontainer'>";
        if (dataList.isEmpty()) {
            html += "<p>No data found for selected parameters.</p>";
        } else {
            for (Data data : dataList) {
                html += "<p>" + data.toString() + "</p>";
            }
        }
        html += "</div>";
        
        html += "</div>";
        
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
