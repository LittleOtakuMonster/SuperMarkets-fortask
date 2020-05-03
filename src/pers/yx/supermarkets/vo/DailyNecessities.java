package pers.yx.supermarkets.vo;

import java.text.SimpleDateFormat;

/**
 * 日用品类
 * @author 余翔
 */
public class DailyNecessities extends Commodity {
    private String mfrs;    //manufacturer制造商
    String simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PD);

    private DailyNecessities(DailyBuilder builder) {
        super(builder);
        mfrs = builder.mfrs;
    }

    public static class DailyBuilder extends Commodity.Builder{
        private String mfrs;

        public DailyBuilder mfrs(String val){
            mfrs = val;
            return this;
        }

        @Override
        public DailyNecessities build() { return new DailyNecessities(this); }
    }

    @Override
    public void showInfo() {
        System.out.println("日用品："+name+"\t编号："+no+"\t价格："+price+"元\t生产日期："+simpleDate+"\t制造商："+mfrs+"\t库存："+SN+"件");
    }

    @Override
    public String toString() {
        return no+"="+name+"/"+simpleDate+"/"+price+"/"+SN+"/"+mfrs+"\n";
    }
}
