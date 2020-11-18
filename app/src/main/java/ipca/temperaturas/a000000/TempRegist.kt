package ipca.temperaturas.a000000

import java.util.*

//
// Created by lourencogomes on 11/18/20.
//

// Resposta 2a pergunta
class TempRegist {

    var localName        : String? = null
    var date        : Date? = null
    var temperature : Double? = null

    constructor(localName: String?, date: Date?, temperature: Double?) {
        this.localName = localName
        this.date = date
        this.temperature = temperature
    }
}