package client

import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.lang.Exception

class SoapClass {
    private val NAMESPACE = "http://tempuri.org/"
    private val URL = "http://www.dneonline.com/calculator.asmx"
    private val SOAP_ACTION = "http://tempuri.org/Add"
    private val METHOD_NAME = "Add"

    fun addNumber(a: Int, b: Int): Int {
        val request = SoapObject(NAMESPACE, METHOD_NAME)

        // Добавляем параметры запроса
        val property1 = PropertyInfo()
        property1.name = "intA"
        property1.value = a
        property1.type = Integer::class.java

        val property2 = PropertyInfo()
        property2.name = "intB"
        property2.value = b
        property2.type = Integer::class.java

        request.addProperty(property1)
        request.addProperty(property2)

        // Создаем объект SoapSerializationEnvelope для запроса
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.dotNet = true
        envelope.setOutputSoapObject(request)
        val transport = HttpTransportSE(URL)
        try {
            transport.call(SOAP_ACTION, envelope)

            // Получаем результат
            val response = envelope.response as SoapPrimitive
            val result = response.toString()
            return result.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 228
    }

}
