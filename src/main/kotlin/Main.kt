import controller.SOAPController

fun main(){
    val controller = SOAPController()
    val soapResponseObject = controller.GetAllDownloadFileInfo()
    if (soapResponseObject != null) {
        println("Response length is: " + soapResponseObject.size)
    }
    if (soapResponseObject != null) {
        for (item in soapResponseObject){
            println(item.toString())
        }
    }

}