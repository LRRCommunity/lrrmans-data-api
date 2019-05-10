package com.jack_watson.enums

enum class RaceState(val num: Int) {
    RACESTATE_INVALID(0),
    RACESTATE_NOT_STARTED(1),
    RACESTATE_RACING(2),
    RACESTATE_FINISHED(3),
    RACESTATE_DISQUALIFIED(4),
    RACESTATE_RETIRED(5),
    RACESTATE_DNF(6)
}