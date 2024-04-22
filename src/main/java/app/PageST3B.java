package app;

import java.util.ArrayList;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PageST3B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3B.html";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countryList = jdbc.getCountryList();

        String html = "<html>";
        html += "<head>";
        html += "<title>Similarity Search</title>";
        html += "<link rel='stylesheet' type='text/css' href='common.css' />";
        html += "</head>";

        html += "<body>";

        html += "<div class='topnav'>";
        html += "<a href='/'>Homepage</a>";
        html += "<a href='mission.html'>Mission Statement</a>";
        html += "<a href='page2A.html'>Country/Global Data</a>";
        html += "<a href='page2B.html'>City/State Data</a>";
        html += "<a href='page3A.html'>Temperature Comparison</a>";
        html += "<a href='page3B.html'>Similarity Search</a>";
        
        html = html + """
                <a href='/' class='logo-link'>
                    <img src='ClimateTracker.png' alt='ClimateTracker logo' height='90'>
                </a>    
        """;
        html += "</div>";

        html += "<div class='header'>";
        html += "<h1>Similarity Search</h1>";
        html += "</div>";

        html += "<h2>Similarity Filters:</h2>";
        html += "<div class='datacontainer'>";


        html += "<form method='post' action='/page3B.html'>";

        html += "<div class='sortcontainer1'>";
        html += "<div id='id_data_sort'>";
        html += "<label><input type='radio' name='data_sort1' value='1'><span>Temperature</span></label>";
        html += "<label><input type='radio' name='data_sort1' value='2'><span>Population (Country)</span></label>";
        html += "<label><input type='radio' name='data_sort1' value='3'><span>Temperature and Population (Country)</span></label>";
        html += "</div>";
        html += "</div>";



        html += "<div class='sortcontainer1'>";
        html += "<div id='id_data_sort'>";
        html += "<label><input type='radio' name='data_sort2' value='1'><span>Absolute Values</span></label>";
        html += "<label><input type='radio' name='data_sort2' value='2'><span>Relative Change</span></label>";
        html += "</div>";
        html += "</div>";


        html += "<div class='sortcontainer1'>";
        html += "<input type='text' class='search-input' placeholder='Number of Results...''>";
        html += "</div>";

        html += "<div class='search-container'>";
        html += "<div id='id_data_sort'>";
        html += "<label><input type='radio' name='data_sort3' value='1'><span>Country</span></label>";
        html += "<label><input type='radio' name='data_sort3' value='2'><span>State</span></label>";
        html += "<label><input type='radio' name='data_sort3' value='3'><span>City</span></label>";
        html += "</div>";
        html += "</div>";

        html += "<div class='search-container'>";
        html += "<div class='form-container'>";
        html += "<div class='form-group'>";
        html += "<label for='country'>Country:</label>";
        html += "<select name='country' id='country'>";
        html += "<option value='' selected disabled class='italic'>Select Country</option>";

        for (String country : countryList) {
            html += "<option value='" + country + "'>" + country + "</option>";
        }

        html += "</select>";
        html += "</div>";

        html += "<div class='form-group2'>";
        html += "<label for='startYear'>Starting Year:</label>";
        html += "<select name='startYear' id='startYear'>";
        html += "<option value='' selected disabled class='italic'>Select Start Year</option>";

        for (int year = 1750; year <= 2013; year++) {
            html += "<option value='" + year + "'>" + year + "</option>";
        }
        html += "</select>";
        html += "</div>";


        html += "<div class='form-group2'>";
        html += "<input type='text' class='search-input' placeholder='Time Period...''>";
        html += "</div>";

        html += "<div class='form-group'>";
        html += "<div class='submit-container'>";
        html += "<input type='submit' value='Submit' class='submit-button'>";
        html += "</div>";
        html += "</div>";

        html += "</form>";
        html += "</div>";
        html += "</div>";

        html += "</div>";



        //GET VALUES FROM FORMS

    if (context.formParamMap().containsKey("cityORstate_button")) {

        String cityORstate_button = context.formParam("cityORstate_button");
       
        String country = context.formParam("country");
        
        String startYearParam = context.formParam("startYear");
        int startYear;
        if (startYearParam != null) {
            startYear = Integer.parseInt(startYearParam);
        } else {
            startYear = 1750;
}

        String endYearParam = context.formParam("endYear");
        int endYear;
        if (endYearParam != null) {
            endYear = Integer.parseInt(endYearParam);
        } else {
            endYear = 2013;
        }

        
        

        if (cityORstate_button != null) {
            int buttonValue = Integer.parseInt(cityORstate_button);
            if (buttonValue == 2) {
                html += outputCity(country, startYear, endYear);
            } else if (buttonValue == 1) {
               html += outputState(country, startYear, endYear);
            } else {
                html += "<h2>Please Sort by City or State</h2>";
            }
        } else {
            html += "<h2>Please Sort by City or State</h2>";
        }
    }

        
    
    html += "<div class='footer'>";
        html += "<p>COSC2803 - Group 179 - ClimateTracker</p>";
        html += "</div>";

        html += "</body></html>";

        context.html(html);

        

    }



    public String outputState (String country, int startYear, int endYear) {
    String html = "";

    // HTML OUTPUT HERE(TABLE / LIST)

    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<State> states = jdbc.getStateData(country, startYear, endYear);
    html += "<div class='table-title'>";
    html += "<h2>Temperature of States in " + country + "</h2>";
    html += "</div>";
    html += "<div class='table-container'>";
    html += "<table>";
    html += "<thead>";
    html += "<tr>";
    html += "<th>State</th>";
    html += "<th>Average Temperature</th>";
    html += "<th>Minimum Temperature</th>";
    html += "<th>Maximum Temperature</th>";
    html += "<th>Year</th>";
    html += "</tr>";
    html += "</thead>";
    html += "<tbody>";

    for (State state : states) {
        html += "<tr>";
        html += "<td>" + state.stateName + "</td>";
        html += "<td>" + state.avgTemp + "</td>";
        html += "<td>" + state.minTemp + "</td>";
        html += "<td>" + state.maxTemp + "</td>";
        html += "<td>" + state.year + "</td>";
        html += "</tr>";
    }

    html += "</tbody>";
    html += "</table>";
    html += "</div>";

    return html;
    }

    public String outputCity(String country, int startYear, int endYear) {
    String html = "";

    // HTML OUTPUT HERE(TABLE / LIST)

    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<City> cities = jdbc.getCityData(country, startYear, endYear);
    html += "<div class='table-title'>";
    html += "<h2>Temperature of Cities in " + country + "</h2>";
    html += "</div>";
    html += "<div class='table-container'>";
    html += "<table>";
    html += "<thead>";
    html += "<tr>";
    html += "<th>City</th>";
    html += "<th>Average Temperature</th>";
    html += "<th>Minimum Temperature</th>";
    html += "<th>Maximum Temperature</th>";
    html += "<th>Year</th>";
    html += "</tr>";
    html += "</thead>";
    html += "<tbody>";

    for (City city : cities) {
        html += "<tr>";
        html += "<td>" + city.cityName + "</td>";
        html += "<td>" + city.avgTemp + "</td>";
        html += "<td>" + city.minTemp + "</td>";
        html += "<td>" + city.maxTemp + "</td>";
        html += "<td>" + city.year + "</td>";
        html += "</tr>";
    }

    html += "</tbody>";
    html += "</table>";
    html += "</div>";

    return html;

    }
    
}

