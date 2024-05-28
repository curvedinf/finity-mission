package com.game.finitymission.motes

enum class MoteMapName(val keyName: String) {
    OBJECT("object"),
    FROM("from"),
    TARGET("target")
}

typealias IdMoteMap = LinkedHashMap<Mote.MoteId, Mote?>
typealias NameMoteMap = LinkedHashMap<MoteMapName, Mote?>