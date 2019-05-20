package com.jack_watson.enums

enum class FlagReason(val num: Int) {
    FLAG_REASON_NONE(0),
    FLAG_REASON_SOLO_CRASH(1),
    FLAG_REASON_VEHICLE_CRASH(2),
    FLAG_REASON_VEHICLE_OBSTRUCTION(3),
    FLAG_REASON_MAX(4)
}