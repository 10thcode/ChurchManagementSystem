package apps.DbManuplation;

import apps.DbConnection.DbConnection;

import javax.management.Query;
import java.sql.*;

public class DbManipulate {

    private Connection con;
    private Statement statement;
    private PreparedStatement pStatement;
    private ResultSet resultSet;


    public  Boolean InsertIntoDb(String query){
       //
       con = DbConnection.getConnection();
        try {
            statement = con.createStatement();
            statement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }



    // Retrieve Data
    public ResultSet retrieveData(String query){

        con =DbConnection.getConnection();

        if (con != null){

            try {
                statement = con.createStatement();
                resultSet = statement.executeQuery(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        return resultSet;
    }


}
