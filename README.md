# 🩸 Meus Exames de Colesterol

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat-square&logo=kotlin)
![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=flat-square&logo=jetpackcompose)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Modular-FF8C00?style=flat-square)

Aplicativo Android para registrar, acompanhar e evoluir o histórico de exames laboratoriais, com foco em saúde cardiovascular e controle de colesterol. O projeto foi pensado para oferecer uma experiência simples, segura e rica em informações, permitindo o acompanhamento de dados médicos com organização, gráficos e gestão documental.

---

## ✨ Funcionalidades

- Cadastro completo de exames laboratoriais com laboratório, data, campos e referências.
- Gestão de histórico de exames com edição, exclusão e detalhes por registro.
- Visualização de evolução de indicadores por meio de gráficos e comparações temporais.
- Acompanhamento de perfil do usuário e dados de saúde relacionados.
- Upload e leitura de laudos em PDF e imagens para consulta rápida.
- Scanner de documentos e QR Code/Barcode via câmera.
- Autenticação com Google/Firebase e controle de sessão do usuário.
- Sincronização com backend em nuvem e serviços de dados remotos.
- Monitoramento de crashes e diagnósticos com Firebase Crashlytics.
- Design System reutilizável para manter interface consistente e escalável.

---

## 🛠️ Tecnologias e bibliotecas

- Linguagem principal: [Kotlin](https://kotlinlang.org/)
- UI: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Arquitetura: MVVM, modularização por módulos e separação de camadas
- Injeção de dependência: [Hilt](https://dagger.dev/hilt/)
- Async / reatividade: Kotlin Coroutines e Flow
- Persistência local: [Room](https://developer.android.com/training/data-storage/room)
- Autenticação: Firebase Auth e Google Sign-In
- Backend / sincronização: [Supabase](https://supabase.com/) e Firebase
- PDF / arquivos: PDFBox Android
- Build e automação: Gradle + Kotlin DSL + Version Catalogs
- Plugins customizados: módulos de convenção do Gradle para padronização do projeto

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura modular e orientada a camadas, com foco em separação de responsabilidades, reutilização e manutenção.

### Camadas principais

- app: módulo principal da aplicação, contendo a navegação, telas, ViewModels, use cases específicos da feature e integração com o núcleo do app.
- toolkit/core: infraestrutura compartilhada do projeto, como base classes, extensões, serviços, sincronização, analytics e utilitários transversais.
- toolkit/designsystem: biblioteca de design system, temas, componentes visuais e padrões UI reutilizáveis.
- toolkit/libs/auth: autenticação de usuários, integrações com Google/Firebase e fluxo de sessão.
- toolkit/libs/camera: leitura e análise de câmera, QR Code e scanner.
- toolkit/libs/servicelocation: serviços de localização e workers/background jobs.
- toolkit/build-logic: plugins e convenções para padronizar build, dependências e configuração de módulos.

Essa estrutura favorece:

- manutenção de código em módulos independentes;
- reutilização de componentes e infraestrutura;
- melhor escalabilidade para novos recursos;
- isolamento das regras de negócio e da interface.

---

## 🌳 Estrutura do projeto

```text
appMyExams/
├── app/
│   ├── build.gradle.kts
│   ├── google-services.json
│   ├── proguard-rules.pro
│   └── src/
│       ├── androidTest/
│       │   └── java/com/vald3nir/myexams/ExampleInstrumentedTest.kt
│       ├── main/
│       │   ├── java/com/vald3nir/myexams/
│       │   │   ├── domain/
│       │   │   │   ├── dto/
│       │   │   │   ├── enums/
│       │   │   │   └── validations/
│       │   │   ├── presentation/
│       │   │   │   ├── components/
│       │   │   │   ├── features/
│       │   │   │   │   ├── app/
│       │   │   │   │   ├── evolution/
│       │   │   │   │   ├── exams/
│       │   │   │   │   ├── onboarding/
│       │   │   │   │   └── profile/
│       │   │   │   └── main/
│       │   │   ├── repository/
│       │   │   │   ├── datasource/
│       │   │   │   ├── di/
│       │   │   │   └── usecases/
│       │   │   └── ...
│       │   └── res/
│       └── test/
│           └── java/com/vald3nir/myexams/ExampleUnitTest.kt
│
├── toolkit/
│   ├── build-logic/
│   │   └── convention/src/main/kotlin/
│   │       ├── ApplicationPlugin.kt
│   │       ├── FirebasePlugin.kt
│   │       ├── HiltPlugin.kt
│   │       ├── ModulePlugin.kt
│   │       ├── NetworkPlugin.kt
│   │       ├── RoomPlugin.kt
│   │       ├── SupabasePlugin.kt
│   │       └── com/toolkit/plugs/
│   ├── core/
│   │   ├── build.gradle.kts
│   │   └── src/main/java/com/vald3nir/toolkit/core/
│   │       ├── baseclasses/
│   │       ├── services/
│   │       ├── theme/
│   │       └── utils/
│   ├── designsystem/
│   │   ├── build.gradle.kts
│   │   └── src/main/java/com/vald3nir/toolkit/designsystem/
│   │       ├── components/
│   │       ├── extensions/
│   │       ├── templates/
│   │       ├── theme/
│   │       └── annotations/
│   └── libs/
│       ├── auth/
│       │   └── src/main/java/com/vald3nir/toolkit/auth/
│       ├── camera/
│       │   └── src/main/java/com/vald3nir/toolkit/camera/
│       └── servicelocation/
│           └── src/main/java/com/vald3nir/toolkit/servicelocation/
│
├── docs/
├── gradle/
├── .gitignore
├── .gitmodules
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── README.md
├── settings.gradle.kts
└── .idea/
```

---

## ▶️ Como executar

### Pré-requisitos

- Android Studio com suporte a Kotlin e Compose
- JDK 17+
- Android SDK configurado
- Dispositivo físico ou emulador Android

### Passos

```bash
# clonar o repositório
git clone https://github.com/vald3nir/Android-My-Exams.git

# Atualizar submódulos do projeto (dependências internas)
git submodule update --init --recursive
git submodule update --rebase
git submodule sync

# entrar na pasta do projeto
cd Android-My-Exams

# sincronizar dependências e abrir no Android Studio
./gradlew assembleDebug
```

Ou simplesmente abra o projeto no Android Studio e execute o app em um emulador ou dispositivo conectado.
