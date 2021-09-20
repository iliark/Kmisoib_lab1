fun main() {
    var opentext = "helloworld"
    println("Задание 1. Открытый текст: helloworld")
    println("Шифротекст: ")
    println(task1encr(opentext, 3))
    println("Расшифровнный текст: ")
    println(task1decr(task1encr(opentext, 3), 3))
    println("Задание 2. Открытый текст: helloworld")
    println("Шифротекст: ")
    println(task2encr(opentext, 3, 5))
    println("Расшифровнный текст: ")
    println(task2decr(task2encr(opentext, 3, 5), 3, 5))
    println("Задание 3. Открытый текст: abracadabra")
    println("Шифротекст, расшифрованный текст: ")
    task3("abracadabra")
    println("Задание 4. Открытый текст: helloworld")
    println("Шифротекст: ")
    println(task4encrypt(opentext, 3))
    println("Расшифровнный текст: ")
    println(task4decrypt(task4encrypt(opentext, 3), 3))
    println("Задание 5. Открытый текст: helloworld")
    println("Шифротекст: ")
    println(task5encrypt(opentext, "testkey"))
    println("Расшифровнный текст: ")
    println(task5decrypt(task5encrypt(opentext, "testkey"), "testkey"))
    println("Задание 6. Поиск ключа для ROT")
    task6()
    println("Задание 7. Открытый текст: helloworld")
    println("Шифротекст: ")
    println(task7(opentext))
    println("Расшифрованный текст:")
    println(task7(task7(opentext)))

}
    val engAlphabet = "abcdefghijklmnopqrstuvwxyz"
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


fun task2encr(input: String, key1: Int, key2: Int): String {
    var result = ""
    for (i in input.indices){
        if (i % 2 != 0)
            result += engAlphabet[Math.floorMod(engAlphabet.indexOf(input[i]) - key1, 26)]
        else
            result += engAlphabet[(engAlphabet.indexOf(input[i]) + key2)% 26]
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
    val input = "Pbatenghyngvbaf! Vg'f n Pnrfne pvcure!".lowercase()
    for (key in 1..26) {
        var result = ""
        for (letter in input){
            if (letter in engAlphabet)
                result+= engAlphabet[(engAlphabet.indexOf(letter) +key) % 26]
            else
                result += letter
        }
        println(result)
    }
}

fun task7(input: String):String {
    var result = ""
    for (letter in input) {
        result += when (letter.uppercaseChar() >= 'A' + 13) {
            true -> (letter - 13)
            false -> (letter + 13)
        }
    }
    return result
}