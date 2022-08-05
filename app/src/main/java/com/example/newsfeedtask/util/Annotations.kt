package com.example.newsfeedtask.util

import androidx.annotation.IntDef
import androidx.annotation.StringDef


@IntDef(
    Value.MALE,
    Value.FEMALE
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class Gender



@IntDef(
    Value.DELIVERY_DUE,
    Value.DELIVERY_DAYS,
    Value.DELIVERY_FULL
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class DeliveryOptionType

@IntDef(
    Value.CAROUSEL,
    Value.GRID
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class ListViewType

@IntDef(
    Value.NONE,
    Value.LEFT_TO_RIGHT,
    Value.RIGHT_TO_LEFT,
    Value.FADE_IN,
    Value.FADE_OUT
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class AnimationType

@StringDef(
    Value.ABOUT_US,
    Value.DELIVERY,
    Value.PRIVACY_POLICY,
    Value.RETURN_POLICY,
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class InfoPageType

@StringDef(
    Value.ADD_VOUCHER,
    Value.REMOVE_VOUCHER
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class VoucherAction

interface Value {

    companion object {

        /**
         * Gender annotation values
         */
        const val MALE = 1
        const val FEMALE = 2

        /**
         * Delivery option type annotation values
         */
        const val DELIVERY_DUE = 1
        const val DELIVERY_DAYS = 2
        const val DELIVERY_FULL = 3

        /**
         * ListView type annotation values
         */
        const val CAROUSEL = 1
        const val GRID = 2

        /**
         * Animation types
         */
        const val NONE = 0
        const val LEFT_TO_RIGHT = 1
        const val RIGHT_TO_LEFT = 2
        const val FADE_IN = 3
        const val FADE_OUT = 4


        /**
         * Info page values
         */
        const val ABOUT_US = "about-us"
        const val DELIVERY = "delivery"
        const val PRIVACY_POLICY = "privacy-policy"
        const val RETURN_POLICY = "return-policy"
        const val TERMS_AND_CONDITIONS = "terms-and-conditions"
        const val IMPRINT = "imprint"

        /**
         * Voucher action
         */
        const val ADD_VOUCHER = "addVoucher"
        const val REMOVE_VOUCHER = "removeVoucher"
    }
}