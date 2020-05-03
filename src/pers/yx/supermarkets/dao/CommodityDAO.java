package pers.yx.supermarkets.dao;

import pers.yx.supermarkets.vo.Commodity;
import pers.yx.supermarkets.vo.DailyNecessities;
import pers.yx.supermarkets.vo.Food;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Pattern;


/**
 * 使用IO+Properties模拟数据库
 * @author 余翔
 */
public class CommodityDAO {
    private static HashMap<Integer, Commodity> commodities = new HashMap<>();//商品集合
    private static final Pattern PATTERN = Pattern.compile("0|([-]?[1-9][0-9]*)");//正则表达式
    private static boolean isInt(String str) {//使用正则表达式判断字符串能否转为int类型
        return PATTERN.matcher(str).matches();
    }
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式化

    static {
        try(FileReader reader = new FileReader("src/commodity.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            for (String key:properties.stringPropertyNames()) {//properties.stringPropertyNames()获得key值，key值就是产品编号
                String value = properties.getProperty(key);//这个方法获得value值
                String[] str = value.split("/");
                int no = Integer.parseInt(key);
                String name = str[0];//0号数组是商品名
                Date PD = sdf.parse(str[1]);//1号数组是日期格式化字符串
                Float price = Float.parseFloat(str[2]);//2号数组是价格
                int SN = Integer.parseInt(str[3]);//3号数组为库存
                String mfrs = str[4];//4号数组为制造商或保质期
                if(isInt(mfrs)) {
                    int days = Integer.parseInt(mfrs);
                    Commodity f = new Food.FoodBuilder().days(days).no(no).name(name).pd(PD).price(price).sn(SN).build();
                    commodities.put(no, f);
                }
                else {
                    Commodity d = new DailyNecessities.DailyBuilder().mfrs(mfrs).no(no).name(name).pd(PD).price(price).sn(SN).build();
                    commodities.put(no, d);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void showAll() {
        for (Commodity c:commodities.values()) {
            c.showInfo();
        }
    }

    public static Commodity[] getAll(){
        ArrayList<Commodity> arrayList = new ArrayList<>(commodities.values());
        return arrayList.toArray(new Commodity[arrayList.size()]);
    }

    public static Commodity getCommodity(int no){
        return commodities.get(no);
    }

    public static void insert(Commodity commodity){
        commodities.put(commodity.no, commodity);
    }

    public static void delete(int no){
        commodities.remove(no);
    }

    public static void commit(){
        try(FileWriter writer = new FileWriter("src/commodity.properties");
            BufferedWriter bw = new BufferedWriter(writer)){
            bw.write("############注释#########################\n");
            bw.write("#key=value\n");
            bw.write("#编号=名字/生产日期/价格/库存/保质期or制造商\n");
            if(commodities.size()!=0){
                Commodity[] commodities = getAll();
                for (Commodity commodity : commodities) {
                    bw.write(commodity.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
