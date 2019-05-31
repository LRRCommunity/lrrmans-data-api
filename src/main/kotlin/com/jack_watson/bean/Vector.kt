package com.jack_watson.bean

//Built my own vector because the built in one doesn't work with Spring as expected
data class Vector<T> (var X: T, var Y: T, var Z: T)