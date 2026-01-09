# 🎵 Song Lyrics App

Aplicação Android desenvolvida em **Kotlin** utilizando **Jetpack Compose**, com o objetivo de permitir a **busca, visualização e armazenamento local de letras de músicas**, consumindo uma API REST pública.

---

## 👥 Equipe
  * Afonso Mateus de Oliveira Souza - 552193
  * Gabriel Cavalcante Alves - 555644
  * José Mateus Freire Araújo - 510605

## 📹 Vídeo da aplicação



https://github.com/user-attachments/assets/8a453cdb-2610-48a2-8624-920891ccef66



## 📱 Descrição do que foi programado

Foi desenvolvida uma aplicação Android composta por **três telas principais**, utilizando **Jetpack Compose** para construção da interface e **Navigation Compose** para navegação entre telas.

### 🔹 Tela 1 — Lista de músicas salvas

* Exibe uma lista de músicas previamente salvas pelo usuário.
* Cada item apresenta:

  * Nome da música
  * Nome da banda/artista
* Ao clicar em um item, o usuário é direcionado para a tela de detalhes da música.
* No canto superior direito há um botão para acessar a tela de busca de músicas.

### 🔹 Tela 2 — Detalhes da música

* Exibe:

  * Nome da banda
  * Nome da música
  * Letra completa da música
* Os dados são carregados a partir do armazenamento local do dispositivo.

### 🔹 Tela 3 — Busca de músicas

* Possui dois campos de entrada:

  * Nome da banda/artista
  * Nome da música
* Ao clicar em **Pesquisar**, a aplicação realiza uma requisição HTTP para uma API REST pública que retorna a letra da música.
* Após a exibição da letra, o usuário pode salvá-la localmente.
* As músicas são armazenadas utilizando **SharedPreferences**, em formato JSON.

### 🔹 Tecnologias utilizadas

* Kotlin
* Jetpack Compose
* Material Design 3
* Navigation Compose
* Retrofit
* OkHttp
* Coroutines
* SharedPreferences
* Gson

---

## 🌐 Descrição do Servidor REST escolhido

A aplicação consome a API pública **Lyrics.ovh**, que disponibiliza letras de músicas de forma gratuita.

### 🔹 URL base da API

```
https://api.lyrics.ovh/
```

### 🔹 Rota utilizada

#### Buscar letra de música

```
GET /v1/{artista}/{musica}
```

##### Parâmetros:

* `artista`: Nome da banda ou artista
* `musica`: Nome da música

##### Exemplo de requisição:

```
https://api.lyrics.ovh/v1/Guns N' Roses/Patience
```

##### Exemplo de resposta:

```json
{
  "lyrics": "Shed a tear 'cause I'm missing you..."
}
```

### 🔹 Observações sobre a API

* A API não exige autenticação.
* Pode apresentar instabilidades ocasionais, como:

  * Timeout
  * HTTP 502 (Bad Gateway)
* Por esse motivo, a aplicação trata erros de rede e exibe mensagens adequadas ao usuário.

---

## 💾 Armazenamento de dados

As músicas pesquisadas podem ser salvas localmente no dispositivo utilizando **SharedPreferences**.
Os dados armazenados incluem:

* Nome da banda
* Nome da música
* Letra da música

Isso permite que o usuário visualize as letras mesmo sem conexão com a internet após o salvamento.

---
