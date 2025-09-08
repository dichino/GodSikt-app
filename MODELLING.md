
## Tekstlig beskrivelse av use case
**Navn:** værdata for valgt posisjon 	

**Aktør:**
-	Bruker
-	Applikasjon
-	API 
  - Locationforecast 
  - MetAlert
-	Mapbox

**Prebetingelser:** 
- Appen må ha tilgang til internett

**Postbetingelser:**
- Brukeren trykker på en annen posisjon på kartet

**Hovedflyt:**
1.	Bruker åpner applikasjonen på enheten sin
2.	Applikasjonen ber om tillatelse til å få tilgang til enhetens posisjonsdata
3.	 Hvis brukeren velger å gi tillatelse:
       •	Applikasjonen bruker enhetens GPS for å få brukerens nåværende posisjon.
4.	Applikasjonen viser et kart der brukeren kan se sitt nåværende posisjon.
5.	 Brukeren trykker på ønsket posisjon på kartet for å velge et annet område.
6.	Applikasjonen markerer den valgte posisjonen på kartet.
7.	Brukeren trykker på en knapp på kartet for å hente værdata for den valgte posisjonen.
8.	Kortet presenterer værdata for den valgte posisjonen

**Alternativflyt:**

- Hvis bruker velger å ikke gi tillatelse:
  - Applikasjonen gir brukeren en ny sjanse til å gi tillatelse. Hvis brukeren igjen velger å ikke gi tillatelse, avsluttes prosessen
  
- Hvis API-ene ikke henter ut data 
    - Systemet viser en melding i kortet om at værdata ikke kan hentes for den valgte posisjonen.

## Mermaid kode
**Sekvensdiagram:**

sequenceDiagram

        participant Bruker
        participant Applikasjon
        participant Mapbox
        participant API

    Bruker->>+Applikasjon: Start applikasjon og trykk på startknappen
    Applikasjon->>-Bruker: Spør om tillatelse til posisjon
    Bruker->>+Applikasjon: Gi tillatelse
    Bruker->>+Applikasjon: Naviger til startskjermen (kartet)
    Bruker->>+Applikasjon: Velg posisjon på kartet
    Applikasjon->>+Mapbox: Markér valgt posisjon på kartet
    Mapbox-->>-Applikasjon: Valgt posisjon er markert
    Bruker->>+Applikasjon: Trykk på værdataknappen
    Applikasjon->>+API: Fetch location forecast
    API-->>-Applikasjon: Returner LocationForecast
    Applikasjon->>+API: Fetch MetAlert
    API-->>-Applikasjon: Returner MetAlert
    Applikasjon->>+Bruker: Vise kort med værdata

**Klassediagram**

classDiagram

    User --> MainActivity
    MainActivity --> PermissionManager
    MainActivity --> MapScreen
    MainActivity --> WeatherDataCard
    PermissionManager --> MainActivity : requestLocationPermission()
    MainActivity : startApp()
    MainActivity : navigateToMapScreen()
    MapScreen : selectLocationOnMap()
    MapScreen : markPosition()
    MapScreen : showWeatherData()
    WeatherDataCard : displayWeatherData()

    class User {
        +startApp()
        +grantPermission()
        +selectLocationOnMap()
        +viewWeatherData()
    }
    class MainActivity {
        +startApp()
        +navigateToMapScreen()
        +requestLocationPermission()
        +showWeatherDataCard()
    }
    class PermissionManager {
        +requestLocationPermission()
        +onPermissionGranted()
        +onPermissionDenied()
    }
    class MapScreen {
        +selectLocationOnMap()
        +markPosition()
        +showWeatherData()
    }

    class WeatherDataCard {
        +displayWeatherData()
    }

**Aktivitetsdiagram**

*Flytskjema for hvordan appen fungerer: *


flowchart TD

    A[Brukeren starter applikasjonen] --> B[Brukeren trykker på Start-knappen]
    B --> C[Brukeren blir spurt om å gi tillatelse til sin posisjon]
    C --> D{Tillatelse gitt?}
    D -- Ja --> E[Naviger til kartskjermen]
    D -- Nei --> F[Be om tillatelse igjen]
    F --> C
    E --> G[Brukeren velger en posisjon på kartet]
    G --> H[Marker valgt posisjon]
    H --> I[Brukeren trykker på værdataknappen]
    I --> J[Vis kort med værdata fra APIene]
    E --> K[Brukeren trykker på sentreringsknappen]
    K --> L[Sentrer posisjonen til brukeren på kartet]
    E --> M[Brukeren trykker på informasjonsknappen]
    M --> N[Vis hvordan appen fungerer]
    E --> O[Brukeren trykker på SOS-knappen]
    O --> P[Varsler kystvakten]

    E --> Q[Brukeren navigerer til skilt skjerm fra navbaren]
    Q --> R[Brukeren velger hvilken type fareskilt å trykke på]
    R --> S[Naviger til valgt fareskilt]

    E --> V[Brukeren navigerer til om skjerm fra navbaren]
    V --> W[Brukeren trykker på om oss-knappen]
    V --> X[Brukeren trykker på personvern-knappen]
    V --> Y[Brukeren trykker på vilkår og betingelser-knappen]
    V --> Z[Brukeren trykker på om datakilder-knappen]

    W --> U1[Vis Om oss informasjon]
    X --> U2[Vis Personvern informasjon]
    Y --> U3[Vis Vilkår og betingelser informasjon]
    Z --> U4[Vis Om datakilder informasjon]

    V --> E[Naviger tilbake til kartskjermen]
    Q --> E[Naviger tilbake til kartskjermen]
    S --> Q[Naviger tilbake til skilt skjermen]

    %% Additional navigation paths to indicate all possible directions
    E <-->|Naviger mellom kartskjermen og skilt skjermen| Q
    E <-->|Naviger mellom kartskjermen og om skjermen| V
    Q <-->|Naviger mellom skilt skjermen og om skjermen| V
