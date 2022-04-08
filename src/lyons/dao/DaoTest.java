package lyons.dao;

import lyons.entity.Goods;
import lyons.entity.SalesMan;

import java.util.ArrayList;

public class DaoTest {
    public static void main(String[] args) {
        ArrayList<SalesMan> salesManList = new SalesManDao().displaySalesMan();
        System.out.println("\t\t\t�����ۻ�Ա�б�\n\n");
        System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");
        for (int i = 0, length = salesManList.size(); i < length; i++) {
            SalesMan salesMan = salesManList.get(i);
            System.out.println("\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassWord());
            System.out.println();
        }
    }

    public static void testDisplay() {
        ArrayList<Goods> goodsList = new GoodsDao().displayGoods();
        System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
        //�����ظ�����������˷���Դ��
        for (int i = 0, length = goodsList.size(); i < length; i++) {
            Goods goods = goodsList.get(i);
            System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + " $\t\t" + goods.getGnum());

            int gNum = goods.getGnum();
            if (gNum == 0) {
                System.out.println("\t\t����Ʒ���ۿգ�");
            } else if (gNum < 10) {
                System.out.println("\t\t����Ʒ�Ѳ���10��");
            } else {
                System.out.println("\t\t-");
            }
            System.out.println("\t");
        }
    }
}
