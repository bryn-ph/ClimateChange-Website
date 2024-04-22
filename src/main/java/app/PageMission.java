package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PageMission implements Handler {


    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {

        String html = "<html>";

        html = html + "<head>" + 
               "<title>Our Mission</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        html = html + "<body>";


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


        

        html += "<div class='content3'>";

        html = html + """
            <div class='header'>
                <div class='header-container'>
                <h2>Our Mission</h2>
                </div>
            </div>
        """;

        html += "<p>This website is directed towards providing an extensive analysis of land and ocean temperatures as they have fluctuated over the last 260+ years. By utilising information from multiple different countries and regions, we aim to both present an interesting collective of climate change data and inform a wide range of users on the issue as it has advanced throughout history. This site will utilise interactive data visualisations and simple data filtration, allowing all sorts of users to explore key climate change issues and how they relate with specific topics. Some of these may include land/ocean temperatures, population growth, and geographical factors.<br> <br>In terms of use, our website employs a back-end database of accurate, varied climate change data. Using this to inform the website, users can navigate through a clean and simple UI/UX to access and search/filter for information as they need.</p>";
        html += "</div>";




        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Persona> personas = jdbc.getPersonaData();
        ArrayList<Student> students = jdbc.getStudentData();


        

        html += "<div class='content'>";

        html += """
            <div class='header'>
                <div class='header-container'>
                    <h2>Team Members</h2>
                </div>
            </div>
            <div class='students-container'>
        """;
        
        for (Student student : students) {
            html += "<div class='student'>"
                        + "<h2>" + student.getName() + "</h2>"
                        + "<p>" 
                        + student.getID() + " <br><br> "
                        + student.getEmail() + "<br>"
                        + "</p>"
                    + "</div>";
        }
        
        html += "</div></div>";
        
      
        

        html += "<div class='content'>";

        html = html + """
            <div class='header'>
                <div class='header-container'>
                <h2>Personas</h2>
                </div>
            </div>
        """;
        html += "</div>";

        html += "<div class='personaContainer'>";
  
        for (Persona persona : personas) {
        html += "<div class='personaContent'>";
        html += "<img src='" + persona.getImage() + "' alt='persona image' height='300'>";
        html += "<br>";
        html += "<br>";
        html += "<p>" + persona.getName() + " - "
                            + persona.getAttr()
                            + persona.getDscrp()
                    + "<p>" + "</div>";
        }
       
        html += "</div>";

        html = html + """
            <div class='footer'>
                <p>COSC2803 - Group 179 - ClimateTracker</p>
            </div>
        """;

        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
