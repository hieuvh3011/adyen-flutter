package com.adyen.adyen_checkout.models

data class DropInStoredPaymentMethodDeletionModel(
    val storedPaymentMethodId: String,
    val dropInFlowType: DropInFlowType
)