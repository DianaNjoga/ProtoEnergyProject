package com.protoEnergy.protoenergy

import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.protoEnergy.protoenergy.data.OrderList

@BindingAdapter("name")
fun TextView.setName(item: OrderList?){
    item?.let {
        text=it.customerName
    }

}
@BindingAdapter("date")
fun TextView.setDate(item: OrderList?){
    item?.let {
        text=it.dateCreated
    }

}
@BindingAdapter("status")
fun TextView.setStatus(item: OrderList?){
    item?.let {
        text=it.status
    }

}
@BindingAdapter("location")
fun TextView.setLocation(item: OrderList?){
    item?.let {
        text=it.deliveryPointName
    }


}