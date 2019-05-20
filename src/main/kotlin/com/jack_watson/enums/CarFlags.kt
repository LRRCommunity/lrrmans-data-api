package com.jack_watson.enums

enum class CarFlags(val num: Int) {
    CAR_HEADLIGHT(1),
    CAR_ENGINE_ACTIVE(2),
    CAR_ENGINE_WARNING(4),
    CAR_SPEED_LIMITER(8),
    CAR_ABS(16),
    CAR_HANDBRAKE(32),
}