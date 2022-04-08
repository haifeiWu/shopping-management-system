package lyons.db;

import lyons.entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestConn {
    public static void main(String[] args) {
        Connection conn = DbConn.getconn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM goods";
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int gID = rs.getInt("gid");
                String gName = rs.getString("gname");
                int gPrice = rs.getInt("gprice");
                int gNum = rs.getInt("gnum");
                Goods goods = new Goods(gID, gName, gPrice, gNum);
                goodsList.add(goods);

                System.out.println(gName);
                System.out.println(gPrice);
                System.out.println(gNum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
    }
}
