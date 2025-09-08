package com.example.IN2000_prosjekt.model.weather

data class MetAlertData(
    val features: List<Feature>,
    val lang: String,
    val lastChange: String,
    val type: String
)
data class GeometryMetAlert(
    val coordinates: List<List<List<Any>>>,
    val type: String
)

data class Feature(
    val geometry: GeometryMetAlert,
    val properties: PropertiesMetAlert,
    val type: String,
    val `when`: When
)
data class PropertiesMetAlert(
    val MunicipalityId: String,
    val administrativeId: String,
    val area: String,
    val awarenessResponse: String,
    val awarenessSeriousness: String,
    val awareness_level: String,
    val awareness_type: String,
    val certainty: String,
    val consequences: String,
    val county: List<String>,
    val description: String,
    val event: String,
    val eventAwarenessName: String,
    val geographicDomain: String,
    val id: String,
    val instruction: String,
    val resources: List<Resource>,
    val riskMatrixColor: String,
    val severity: String,
    val title: String,
    val type: String
)
data class Resource(
    val description: String,
    val mimeType: String,
    val uri: String
)
data class When(
    val interval: List<String>
)