package com.chen.fakevibrato.bean

class ButtonBean {
    var type = ""  //    onBackPressed 返回键
    var position = ""  //

    constructor(type: String, position: String) {
        this.type = type
        this.position = position
    }

    override fun toString(): String {
        return "ButtonBean(type='$type', position='$position')"
    }


}