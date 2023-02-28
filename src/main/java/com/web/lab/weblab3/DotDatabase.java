package com.web.lab.weblab3;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class DotDatabase {
    private DataSource dataSource;

    private Connection connection;

    @PostConstruct
    public void init() throws NamingException {
        initConnection();
    }

    private void initConnection() throws NamingException {
        String url = "jdbc:postgresql://localhost:5432/studs";
        String user = "s339923";
        String password = "9vYrrNRERIyafAsm";

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
            connection.createStatement().execute(
                    "create table if not exists dotslist (" +
                            "x float , y float, r float, res text, timework text ,owner text)"
            );
        } catch (SQLException e) {
            throw new IllegalStateException("Couldn't create connection", e);
        }
    }

    public void addPointToTable(Dot dot){
        try {
            if (connection == null) {
                initConnection();
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO dotslist VALUES (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setDouble(1, dot.getX());
            preparedStatement.setDouble(2, dot.getyDouble());
            preparedStatement.setFloat(3, dot.getR());
            preparedStatement.setString(4, dot.getRes());
            preparedStatement.setString(5,dot.getTimework());
            preparedStatement.setString(6, dot.getOwner());
            preparedStatement.execute();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearDatabase(){
        try {
            if (connection == null) {
                initConnection();
            }
            connection.createStatement().execute("DELETE FROM dotslist");
        }catch (NamingException | SQLException e){
            e.printStackTrace();
        }
    }

    public List<Dot> getPoints(String session_id){
        List<Dot> pointsList = new ArrayList<>();
        try {
            if (connection == null)
                initConnection();
            ResultSet rs = connection.createStatement().executeQuery("select * from dotslist");
            while (rs.next()) {
                Dot dot = new Dot();
                dot.setX(rs.getFloat("x"));
                dot.setY(rs.getString("y"));
                dot.setR(rs.getFloat("r"));
                dot.setRes(rs.getString("res"));
                dot.setTimework(rs.getString("timework"));
                if (rs.getString("owner").equals(session_id)) {
                    pointsList.add(0, dot);
                }
            }
        }catch (SQLException | NamingException throwable) {
            throwable.printStackTrace();
        }
        return pointsList;
    }
}
