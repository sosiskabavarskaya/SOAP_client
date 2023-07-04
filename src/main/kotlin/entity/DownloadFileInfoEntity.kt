package entity

class DownloadFileInfoEntity {
    var versionId: Long = 0
    var textVersion: String? = null
    var fiasCompleteDbfUrl: String? = null
    var fiasCompleteXmlUrl: String? = null
    var fiasDeltaDbfUrl: String? = null
    var fiasDeltaXmlUrl: String? = null
    var kladr4ArjUrl: String? = null
    var kladr47ZUrl: String? = null
    var garXMLFullURL: String? = null
    var garXMLDeltaURL: String? = null
    var date: String? = null

    override fun toString(): String {
        return "$versionId, $textVersion, $fiasCompleteDbfUrl, $fiasCompleteXmlUrl, $fiasDeltaDbfUrl, $fiasDeltaXmlUrl, $kladr47ZUrl, $kladr4ArjUrl, $garXMLDeltaURL, $garXMLFullURL, $date"
    }
}