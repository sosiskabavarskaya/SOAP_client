package controller

import entity.DownloadFileInfoEntity
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class SOAPController {
    private val namespace = "https://fias.nalog.ru/WebServices/Public/DownloadService.asmx/"
    private val url = "https://fias.nalog.ru/WebServices/Public/DownloadService.asmx"

    // Выдает список объектов DownloadFileInfoEntity
    fun GetAllDownloadFileInfo(): List<DownloadFileInfoEntity>? {
        val method = "GetAllDownloadFileInfo"
        val request = SoapObject(namespace, method)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.dotNet = true
        envelope.setOutputSoapObject(request)
        val transport = HttpTransportSE(url)
        val soapAction = namespace + method
        try {
            transport.call(soapAction, envelope)
            val response = envelope.response as SoapObject // гарфиас работает с SOAPObject почему-то (да и похуй: https://stackoverflow.com/a/31263702)
            val objectList: MutableList<DownloadFileInfoEntity> = mutableListOf<DownloadFileInfoEntity>()
            for (i in 0 until response.propertyCount) {
                val item = response.getProperty(i) as SoapObject
                val entity = this.parseSoapObjectToEntity(item)
                objectList.add(entity)
            }
            return objectList
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun GetLastDownloadFileInfo(): DownloadFileInfoEntity? {
        val method = "GetLastDownloadFileInfo"
        val request = SoapObject(namespace, method)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.dotNet = true
        envelope.setOutputSoapObject(request)
        val transport = HttpTransportSE(url)
        val soapAction = namespace + method
        try {
            transport.call(soapAction, envelope)
            val response = envelope.response as SoapObject // гарфиас работает с SOAPObject почему-то (да и похуй: https://stackoverflow.com/a/31263702)
            return parseSoapObjectToEntity(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun parseSoapObjectToEntity(soapObject: SoapObject): DownloadFileInfoEntity {
        val entity = DownloadFileInfoEntity()

        entity.versionId = soapObject.getProperty("VersionId").toString().toLong()
        entity.textVersion = soapObject.getProperty("TextVersion").toString()
        entity.fiasCompleteDbfUrl = soapObject.getProperty("FiasCompleteDbfUrl").toString()
        entity.fiasCompleteXmlUrl = soapObject.getProperty("FiasCompleteXmlUrl").toString()
        entity.fiasDeltaDbfUrl = soapObject.getProperty("FiasDeltaDbfUrl").toString()
        entity.fiasDeltaXmlUrl = soapObject.getProperty("FiasDeltaXmlUrl").toString()
        entity.kladr4ArjUrl = soapObject.getProperty("Kladr4ArjUrl").toString()
        entity.kladr47ZUrl = soapObject.getProperty("Kladr47ZUrl").toString()
        entity.garXMLFullURL = soapObject.getProperty("GarXMLFullURL").toString()
        entity.garXMLDeltaURL = soapObject.getProperty("GarXMLDeltaURL").toString()
        entity.date = soapObject.getProperty("Date").toString()

        return entity
    }
}