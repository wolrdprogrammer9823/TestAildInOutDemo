package com.example.testaildinoutdemo.bean
import android.os.Parcel
import android.os.Parcelable

class OrderBean() : Parcelable {

     var id :String? = null
     var name :String? = null
     var amount :Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        amount = parcel.readInt()
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(name)
        dest?.writeInt(amount)
    }

    override fun toString(): String {
        return "OrderBean(id=$id, name=$name, amount=$amount)"
    }

    fun readFromParcel(parcel: Parcel) {
        id = parcel.readString()
        name = parcel.readString()
        amount = parcel.readInt()
    }

    companion object CREATOR : Parcelable.Creator<OrderBean> {
        override fun createFromParcel(parcel: Parcel): OrderBean {
            return OrderBean(parcel)
        }

        override fun newArray(size: Int): Array<OrderBean?> {
            return arrayOfNulls(size)
        }
    }


}