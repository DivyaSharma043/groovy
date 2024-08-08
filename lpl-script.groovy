import groovy.json.JsonSlurper

def getAbnormalCount(String jsonInput) {
    def slurper = new JsonSlurper()
    def data = slurper.parseText(jsonInput)

    int count = 0
    data.each { clientRequestData ->
        List<Map> clientInputDataList = clientRequestData.clientInputData
        clientInputDataList.each { inputData ->
            def mapData = inputData.metaDataMap
            String status = mapData.get("status")
            if (("H".equals(status) || "L".equals(status)) && !"done".equals(status)) {
                count++
            }
        }
    }
    if (count == 0) {
        return 14
    }
    return count
}

return getAbnormalCount(input)
