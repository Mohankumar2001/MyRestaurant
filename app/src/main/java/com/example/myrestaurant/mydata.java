package com.example.myrestaurant;

public class mydata {
        public int order_no,table_no;
        private String dish1 ,dish2 ,dish3,dish4;

        public mydata(int a , int b ,String d1, String d2 , String d3 , String d4) {
           order_no = a;
           table_no =b;
           dish1 =d1;
           dish2 = d2;
           dish3 =d3;
           dish4 = d4;
        }

        public int gettno() {
            return table_no;
        }
        public int getorderno() {
            return order_no;
        }

        public  String getd1(){
            return dish1;
        }
    public  String getd2(){
        return dish2;
    }
    public  String getd3(){
        return dish3;
    }
    public  String getd4(){
        return dish4;
    }
    }

