package com.protoEnergy.protoenergy.data
//data class OrderList(
//    val orders: List<Order>
//)

data class OrderList(
    val customerCode: String,
    val customerName: String,
    val deliveryPointCode: String,
    val deliveryPointName: String,
    val salesAreaCode: String,
    val salesAreaName: String,
    val batchNumber: String,
    val remarks: String,
    val status: String,
    val orderTotal: String,
    val userPhoneNumber0: String,
    val id: String,
    val dateCreated: String,
    val createdBy: String,
    val creatorUserEmail: String,
    val dateModified: String,
    val modifiedBy: String,
    val modifierUserEmail: String
)