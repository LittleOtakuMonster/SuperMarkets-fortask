package pers.yx.supermarkets.vo;


import java.util.Date;

/**
 * 商品类
 * 基本属性有编号、商品名称、库存数量、单价、生产日期
 *
 * @author 余翔
 */
public abstract class Commodity {
    public int no;          //编号
    public String name;     //商品名称
    public int SN;          //库存数量stock number
    public Float price;     //单价
    public Date PD;         //生产日期production date

    public Commodity(){}

    public Commodity(Builder builder){
        no = builder.no;
        name = builder.name;
        SN = builder.SN;
        price = builder.price;
        PD = builder.PD;
    }

    /**
     * 商品建造者
     */
    public abstract static class Builder{
        private int no;
        private String name;
        private int SN;
        private Float price;
        private Date PD;

        public Builder no(int val){ no=val; return this; }
        public Builder name(String val){ name = val; return this; }
        public Builder sn(int val){ SN = val; return this; }
        public Builder price(Float val){ price = val; return this; }
        public Builder pd(Date val){ PD = val; return this; }

        public abstract Commodity build();
    }

    /**
     * 获得商品信息的方法
     */
    public abstract void showInfo();

    public Float getPrice() {
        return price;
    }

    public int getSN() {
        return SN;
    }

    public String getName() {
        return name;
    }
}
