package com.dev.spread.fragments.setting.payments

import java.io.Serializable

data class PaymentModel(
     val cardimage : Int ?=null,
     val binimage:Int ?=null,
     val labeltext:String?=null
) :Serializable