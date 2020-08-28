package com.example.testaildinoutdemo
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.example.testaildinoutdemo.bean.OrderBean
import com.example.testaildinoutdemo.service.OrderService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var orderManager : IOrderManager? = null
    private var orderBeanList : List<OrderBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener(this)
        btn_query.setOnClickListener(this)
        btn_search.setOnClickListener(this)

        val intent = Intent(this, OrderService::class.java)
        intent.setPackage("com.example.testaildinoutdemo.service")
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add -> {

                val orderBean = OrderBean()
                orderBean.id = "id_" + Random.nextInt(1000000)
                orderBean.name = "name_" + Random.nextInt()
                orderBean.amount = Random.nextInt(1000)
                orderManager?.add(orderBean)
            }

            R.id.btn_search -> {

                //测试out数组
                val names = arrayOf("大西厢", "小梧桐", "房间","芒果")
                orderManager?.getNameList(names)
                Log.d(TAG, names.contentToString())

                //测试inout数组
                val ids = arrayOf("ids1", "ids2", "ids3", "ids4")
                orderManager?.updateListIds(ids)
                Log.d(TAG, ids.contentToString())

                //out方式传递Parcelable对象
                val orderBean = OrderBean()
                orderBean.id = "1111111111111"
                orderBean.name = "2222222222222222"
                orderBean.amount = 20000
                orderManager?.getOrder(orderBean)
                Log.d(TAG, orderBean.toString())

                //inout方式传递Parcelable对象
                val mOrderBean = OrderBean()
                mOrderBean.id = "77777"
                mOrderBean.name = "上山打老虎"
                mOrderBean.amount = 8000
                orderManager?.updateOrder(mOrderBean)
                Log.d(TAG, mOrderBean.toString())
            }

            R.id.btn_query -> {

                orderBeanList = orderManager?.all

                for (orderBean in orderBeanList!!) {

                    Log.d(TAG, orderBean.toString())
                }
            }

            else -> {}
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            orderManager = IOrderManager.Stub.asInterface(service)
            Log.d(TAG, "override fun onServiceConnected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }
}