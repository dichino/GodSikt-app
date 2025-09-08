# GodSikt-prosjektet
En Android app for å sjekke relevante værdata for hobby-båtførere i Norge. Appen ble planlagt, designet og laget fullstendig fra grunnen av studenter fra Universitetet av Oslo som prosjektarbeid for kurset [IN2000 – Software Engineering med prosjektarbeid](https://www.uio.no/studier/emner/matnat/ifi/IN2000/).

*Data er fra open access API fra MET: MetAlerts og Locationforecast*

Dette prosjektet var laget av
- Ayuub Abdulkadir Hassan (ayuubah@ifi.uio.no)
- Dichino Thang Nguyen (dichinon@ifi.uio.no)
- Eirik Øye Andersen (eirikoa@ifi.uio.no)
- Jacob Vindal Starheim (jacobvs@ifi.uio.no)
- Samatar Abdi Hassan (samatarh@ifi.uio.no)
- Yusuf Ali Ismail Sheik Nuur (yasheikn@ifi.uio.no)

## GodSiktApp
Android appen for prosjektet GodSikt ble skrevet  med Jetpack Compose (API-nivå 33)

Denne appen er skrevet i Kotlin med bruk av Jetpack Compose og moderne Android-utviklingsverktøy og -biblioteker. Vi har benyttet følgende biblioteker og verktøy:
- Mapbox Android SDK (v11.3.0) for kartfunksjonalitet, inkludert Mapbox Compose-utvidelse (v11.3.0).
- AndroidX Compose-biblioteket (v1.6.6) for oppbygging av det moderne brukergrensesnittet.
- AndroidX Navigation-komponentene (v2.7.7) for navigasjon i appen.
- Ktor (v2.3.7) for nettverkskommunikasjon.
- Coil Compose (v2.6.0) for bildehåndtering.
- Room (v2.5.2) for lokal lagring av data.
- Kotlin Coroutines (v1.7.3 og v1.6.4) for asynkron programmering.

## Funksjonaliteter
- Sanntidsposisjon
- Værdata for valgt posisjon
- SKiltforklaring
- SOS-varsel

## Dokumentasjon
For ytterligere informasjon prosjektet henivses det til:

### Archietecture
ARCHITECTURE.md beskriver arkitekturen som er benyttet i appen, inklusivt objektorienterte prisnipper og design patterns. 

### Modelling
MODELLING.md har beskrivelse og diagrammer for et funksjonelt krav, samt et flytskjema for hele appen.

### Rapport
Rapporten innlevert sammen med prosjektet inneholder en mer omfattende beskrivelse av:
- Presentasjon
- Brukerdokumentasjon
- Produktdokumentasjon
- Prosessdokumentasjon
- Refleksjon
- Avslutning

## Hvordan man kjører appen
For å kjøre `GodSiktApp` på en Android-enhet eller emulator, må du ha Android Studio nedlastet, og så følge disse trinnene:

1. **Åpne i Android Studio**: Åpne prosjektet i Android Studio ved å velge `File > Open...` og navigere til katalogen der du har prosjektet.

2. **Konfigurer en Android-emulator**:
    - Gå til `Tools > Device Manager` i Android Studio.
    - Øverst til høyre og klikk på `+` eller `Create Virtual Device`.
    - Velg en Android-enhet og trykk `Next`.
    - Velg en Android-versjon med API-nivå 33 eller høyere og trykk `Next`.
    - Velg navn og trykk `Finish`.
    - Start emulator ved å klikke på den i listen over virtual devices.

3. **Bygg og kjør appen**:
    - Velg en virtual device fra dropdown-menyen øverst til høyre i Android Studio.
    - Trykk på ikonet for en Android-enhet (det ser ut som en telefon med en figur) i toolbardelen øverst til venstre.
    - Valg `Run 'app'` fra menyen som vises eller trekant-knappen på toppen.

Husk at for å få mest mulig ut av denne appen, bør du ha en stabil internettforbindelse siden appen bruker nettverkskommunikasjon for å hente data.




