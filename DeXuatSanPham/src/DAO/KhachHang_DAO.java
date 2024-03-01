/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import POJO.KhachHang;
import POJO.ThuongHieu;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.driver.Value;

/**
 *
 * @author Admin
 */
public class KhachHang_DAO {
    DBConnect con = DBConnect.getInstance();
    public boolean TaoNutKH(KhachHang kh)
    {
        int n = 0;
        DBConnect con = DBConnect.getInstance();
        try (Session session = con.getSession())
        {
            session.writeTransaction((var tx) -> {
                tx.run("CREATE (c:customer {nameKH: $nameKH, soThich:$soThich })", 
                   Values.parameters("nameKH", kh.getTenKH(), "soThich",kh.getSoThich()));
                Result result = tx.run("MATCH (c:customer {name: $nameKH, soThich:$soThich}) RETURN c", 
                       Values.parameters("nameKH", kh.getTenKH(),"soThich",kh.getSoThich()));
                while (result.hasNext()) {
                    org.neo4j.driver.Record record = result.next();
                    System.out.println("Đã Tạo Thành Công Khách Hàng!");
                }
                return null;
            });           
        } catch (Exception e) {
            System.out.println("Tạo Khách Hàng Thất Bại!");
            e.printStackTrace();
        }
        return n>0;
    }
    public void TaoQuanHeKH(String tenKH, String tenThuongHieu){
        KhachHang kh = new KhachHang();
        ThuongHieu th = new ThuongHieu();
        try (Session session = con.getSession()) {           
            Result result = session.run("MATCH (b:brand), (c:customer) " +
                "WHERE b.name = $tenThuongHieu AND c.nameKH = $tenKH " +
                "CREATE (c)-[r:thich]->(b) " +
                "RETURN c, b",
                Values.parameters("tenThuongHieu", tenThuongHieu, "tenKH", tenKH));
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                System.out.println("Tạo Liên Kết Quan Hệ Thành Công!");
            }
        } catch (Exception e) {
            System.out.println("Tạo Liên Kết Quan Hệ Thất Bại!");
        }
    }
    
    public List<String> DeXuatSanPhamGiaTren20tr(String thuonghieumongmuon) {
        List<String> ketQua = new ArrayList<>();
        try (Session session = con.getSession()) {
            Result result = session.run("MATCH (b:brand{name:'" + thuonghieumongmuon + "'})<-[:thuoc]-(p:product) " +
                                        "WHERE toInteger(p.gia) > 20000000 RETURN p");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Value product = record.get("p");
                String tenSanPham = product.get("name").asString();
                ketQua.add(tenSanPham);
                System.out.println("Đề xuất Thành Công!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đề xuất sản phẩm với giá trên 20 triệu: " + e.getMessage());
        }
        return ketQua;
    }
    
    public List<String> DeXuatSanPhamGia10Den20(String thuonghieumongmuon) {
        List<String> ketQua = new ArrayList<>();
        try (Session session = con.getSession()) {
            Result result = session.run("MATCH (b:brand{name:'" + thuonghieumongmuon + "'})<-[:thuoc]-(p:product) " +
                                        "WHERE toInteger(p.gia) >= 10000000 AND toInteger(p.gia) <= 20000000 RETURN p");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Value product = record.get("p");
                String tenSanPham = product.get("name").asString();
                ketQua.add(tenSanPham);
                System.out.println("Đề xuất Thành Công!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đề xuất sản phẩm giá 10 - 20 triệu: " + e.getMessage());
        }
        return ketQua;
    }
    
    public List<String> DeXuatSanPhamGia5Den10(String thuonghieumongmuon) {
        List<String> ketQua = new ArrayList<>();
        try (Session session = con.getSession()) {
            Result result = session.run("MATCH (b:brand{name:'" + thuonghieumongmuon + "'})<-[:thuoc]-(p:product) " +
                                        "WHERE toInteger(p.gia) >= 5000000 AND toInteger(p.gia) <= 10000000 RETURN p");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Value product = record.get("p");
                String tenSanPham = product.get("name").asString();
                ketQua.add(tenSanPham);
                System.out.println("Đề xuất Thành Công!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đề xuất sản phẩm giá 5 - 10 triệu: " + e.getMessage());
        }
        return ketQua;
    }
    
    public List<String> DeXuatSanPhamKhongTheoGia(String thuonghieumongmuon) {
        List<String> ketQua = new ArrayList<>();
        try (Session session = con.getSession()) {
            Result result = session.run("MATCH (b:brand{name:'" + thuonghieumongmuon + "'})<-[:thuoc]-(p:product) RETURN p");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Value product = record.get("p");
                String tenSanPham = product.get("name").asString();
                ketQua.add(tenSanPham);
                System.out.println("Đề xuất Thành Công!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đề xuất sản phẩm: " + e.getMessage());
        }
        return ketQua;
    }
}
