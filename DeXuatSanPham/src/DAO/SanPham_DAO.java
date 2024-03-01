/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.SanPham;
import POJO.ThuongHieu;
import java.util.ArrayList;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

/**
 *
 * @author Admin
 */
public class SanPham_DAO {
    DBConnect con = DBConnect.getInstance();
    public static ArrayList<SanPham> layThongTinDuLieuSanPham() {
        ArrayList<SanPham> dsSanPham = new  ArrayList<SanPham>();
        try {
            DBConnect con = DBConnect.getInstance();
            try (var session = con.getSession()) {
                Result result = session.run("MATCH (p:product) RETURN p.name, p.gia, p.ram"); 

                while (result.hasNext()) {
                    org.neo4j.driver.Record record = result.next();
                    String name = record.get("p.name").asString();
                    Object giaObj = record.get("p.gia").asObject();
                    int gia = 0;
                    if (giaObj instanceof Number) {
                        gia = ((Number) giaObj).intValue();
                    } else if (giaObj instanceof String) {
                        gia = Integer.parseInt((String) giaObj);
                    }
                    String ram = record.get("p.ram").asString();
                    SanPham sp = new SanPham(name, gia, ram);
                    dsSanPham.add(sp);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsSanPham;
    }
    
    public boolean TaoNutSP(SanPham sp)
    {
        int n = 0;
        try (Session session = con.getSession())
        {
            session.writeTransaction((var tx) -> {
                tx.run("CREATE (p:product {name: $name, gia: $gia, ram: $ram})", 
                   Values.parameters("name", sp.getName(), "gia", sp.getGia(), "ram", sp.getRam()));               
                Result result = tx.run("MATCH (p:product {name: $name, gia: $gia, ram: $ram}) RETURN p", 
                       Values.parameters("name", sp.getName(), "gia", sp.getGia(), "ram", sp.getRam()));
                while (result.hasNext()) {
                    org.neo4j.driver.Record record = result.next();
                    System.out.println("Đã Tạo Thành Công Nút!");
                }
                return null;
            });
        } catch (Exception e) {
            System.out.println("Thất Bại!");
            e.printStackTrace();
        }
        return n>0;
    }
    public void TaoQuanHe(String relationshipType, String tenSanPham, String tenThuongHieu){
        SanPham sp = new SanPham();
        ThuongHieu th = new ThuongHieu();
        try (Session session = con.getSession()) {           
            Result result = session.run("MATCH (b:brand {name: $tenThuongHieu}), (p:product {name: $tenSanPham}) " +
                           "CREATE (p)-[r:" + relationshipType + "]->(b) " +
                           "RETURN p, b",
                            Values.parameters("tenSanPham", tenSanPham, "tenThuongHieu", tenThuongHieu));
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                System.out.println("Tạo Liên Kết Quan Hệ Thành Công!");
            }
        } catch (Exception e) {
            System.out.println("Tạo Liên Kết Quan Hệ Thất Bại!");
        }
    } 
    public boolean XoaNutSP(SanPham sp)
    {
        int n = 0;
        try (Session session = con.getSession())
        {
            session.writeTransaction((var tx) -> {
                Result result = tx.run("MATCH (p:product {name: $name}) DETACH DELETE p",
                                    Values.parameters("name", sp.getName()));
                
                while (result.hasNext()) {
                    org.neo4j.driver.Record record = result.next();
                    System.out.println("Đã Xoá Thành Công Nút!");
                }
                return null;
            });           
        } catch (Exception e) {
            System.out.println("Xoá Nút Thất Bại!");
            e.printStackTrace();
        }
        return n>0;
    }
    public boolean SuaSP(String tenSanPham, String tenMoi, String giaTienMoi, String ramMoi) {
        int n = 0;
        DBConnect con = DBConnect.getInstance();
        try (Session session = con.getSession()) {
            boolean success = session.writeTransaction((TransactionWork<Boolean>) tx -> {
                Result result = tx.run("MATCH (p:product {name: $tenSanPham}) SET p.name = $tenMoi, p.gia = $giaTienMoi, p.ram = $ramMoi  RETURN p",
                    Values.parameters("tenSanPham", tenSanPham, "tenMoi", tenMoi, "giaTienMoi", giaTienMoi, "ramMoi", ramMoi));              
                int propertiesUpdated = result.consume().counters().propertiesSet();
                if (propertiesUpdated > 0) {
                    System.out.println("Sửa Sản Phẩm Thành Công!");
                    return true;
                } else {
                    System.out.println("Sửa Sản Phẩm Thất Bại!");
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
    
    public boolean ThemSanPhamVaLienKetVoiThuongHieu(String tenSanPham, String tenThuongHieu, SanPham sp) {
        try (Session session = con.getSession()) {
            // Kiểm tra xem thương hiệu có tồn tại không
            Result brandResult = session.run("MATCH (b:brand {name: $brandName}) RETURN b", 
                    Values.parameters("brandName", tenThuongHieu));

            // Nếu không có thương hiệu, tạo thương hiệu mới
            Node brandNode;
            if (!brandResult.hasNext()) {
                // Thương hiệu không tồn tại, bạn có thể xử lý theo ý muốn ở đây
                return false;
            } else {
                brandNode = brandResult.single().get("b").asNode();
            }

            // Tạo sản phẩm và liên kết nó với thương hiệu
            session.writeTransaction((var tx) -> {
                tx.run("CREATE (p:product {name: $productName, gia: $gia, ram: $ram})", 
                        Values.parameters("productName", tenSanPham, "gia", sp.getGia(), "ram", sp.getRam()));
                tx.run("MATCH (p:product {name: $productName}), (b:brand {name: $brandName}) " +
                        "CREATE (p)-[:thuoc]->(b)" + 
                        "Return p,b", 
                        Values.parameters("productName", tenSanPham, "brandName", tenThuongHieu));
                return null;
            });

            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sản phẩm và liên kết với thương hiệu: " + e.getMessage());
            return false;
        }
    }
}
