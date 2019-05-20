package com.jack_watson.enums

enum class PitMode(val num: Int) {
    PIT_MODE_NONE(0),
    PIT_MODE_DRIVING_INTO_PITS(1),
    PIT_MODE_IN_PIT(2),
    PIT_MODE_DRIVING_OUT_OF_PITS(3),
    PIT_MODE_IN_GARAGE(4),
    PIT_MODE_DRIVING_OUT_OF_GARAGE(5),
    PIT_MODE_MAX(6)

}