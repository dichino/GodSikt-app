# Apparkitektur og designmønstre

## MVVM Designmønster og Unidirectional Data Flow (UDF)

Appen vår er bygget på en trelagsarkitektur som består av et presentasjonslag, et forretningslogikklag og et datatilgangslag. Denne arkitekturen fremmer klar separasjon av bekymringer, noe som gjør koden enklere å vedlikeholde, teste og videreutvikle. Vi benytter oss av MVVM (Model-View-ViewModel) designmønsteret, som deler appen inn i Model, View og ViewModel. Modellen håndterer data og forretningslogikk, View viser data til brukeren, og ViewModel fungerer som et mellomledd som håndterer logikken mellom Model og View. I tillegg bruker vi Unidirectional Data Flow (UDF) for å sikre en enkeltveis flyt av data, noe som reduserer kompleksiteten og gjør det enklere å forstå hvordan data flyter gjennom systemet.

## Organisering av kode

For å opprettholde lav kobling og høy kohesjon, er koden organisert i separate moduler med klare ansvarsområder. Lav kobling oppnås ved å bruke veldefinerte grensesnitt mellom moduler, som reduserer avhengigheter. Høy kohesjon sikres ved å gruppere relaterte funksjoner sammen, slik at all logikk relatert til en spesifikk funksjonalitet, som for eksempel værdata, er samlet i en spesifikk ViewModel og tilhørende Model. Dette gjør det enklere å vedlikeholde og utvide koden over tid, samtidig som det gir en klar struktur for utviklere å jobbe etter.

## Teknologier og verktøy

Vi bruker Android Studio som vårt hovedutviklingsmiljø, sammen med Kotlin som primærspråk for apputviklingen vår, og Mapbox for kartfunksjonaliteten. Vi har også integrert en lokal database ved hjelp av Room for å håndtere vedvarende data lokalt på en effektiv måte. Vi har valgt å støtte Android API-nivå 33 (Android 13) og nyere, i henhold til retningslinjer fra Android-utviklere som krever at nye apper målrettes mot API-nivå 33 eller høyere fra og med 31. august 2023. Dette valget gir oss tilgang til de nyeste funksjonene i Android-plattformen, inkludert sikkerhetsforbedringer, ytelsesoptimaliseringer og nye brukergrensesnittkomponenter. Det sikrer også at vi når ut til en bred brukerbase med nyere enheter, noe som er viktig for å holde appen relevant og konkurransedyktig.
