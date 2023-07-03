package client

import entity.DownloadFileInfoEntity
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class SOAPClient {
    fun makeFiasRequest(namespace: String, url: String, method: String): DownloadFileInfoEntity? {
        val request = SoapObject(namespace, method)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.dotNet = false
        envelope.setOutputSoapObject(request)
        val transport = HttpTransportSE(url)
        val soapAction = namespace + method
        try {
            transport.call(soapAction, envelope)
            var response = envelope.response as SoapObject // гарфиас работает с SOAPObject почему-то
            System.out.println(response.toString())
            response = response.getProperty("DownloadFileInfo") as SoapObject

            val entityObject = this.parseSoapObjectToEntity(response)
            return entityObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun parseSoapObjectToEntity(soapObject: SoapObject): DownloadFileInfoEntity {
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