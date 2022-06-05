//1. 계산식 제외한 나머지 문자 제거
val trim = """[^.\d-+*/()]""".toRegex()

fun trim(v: String): String {
    return v.replace(trim, "")
}

//2. -인 경우 + 추가
fun repMtoPM(v: String) = v.replace("-", "+-")

//3. 곱하기, 나누기 계산
val groupMD = """(\/|\*?)((?:\+|\+-)?[.\d]+)([*/])((?:\+|\+-)?[.\d]+)""".toRegex()

tailrec fun foldMD(v: String): String {
    if (groupMD.containsMatchIn(v).not()) {
        return v
    }

    return foldMD(groupMD.findAll(v).fold(v) { acc, curr ->
        val (sentence, md, left, op, right) = curr.groupValues
        when(md) {
            "/", "*" -> acc
            else -> {
                val leftValue = left.replace("+", "").toDouble()
                val rightValue = right.replace("+", "").toDouble()
                val result = when (op) {
                    "*" -> leftValue * rightValue
                    "/" -> leftValue / rightValue
                    else -> throw Throwable("invalid operator $op")
                }
                acc.replace(sentence, "+$result")
            }
        }
    })
}

//4. 더하기, 빼기 계산
fun sum(v: String): Double = v.split("+").sumOf { it.ifBlank { "0" }.toDouble() }

//5. 괄호 계산
val groupBracket = """(?:\()([^()]*)(?:\))""".toRegex()

tailrec fun foldBracket(v: String): String {
    if(groupBracket.containsMatchIn(v).not()) {
        return v
    }

    return foldBracket(groupBracket.findAll(v).fold(v) { acc, curr ->
        val(sentence, value) = curr.groupValues
        acc.replace(sentence, "+${numberSentence(value)}")
    })
}

fun numberSentence(v: String) = sum(foldMD(v))

fun cal(v: String): Double = numberSentence(foldBracket(repMtoPM(trim(v))))