package com.example.testaildinoutdemo;
import com.example.testaildinoutdemo.OrderBean;

interface IOrderManager {

     List<OrderBean> getAll();

     void add(in OrderBean orderBean);

     void getNameList(out String[] names);

     void updateListIds(inout String[] ids);

     void getOrder(out OrderBean orderBean);

     void updateOrder(inout OrderBean orderBean);
}
