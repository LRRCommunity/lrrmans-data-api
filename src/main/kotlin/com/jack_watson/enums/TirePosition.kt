package com.jack_watson.enums

enum class TirePosition(val num: Int, val queryKey: String) {
    TIRE_FRONT_LEFT(0, "front_left"),
    TIRE_FRONT_RIGHT(1, "front_right"),
    TIRE_REAR_LEFT(2, "rear_left"),
    TIRE_REAR_RIGHT(3, "rear_right");

    companion object {
        fun getTirePositionByIndex(index: Int) =
            when (index) {
                0 -> TIRE_FRONT_LEFT
                1 -> TIRE_FRONT_RIGHT
                2 -> TIRE_REAR_LEFT
                3 -> TIRE_REAR_RIGHT
                else -> {
                    TIRE_FRONT_LEFT
                }
            }
    }
}


