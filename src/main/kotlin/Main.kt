import client.SoapClass

fun main(){
    val client = SoapClass()
    val a = 10
    val b = 34
    val result = client.addNumber(a, b)
    println("res is $result")
}