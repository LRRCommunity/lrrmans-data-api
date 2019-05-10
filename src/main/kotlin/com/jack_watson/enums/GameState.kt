package com.jack_watson.enums

enum class GameState (val num: Int){
    GAME_EXITED(0),
    GAME_FRONT_END(1),
    GAME_INGAME_PLAYING(2),
    GAME_INGAME_PAUSED(3),
    GAME_INGAME_INMENU_TIME_TICKING(4),
    GAME_INGAME_RESTARTING(5),
    GAME_INGAME_REPLAY(6),
    GAME_FRONT_END_REPLAY(7),
}