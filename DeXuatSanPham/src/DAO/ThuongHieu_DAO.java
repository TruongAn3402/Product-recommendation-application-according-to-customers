/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ThuongHieu;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.Values;

/**
 *
 * @author Admin
 */
public class ThuongHieu_DAO {
    public boolean TaoNutTH(ThuongHieu th)
    {
        int n = 0;
        DBConnect con = DBConnect.getInstance();
        try (Session session = con.getSession())
        {
            session.writeTransaction((var tx) -> {
                tx.run("CREATE (b:brand {name: $name})", 
                   Values.parameters("name", th.getName()));
                //System.out.println("Đã Tạo Thành Công Nút!");
                
                Result result = tx.run("MATCH (b:brand {name: $name}) RETURN b", 
                       Values.parameters("name", th.getName()));
                while (result.hasNext()) {
                    org.neo4j.driver.Record record = result.next();
                    System.out.println("Đã Tạo Thành Công Thương Hiệu!");
                }
                return null;
            });           
        } catch (Exception e) {
            System.out.println("Tạo Thương Hiệu Thất Bại!");
            e.printStackTrace();
        }
        return n>0;
    }
    
    public boolean XoaNutTH(ThuongHieu th)
    {
        int n = 0;
        DBConnect con = DBConnect.getInstance();
        try (Session session = con.getSession())
        {
            session.writeTransaction((var tx) -> {
                Result result = tx.run("MATCH (b:brand {name: $name}) DETACH DELETE b",
                                    Values.parameters("name", th.getName()));
                while (result.hasNext()) {
                    org.neo4j.driver.Record record = result.next();
                    //System.out.println("Record: " + record);
                    System.out.println("Đã Xoá Thành Công Thương Hiệu!");
                }
                return null;
            });           
        } catch (Exception e) {
            System.out.println("Xoá Nút Thất Bại!");
            e.printStackTrace();
        }
        return n>0;
    }
 
    public boolean SuaNutTH(String name, String newName) {
        DBConnect con = DBConnect.getInstance();
        try (Session session = con.getSession()) {
            boolean success = session.writeTransaction((TransactionWork<Boolean>) tx -> {
                Result result = tx.run("MATCH (b:brand {name: $currentName}) SET b.name = $newName RETURN b",
            Values.parameters("currentName", name, "newName", newName));

                int propertiesUpdated = result.consume().counters().propertiesSet();
                if (propertiesUpdated > 0) {
                    // Cập nhật thành công
                    System.out.println("Sửa Thương Hiệu Thành Công!");
                    return true;
                } else {
                    System.out.println("Sửa Thương Hiệu Thất Bại!");
                    return false;
                }
            });

            return success;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi trong quá trình truy vấn Neo4j: " + e.getMessage());
            return false;
        }
    }

    
}
