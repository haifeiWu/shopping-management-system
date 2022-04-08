package lyons.dao;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Gsales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 数据库gSales表操作
 *
 * @author lyons(zhanglei)
 */
public final class GsalesDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1.当天卖出的商品
     *
     * @return ArrayList<Gsales> 商品信息,包括 allSum (单种商品的销售总和)
     */
    public ArrayList<Gsales> dailyGsales() {
        ArrayList<Gsales> GsalesList = new ArrayList<Gsales>();
        conn = DbConn.getconn();

        //售卖时间=当前时间
        String sql = "SELECT \n" +
                "    g.gname, g.gprice, g.gnum,gs.snum\n" +
                "FROM\n" +
                "    (SELECT \n" +
                "        *\n" +
                "    FROM\n" +
                "        goods) g\n" +
                "        LEFT JOIN\n" +
                "    (SELECT \n" +
                "        *\n" +
                "    FROM\n" +
                "        gsales\n" +
                "    WHERE\n" +
                "        sdate < ?) gs ON g.gid = gs.gid\n" +
                "WHERE\n" +
                "    gs.snum > 0;";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, System.currentTimeMillis());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String gName = rs.getString(1);
                double gPrice = rs.getDouble(2);
                int gNum = rs.getInt(3);
                int snum = rs.getInt(4);

                Gsales Gsales = new Gsales(gName, gPrice, gNum, snum);
                GsalesList.add(Gsales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return GsalesList;
    }

    /**
     * 2.购物结算-向sales表中插入商品数据！
     *
     * @param gSales 售卖商品对象
     * @return boolean
     */
    public boolean shoppingSettlement(Gsales gSales) {
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO gsales(GID,SID,SNUM,sdate) VALUES(?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gSales.getGId());
            pstmt.setInt(2, gSales.getSId());
            pstmt.setInt(3, gSales.getSNum());
            pstmt.setLong(4, System.currentTimeMillis());

            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return bool;
    }

}
