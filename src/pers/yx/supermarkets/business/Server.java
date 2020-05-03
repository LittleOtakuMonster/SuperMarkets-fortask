package pers.yx.supermarkets.business;

import pers.yx.supermarkets.dao.CommodityDAO;
import pers.yx.supermarkets.vo.Commodity;
import pers.yx.supermarkets.vo.DailyNecessities;
import pers.yx.supermarkets.vo.Food;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * 业务服务层，只关心业务，不关心数据
 * @author 余翔
 */
public class Server {


    /**
     * 查看所有商品库存信息
     */
    public void showAll(){
        CommodityDAO.showAll();//不接返回值
    }

    /**
     * 查询某一商品信息
     */
    public void getOne(int no){
        Commodity c = CommodityDAO.getCommodity(no);
        if(c!=null){
            c.showInfo();
        }
        else {
            System.out.println("没有这个库存");
        }
    }


    /**
     * 添加商品
     */
    public void add(int no, String name, int SN, Float price, Date PD,String str){
        Pattern p = Pattern.compile("0|([-]?[1-9][0-9]*)");
        if(p.matcher(str).matches()){
            int days = Integer.parseInt(str);
            CommodityDAO.insert(new Food.FoodBuilder().days(days).no(no).name(name).pd(PD).price(price).sn(SN).build());
        }
        else CommodityDAO.insert(new DailyNecessities.DailyBuilder().mfrs(str).no(no).name(name).pd(PD).price(price).sn(SN).build());
        System.out.println("添加成功");
    }

    /**
     * 删除商品
     */
    public void remove(int no){
        if(CommodityDAO.getCommodity(no)!=null) {
            CommodityDAO.delete(no);
            System.out.println("删除成功");
        }
        else {
            System.out.println("没有该商品的库存");
        }
    }

    /**
     * 保存信息(提交事务)
     */
    public void save(){
        CommodityDAO.commit();
    }

    /**
     * 查询单件商品库存金额
     */
    public void getSingleCSN(int no){
        Commodity commodity = CommodityDAO.getCommodity(no);
        if(commodity!=null) {
            float ap = commodity.getPrice() * commodity.getSN();
            System.out.println(commodity.getName() + "的库存金额为：" + ap);
        }
    }

    /**
     * 查询总库存金额
     */
    public void getAllCSN(){
        Commodity[] commodities = CommodityDAO.getAll();
        float totalPrice = 0;
        if(commodities.length!=0) {
            for (Commodity c : commodities) {
                totalPrice += c.getPrice() * c.getSN();
            }
            System.out.println("总库存金额为：" + totalPrice);
        }
    }
}
