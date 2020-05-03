package Test;

import pers.yx.supermarkets.business.Server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 余翔
 */
public class CommodityTest {

    public static void main(String[] args){
        Server server = new Server();
        server.showAll();
        System.out.println("------------------------------------------");
        server.getOne(1);
        System.out.println("-------------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date PD = null;
        try {
            PD = sdf.parse("2020-04-30 11:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        server.add(3, "棒棒糖", 33, 12f, PD ,"100");
        server.getOne(3);
        System.out.println("-------------------------------------------");
        server.remove(3);
        System.out.println("--------------------------------------------");
        server.getSingleCSN(2);
        System.out.println("---------------------------------------------");
        server.getAllCSN();
        server.save();
    }
}