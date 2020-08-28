package com.example.testaildinoutdemo.service
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.testaildinoutdemo.IOrderManager
import com.example.testaildinoutdemo.bean.OrderBean
import java.util.concurrent.CopyOnWriteArrayList

class OrderService : Service() {

    private var orderBeanList = CopyOnWriteArrayList<OrderBean>()

    override fun onBind(intent: Intent?): IBinder? {

        return orderBinder
    }

    private val orderBinder = object : IOrderManager.Stub() {

        override fun getAll(): MutableList<OrderBean> = orderBeanList

        override fun add(orderBean: OrderBean?) {

            orderBean?.id = orderBean?.id + "11"
            orderBean?.name = orderBean?.name + "_nn"
            orderBean?.amount = orderBean?.amount!! + 5000
            orderBeanList.add(orderBean)
        }

        override fun getNameList(names: Array<String>?) {
            names?.set(0,"苹果")
            names?.set(1,"雪梨")
            names?.set(2,"香蕉")
            names?.set(3,"杨梅")
        }

        override fun updateListIds(ids: Array<String>?) {
            ids?.set(0, "aaaaaaa")
            ids?.set(1, "bbbbbbb")
            ids?.set(2, "ccccccc")
        }

        override fun getOrder(orderBean: OrderBean?) {
            orderBean?.id = orderBean?.id + "12345"
            orderBean?.name = orderBean?.name + "笑傲江湖"
            orderBean?.amount = orderBean?.amount!! + 10000
        }

        override fun updateOrder(orderBean: OrderBean?) {
            orderBean?.id = orderBean?.id + "987654"
            orderBean?.name = orderBean?.name + "一统江湖"
            orderBean?.amount = orderBean?.amount!! + 8000
        }
    }
}