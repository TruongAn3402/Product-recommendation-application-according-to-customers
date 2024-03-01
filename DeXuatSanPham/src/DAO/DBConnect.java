/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.exceptions.ServiceUnavailableException;

/**
 *
 * @author Admin
 */
public class DBConnect {
    private static DBConnect instance = new DBConnect();
    private static Driver driver;

    private DBConnect() {
        try {
            driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123456789"));
            System.err.println("Kết nối database Neo4j thành công!");
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
            System.err.println("Kết nối database Neo4j thất bại!");
        }
    }

    public static DBConnect getInstance() {
        return instance;
    }

    public Session getSession() {
        return driver.session();
    }

    public void close() {
        if (driver != null) {
            driver.close();
        }
    }
}
