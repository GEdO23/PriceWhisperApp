# OM Corp.
# Price Whisper Mobile App

## Equipe de desenvolvimento
| RM       | NOME                        |
|:---------|:----------------------------|
| RM551575 | André Sant’Ana Boim         | 
| RM98251  | Marcelo Hespanhol Dias      |
| RM99632  | Gabriel Eringer de Oliveira |
| RM551988 | Gustavo Imparato Chaves     |

## Descrição do Projeto

O Price Whisper Mobile App é um componente essencial da solução Price Whisper, que visa otimizar a precificação no setor varejista utilizando Machine Learning. Este aplicativo móvel, desenvolvido em Kotlin, permite a visualização e o gerenciamento de produtos e preços diretamente do dispositivo móvel, integrando-se com várias APIs e serviços backend para fornecer uma experiência completa e eficiente.

## Funcionalidades Principais

- **Visualização de Produtos**: Permite aos usuários visualizar a lista de produtos disponíveis.
- **Gerenciamento de Preços**: Facilita a atualização e o gerenciamento de preços dos produtos.
- **Integração com APIs**: Conecta-se a APIs para buscar e enviar dados relacionados aos produtos e preços.
- **Suporte Multilíngue**: Suporte para diferentes locais e idiomas, como Português do Brasil.

## Arquitetura do Projeto

O app Kotlin segue uma arquitetura modular e organizada em pacotes, facilitando a manutenção e escalabilidade. A estrutura principal inclui:

- **Pacote `utils`**: Contém utilitários como `CurrencyUtil` e `StockUtil` para formatação de preços e estoque.
- **Pacote `ui`**: Contém as atividades e fragmentos que compõem a interface do usuário.

## Configurações Necessárias

Para configurar o ambiente e rodar o projeto localmente, siga os passos abaixo:

1. Clone o repositório:
    ```sh
    git clone https://github.com/GEdO23/PriceWhisper.git
    cd PriceWhisper
    ```

2. Abra o projeto no Android Studio.

3. Instale as dependências:
    - O Android Studio deve automaticamente baixar e instalar as dependências listadas no arquivo `build.gradle`.

4. Rode o app:
    - Conecte um dispositivo Android ou inicie um emulador.
    - Clique em "Run" no Android Studio.

## APIs Utilizadas

- **Firebase Realtime Database**: Utilizado para armazenar e buscar dados de produtos.
    - URL: `https://pricewhisper-auth-cc2c8-default-rtdb.firebaseio.com/products`
- **Backend Java**: Microsserviço para gerenciamento de produtos (futuro).
- **Backend .NET**: Microsserviço para gestão de usuários (futuro).
- **Algoritmo de Machine Learning**: Implementado via Flask para análise e recomendação de preços (futuro).

## Instruções de Deploy

Atualmente, o app não requer um setup especial para deployment. Basta seguir as instruções de configuração para rodar o app em um dispositivo ou emulador Android.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Android SDK**: Ferramentas e bibliotecas para desenvolvimento Android.
- **Firebase**: Plataforma para desenvolvimento de aplicativos móveis e web.

## Dependências

As principais dependências do projeto estão listadas no arquivo `build.gradle`:

```gradle
dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.5.21"
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.google.firebase:firebase-database:20.0.3'
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
}
```