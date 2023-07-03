import client.SOAPClient

fun main(){
    val client = SOAPClient()
    val namespace = "https://fias.nalog.ru/WebServices/Public/DownloadService.asmx/"
    val url = "https://fias.nalog.ru/WebServices/Public/DownloadService.asmx"
    val method = "GetAllDownloadFileInfo"

    val soapResponseObject = client.makeFiasRequest(namespace, url, method)
    System.out.println("response is: " + soapResponseObject.toString())
}