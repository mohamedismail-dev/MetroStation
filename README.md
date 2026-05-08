# 🚇 MetroStation – Smart Cairo Metro Navigator

![Android](https://img.shields.io/badge/Platform-Android-brightgreen)
![Language](https://img.shields.io/badge/Java-orange)
![License](https://img.shields.io/badge/License-MIT-blue)

> **The easiest way to use Cairo Metro:** type the name of any place in Cairo, and the app instantly finds the nearest metro station to you and the nearest station to your destination – in seconds, without needing an internet connection.

---

## ✨ Highlight Feature – “Go Anywhere by Just Typing a Name”

Simply type a street name, district, or landmark (e.g., "Zamalek", "Nasr City"), and the app will:

- Automatically get your current GPS coordinates.
- Convert the place name you entered into geographical coordinates using Geocoding.
- Calculate the nearest metro station to your current location.
- Calculate the nearest metro station to your target destination.
- Then plot the full route: number of stations, estimated travel time, and ticket fare.

**No need to memorize station names – just type where you want to go!**

---

## 📱 All Features

- **Smart Nearest Station Search** – enter any place name and let the app do the rest.
- **Route Planning** – between any two stations across all three Cairo Metro lines.
- **Trip Summary**:
  - List of intermediate stations.
  - Number of stops.
  - Estimated travel time (in minutes).
  - Ticket price (in EGP).
- **Metro Map** – visual map of the three lines with animated directional arrows (English/Arabic).
- **Bilingual Interface** – switch between Arabic and English with one tap.
- **Fully Offline** – station data and coordinates are stored locally; no network needed for route calculation.

---

## 🛠 Tech Stack

- **Language**: Java
- **Architecture**: Native Android (XML + Activities)
- **Key Libraries**:
  - AndroidX, Material Components
  - AirLocation (GPS)
  - AndroidViewAnimations
  - SearchableSpinner (JitPack)
  - Chip Navigation Bar
- **Build**: Gradle 8.6, AGP 8.2.2

---

## 💰 Ticket Prices (based on number of stations – updated 2026)

| Stops | Price (EGP) |
|-------|-------------|
| 1 – 9 | 10 |
| 10 – 16 | 12 |
| 17 – 23 | 15 |
| 24+ | 20 |

> The fare is automatically calculated based on the number of stations you will pass.

---

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/mohamedismail-dev/MetroStation.git
