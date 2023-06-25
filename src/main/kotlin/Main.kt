import client.SoapClass

fun main(){
    val client = SoapClass()
    val a = 228
    val b = 1337
    val result = client.addNumber(a, b)
    println("res is $result")
}