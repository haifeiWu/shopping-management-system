package lyons.dao;

import lyons.entity.Goods;
import lyons.entity.SalesMan;

import java.util.ArrayList;

public class DaoTest {
    public static void main(String[] args) {
        ArrayList<SalesMan> salesManList = new SalesManDao().displaySalesMan();
        System.out.println("\t\t\t所有售货员列表\n\n");
        System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
        for (int i = 0, length = salesManList.size(); i < length; i++) {
            SalesMan salesMan = salesManList.get(i);
            System.out.println("\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassWord());
            System.out.println();
        }
    }

    public static void testDisplay() {
        ArrayList<Goods> goodsList = new GoodsDao().displayGoods();
        System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
        //避免重复计算变量，浪费资源！
        for (int i = 0, length = goodsList.size(); i < length; i++) {
            Goods goods = goodsList.get(i);
            System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + " $\t\t" + goods.getGnum());

            int gNum = goods.getGnum();
            if (gNum == 0) {
                System.out.println("\t\t该商品已售空！");
            } else if (gNum < 10) {
                System.out.println("\t\t该商品已不足10件");
            } else {
                System.out.println("\t\t-");
            }
            System.out.println("\t");
        }
    }
}
