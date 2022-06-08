package com.danghuong.demoyoutubeapi.common


class Event (){

    var typeEvent: Int?=null
    var stringValue: String ?=null
    var stringValue2: String?=null
    var isPlay: Boolean ?=null
    var getNumber1 :Int?=null
    var getNumber2 :Int?=null

    constructor(typeEvent: Int, isPlay: Boolean) : this() {
        this.typeEvent = typeEvent
        this.isPlay = isPlay
    }

    constructor(typeEvent: Int, getNumber: Int) : this() {
        this.typeEvent = typeEvent
        this.getNumber1 = getNumber
    }


    constructor(typeEvent: Int, stringValue: String) : this() {
        this.typeEvent = typeEvent
        this.stringValue = stringValue
    }

    constructor(typeEvent: Int) : this() {
        this.typeEvent = typeEvent
    }
}

