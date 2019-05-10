package com.jack_watson.enums

enum class SessionState(val num: Int) {
    SESSION_INVALID(0),
    SESSION_PRACTICE(1),
    SESSION_TEST(2),
    SESSION_QUALIFY(3),
    SESSION_FORMATION_LAP(4),
    SESSION_RACE(5),
    SESSION_TIME_ATTACK(6)
}