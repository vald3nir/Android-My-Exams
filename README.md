# 🩸 Meus Exames de Sangue

![Android](https://img.shields.io/badge/Platform-Android-green?style=flat-square&logo=android)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple?style=flat-square&logo=kotlin)
![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=flat-square&logo=jetpackcompose)
![Architecture](https://img.shields.io/badge/Architecture-Modular%20MVVM-orange?style=flat-square)

O **Meus Exames de Sangue** é um aplicativo Android desenvolvido para o registro, gerenciamento e acompanhamento do histórico de exames laboratoriais. O app permite criar, editar e agrupar resultados, consultar detalhes, acompanhar a evolução temporal de indicadores de saúde por meio de gráficos e anexar documentos (PDFs e imagens).

Projetado com foco em alta modularidade, escalabilidade, experiência do usuário (UX) e sincronização segura de dados com backend em nuvem.

---

## ✨ Funcionalidades

- **Gerenciamento de Exames:** Cadastro, edição e listagem detalhada de exames (data, laboratório, campos e valores de referência).
- **Evolução de Indicadores:** Gráficos históricos para acompanhamento da variação de parâmetros ao longo do tempo.
- **Anexos e Documentos:** Leitura, exportação e visualização de arquivos PDF e imagens.
- **Digitalização (Scanner):** Captura rápida de exames e laudos via câmera do dispositivo.
- **Autenticação Segura:** Login social (Google/Firebase) com persistência e controle de sessão.
- **Sincronização Cloud:** Backup e sincronização em tempo real via Supabase/Firebase.
- **Monitoramento:** Telemetria de erros e crashes via Firebase Crashlytics.

---

## 🛠️ Tecnologias e Bibliotecas

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **Interface (UI):** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Design System reativo e modular)
- **Injeção de Dependência:** [Hilt](https://dagger.dev/hilt/)
- **Assincronismo:** Kotlin Coroutines & `Flow`
- **Banco de Dados Local:** [Room](https://developer.android.com/training/data-storage/room)
- **Backend & Cloud:** Supabase (Remote Data) & Firebase (Auth, Crashlytics, App Distribution)
- **Processamento de Documentos:** PDFBox Android
- **Build System:** Gradle (Kotlin DSL, Version Catalogs `libs.versions.toml` e KSP)

---

## 🏗️ Arquitetura e Estrutura

O projeto adota uma **arquitetura multi-módulo** dividida em camadas bem definidas, seguindo as recomendações oficiais de arquitetura Android:

```text
├── app/                  # Entrypoint da aplicação e navegação principal
└── toolkit/              # Módulos reutilizáveis
    ├── core/             # Utilitários globais e extensões
    ├── designsystem/     # Componentes de UI, temas e tokens
    ├── build-logic/      # Plugins customizados de build do Gradle
    └── feature-*/        # Módulos de funcionalidade (auth, camera, location, etc.)
