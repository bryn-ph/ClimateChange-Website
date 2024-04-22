package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/ClimateDatabase02.db";
    // public static final String DATABASE = "jdbc:sqlite:database/climate.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */

    public ArrayList<State> getStateData(String country, int startYear, int endYear) {
    ArrayList<State> states = new ArrayList<State>();

    Connection connection = null;

    try {
        connection = DriverManager.getConnection(DATABASE);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        String query = "SELECT s.State, st.AverageTemperature, st.MinimumTemperature, st.MaximumTemperature, st.Year FROM StateID s JOIN StateTemp st ON s.State_ID = st.State_ID WHERE s.Country = '" + country + "' AND st.Year >= " + startYear + " AND st.Year <= " + endYear;
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            State state = new State();
            state.stateName = results.getString("State");
            state.avgTemp = results.getFloat("AverageTemperature");
            state.minTemp = results.getFloat("MinimumTemperature");
            state.maxTemp = results.getFloat("MaximumTemperature");
            state.year = results.getInt("Year");
            states.add(state);
        }

        statement.close();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return states;
}

     
    public ArrayList<City> getCityData(String country, int startYear, int endYear) {
    ArrayList<City> cities = new ArrayList<City>();
    
    Connection connection = null;
    ArrayList<String> cityData = new ArrayList<>();

    try {
        connection = DriverManager.getConnection(DATABASE);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        String query = "SELECT CityName as City, AvgTemp, MinTemp, MaxTemp, Year FROM CityTemp WHERE CountryName = '" + country + "' AND Year BETWEEN " + startYear + " AND " + endYear;
        ResultSet results = statement.executeQuery(query);

       while (results.next()) {

                City city = new City();

                city.cityName    = results.getString("City");
                city.avgTemp  = results.getFloat("AvgTemp");
                city.minTemp  = results.getFloat("MinTemp");
                city.maxTemp  = results.getFloat("MaxTemp");
                city.year = results.getInt("Year");

                cities.add(city);
            }


        statement.close();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return cities;
}

    public ArrayList<String> getCountryList() {
    Connection connection = null;
    ArrayList<String> countryList = new ArrayList<>();

    try {
        connection = DriverManager.getConnection(DATABASE);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        String query = "SELECT DISTINCT CountryName from CityTemp";
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country = results.getString("CountryName");
            countryList.add(country);
        }

        statement.close();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return countryList;
}

    public ArrayList<Persona> getPersonaData() {
        // Create the ArrayList of LGA objects to return
        ArrayList<Persona> personas = new ArrayList<Persona>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Persona";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name  = results.getString("name");
                String attributes  = results.getString("attributes");
                String description  = results.getString("description");
                String image = results.getString("Image_FilePath");
                // Create a LGA Object
                Persona persona = new Persona(name, attributes, description, image);

                // Add the lga object to the array
                personas.add(persona);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return personas;
    }
    
    public ArrayList<Student> getStudentData() {

        ArrayList<Student> students = new ArrayList<Student>();

        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Student";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String id  = results.getString("StudentID");
                String name  = results.getString("Name");
                String email  = results.getString("Email");
                // Create a LGA Object
                Student student = new Student(id, name, email);

                students.add(student);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return students;
    }
    
    public ArrayList<Data> getData(String dataSort1, String dataSort2, int startYear, int endYear) {
        ArrayList<Data> dataList = new ArrayList<Data>();
        Connection connection = null;
        PreparedStatement statement = null;
    
        try {
            connection = DriverManager.getConnection(DATABASE);
    
            String query = "";
            if (dataSort1.equals("1") && dataSort2.equals("1")) {
                query = "SELECT * FROM CountryTemp WHERE Year BETWEEN ? AND ?";
            } else if (dataSort1.equals("1") && dataSort2.equals("2")) {
                query = "SELECT * FROM GlobalTemp WHERE Year BETWEEN ? AND ?";
            } else if (dataSort1.equals("2") && dataSort2.equals("1")) {
                query = "SELECT * FROM CountryPop WHERE Year BETWEEN ? AND ?";
            } else if (dataSort1.equals("2") && dataSort2.equals("2")) {
                query = "SELECT * FROM GlobalPop WHERE Year BETWEEN ? AND ?";
            }
    
            statement = connection.prepareStatement(query);
            statement.setInt(1, startYear);
            statement.setInt(2, endYear);
    
            ResultSet results = statement.executeQuery();
    
            while (results.next()) {
                // get the results and create a new Data object
                Data data = new Data();
                data.setYear(results.getInt("Year"));
                data.setAvgTemp(results.getFloat("AvgTemp"));
                data.setMinTemp(results.getFloat("MinTemp"));
                data.setMaxTemp(results.getFloat("MaxTemp"));
                data.setCountryName(results.getString("CountryName"));
    
                // add the Data object to your list
                dataList.add(data);
            }
    
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    
        return dataList;
    }

}
