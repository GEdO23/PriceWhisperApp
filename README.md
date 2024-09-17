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

O **Price Whisper Mobile App** faz parte da solução **Price Whisper**, voltada para otimizar a precificação de produtos no setor varejista, utilizando machine learning. Desenvolvido em **Kotlin**, este aplicativo móvel oferece uma interface intuitiva para que gestores possam gerenciar produtos, visualizar insights estratégicos de preços e tomar decisões mais informadas diretamente de seus dispositivos móveis.

O app se integra com APIs e outros sistemas backend, como Firebase e serviços Java/.NET, garantindo a sincronização contínua de dados em tempo real e proporcionando uma experiência completa e eficiente para o usuário final.

## Problema e Solução

No setor varejista, precificar produtos de forma eficiente é essencial para garantir a competitividade e a lucratividade. O **Price Whisper** visa resolver o problema da precificação subótima por meio de análises dinâmicas e dados em tempo real, utilizando machine learning para gerar recomendações de preços.

O aplicativo móvel é uma peça fundamental dessa solução, permitindo que os gestores acessem essas informações e façam ajustes em qualquer lugar, integrando-se perfeitamente ao ecossistema do **Price Whisper**.

## Funcionalidades Principais

1. **Visualização de Produtos**: Exibe a lista de produtos disponíveis, com informações detalhadas.
2. **Gerenciamento de Preços**: Permite a atualização e controle de preços de forma rápida e intuitiva.
3. **Exibição de Insights e Dados Dinâmicos**: Apresenta análises e sugestões de ajuste de preços baseadas em machine learning. (futuro)
4. **Autenticação de Usuários**: Utiliza Firebase para login e registro de usuários. (futuro)
5. **Integração com APIs**: Se comunica com APIs REST para sincronização de dados e backend. (futuro)
6. **Suporte Multilíngue**: Possui suporte para diferentes localidades e idiomas, incluindo português (Brasil).

## Arquitetura do Projeto

O app Kotlin segue uma arquitetura modular e organizada em pacotes, facilitando a manutenção e escalabilidade. A estrutura principal inclui:

- **Pacote `utils`**: Funções utilitárias, como formatação de preços e controle de estoque.
- **Pacote `ui`**: Contém as atividades responsáveis pela interface com o usuário.

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

- **Firebase Realtime Database**: Usado para armazenar e buscar dados de produtos.
    - URL: `https://pricewhisper-auth-cc2c8-default-rtdb.firebaseio.com/products`
- **Backend Java**: Para gerenciamento de produtos (futuro).
- **Backend .NET**: Para gerenciamento de usuários (futuro).
- **Algoritmo de Machine Learning**: Integração futura via Flask para recomendação e otimização de preços

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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.okhttp)
    implementation(libs.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
```
