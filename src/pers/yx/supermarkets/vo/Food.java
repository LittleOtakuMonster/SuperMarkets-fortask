package pers.yx.supermarkets.vo;

import java.text.SimpleDateFormat;

/**
 * 食品类
 * @author 余翔
 */
public class Food extends Commodity {
    private Integer days;   //保质期天数
    String simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PD);


    private Food(FoodBuilder builder){
        super(builder);
        days = builder.days;
    }

     public static class FoodBuilder extends Commodity.Builder{
        private Integer days;

        public FoodBuilder days(int val){
            days = val;
            return this;
        }

        @Override
        public Food build() {
            return new Food(this);
        }

    }

    @Override
    public void showInfo() {
        System.out.println("食品："+name+"\t编号："+no+"\t价格："+price+"元\t生产日期："+simpleDate+"\t保质期："+days+"天\t库存："+SN+"件");
    }

    @Override
    public String toString() {
        return no+"="+name+"/"+simpleDate+"/"+price+"/"+SN+"/"+days+"\n";
    }
}
