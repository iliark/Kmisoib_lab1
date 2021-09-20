fun main() {
    //task2("Pbatenghyngvbaf! Vg'f n Pnrfne pvcure!", 14)
    //
// task6()
    // task3("abracadabra")
    //println(task1encr("Amogus", 3))
    //println(task1decr("Agmuos", 3))
    // println(task5encrypt("zanuhni bebru", "suck"))
    // println(task5decrypt("rupezhk tydbm", "cock"))
    println(task4encrypt ("abracadabra", 2))
    println(task4decrypt("bdrcccdcstc", 2))
}
fun task1encr(opentext: String, key: Int): String {
    var resultEncr = ""
    var cur = 0
    var index = 0
    for (i in opentext.indices) {
        resultEncr += opentext[index]
        index += key
        if (index >= opentext.length) {
            cur += 1
            index = cur
        }
    }
    return resultEncr
}


fun task1decr(crypttext: String, key: Int): CharArray {
    val resultDecr=CharArray(crypttext.length)
    var cur = 0
    var index = 0
    for (letter in crypttext) {
        resultDecr[index] = letter
        index += key
        if (index >= crypttext.length) {
            cur += 1
            index = cur
        }
    }
    return resultDecr
}


fun task2encr(input: String, key: Int): String {
    var result = ""
    for (letter in input){
        result += if (letter.isLetter())
            if ((letter+key).uppercaseChar()<= 'Z') letter+key else letter+key-26
        else letter
    }
    return result
}

fun task2decr(input: String, key: Int): String {
    var result = ""
    for (letter in input){
        result += if (letter.isLetter())
            if ((letter+key).uppercaseChar()<= 'Z') letter+key else letter+key-26
        else letter
    }
    return result
}

fun task3(input: String) {
    val mapEncode =
        mapOf(
            'a' to listOf(34802, 29343, 24392),
            'b' to listOf(33463, 18124, 27134),
            'c' to listOf(73434, 11124, 31124),
            'd' to listOf(91232, 15123, 23213),
            'r' to listOf(11232, 17213, 25123)
        )
    var encrypted = ""
    var decrypted = ""
    var index = 0

    for (letter in input) {
        if (index < mapEncode.getValue(letter).size-1)
            encrypted += mapEncode.getValue(letter)[index++]
        else {
            encrypted += mapEncode.getValue(letter)[index]
            index = 0
        }
    }
    println("Encrypted: $encrypted")

    for (number in encrypted.chunked(5))
        for ((key, values) in mapEncode)
            if (number in values.toString())
                decrypted += key

    println("Decrypted: $decrypted")
}
fun task4encrypt(input: String, key: Int): String{
    val engAlphabet = "abcdefghijklmnopqrstuvwxyz"
    val chunkedInput = input.chunked(2)
    var encrypted = ""
    for (pair in chunkedInput) {
        if (pair.length == 2)
            encrypted.append(engAlphabet[(engAlphabet.indexOf(pair[0]) + engAlphabet.indexOf(pair[1])) % 26].toString() + engAlphabet[(engAlphabet.indexOf(pair[1]) + key) % 26].toString())
        else
            encrypted.append(engAlphabet[(engAlphabet.indexOf(pair[0])+key) % 26])
    }
    return encrypted.toString()
}

fun task4decrypt(input: String, key: Int): String{
    val engAlphabet = "abcdefghijklmnopqrstuvwxyz"
    val chunkedInput = input.chunked(2)
    var decrypted = ""
    for (pair in chunkedInput) {
        if (pair.length == 2)
            decrypted.append(engAlphabet[Math.floorMod(engAlphabet.indexOf(pair[0]) - engAlphabet.indexOf(pair[1]) + key, 26)].toString() + engAlphabet[Math.floorMod(engAlphabet.indexOf(pair[1]) - key, 26)].toString())
        else
            decrypted += engAlphabet[Math.floorMod(engAlphabet.indexOf(pair[0]-key), 26)]
    }
    return decrypted.toString()
}

fun task5encrypt(opentext: String, key: String): String{
    val engAlphabet = "abcdefghijklmnopqrstuvwxyz"
    var result_encr = ""
    for (i in opentext.indices) {
        if (opentext[i] in engAlphabet)
        result_encr += engAlphabet[(engAlphabet.indexOf(opentext[i]) + engAlphabet.indexOf(key[i % key.length])) % 26]
        else
        result_encr += opentext[i]
    }
    return result_encr
}

fun task5decrypt(crypttext: String, key: String): String{
    val engAlphabet = "abcdefghijklmnopqrstuvwxyz"
    var result_encr = ""
    for (i in crypttext.indices) {
        if (crypttext[i] in engAlphabet)
            result_encr += engAlphabet[Math.floorMod(engAlphabet.indexOf(crypttext[i]) - engAlphabet.indexOf(key[i % key.length]), 26)]
        else
            result_encr += crypttext[i]
    }
    return result_encr
}

fun task6() {
    val input = "Pbatenghyngvbaf! Vg'f n Pnrfne pvcure!"
    for (key in 1..26) {
        var result = ""
        for (letter in input){
            result += if (letter.isLetter())
                if ((letter+key).uppercaseChar()<= 'Z') letter+key else letter+key-26
            else letter
        }
        println(result)
    }
}

fun task7(input: String) {
    var result = ""
    for (letter in input) {
        result += when (letter.uppercaseChar() >= 'A' + 13) {
            true -> (letter - 13)
            false -> (letter + 13)
        }
    }
    println(result)
}