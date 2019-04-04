# Moviefy

## Capas de la aplicación y finalidad de cada una de sus clases

### Presentación: Esta capa abarca los MVP creados para cada pantalla de la aplicación.

Clases:
- Main MVP: Vista principal de la aplicación donde se visualiza la lista de películas y las categorías.
- Movie MVP: Vista de detalle de una película
- Splash MVP: Primer contacto con la aplicación
- MovieAdapter: Adapter utilizado en RecyclerView de películas
- MovieViewHolder: Vista del ítem de la película dentro de MovieAdapter
- ImageHelper y ImageHelperImpl: Interfaz e implementación para facilitar la carga de imágenes
- DateUtils: Utils para el formateo de fechas
- Extentions: Funcionalidades comunes en una activity creadas como extensiones, como ocultar teclado y entrar en modo pantalla completa.

Cada MVP está compuesto por cuatro clases:
- Activity: Vista
- Contract: Contrato entre la vista y el presenter
- Presenter: Lógica de la capa de presentación
- Module: Clase utilizada para la inyección de dependencias con Dagger

### Red: 
- NetworkingModule: Clase utilizada para la inyección de dependencias con Dagger
- IMoviefyServer: Interfaz que define las llamadas al servidor: path, queries.
- ConnectionHelper: Identifica si se tiene coneccion o si se está offline al utilizar la aplicación.
- RetrofitHelper: Crea una instancia de Retrofit para ser utilizada al realizar las llamadas.
- MoviesResponse: Clase utilizada para mapear la respuesta de los servicios que devuelven una lista de películas. Se utiliza Gson para este mapeo.
- RepositoryModule: Clase utilizada para la inyección de dependencias con Dagger
- MovieRepository y MovieRepositoryImpl: Interfaz e implementación encargadas de la obtención de datos sobre las películas

### Persistencia: Clases útiles y tipos de datos utilizados para la persistencia local de las películas.
- RepositoryModule: Clase utilizada para la inyección de dependencias con Dagger
- Genre, Movie, MovieCategory, MovieDetail, ProductionCompany: Tipos de dato utilizados para el manejo local de las películas.
- MovieOffline: Tipo de dato necesario para la persistencia local de los datos al utilizar Requery.
- DatabaseModule: Clase utilizada para la inyección de dependencias con Dagger
- DatabaseServer y RequeryServerImpl: Interfaz e implementación para la funcionalidad de la base de datos: select, update, delete.

## Responda y escriba dentro del Readme con las siguientes preguntas:
### 1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

El principio de responsabilidad única tiene como propósito que cada clase de la aplicación tenga una única funcionalidad, de esta forma, la clase tendría una única razón para cambiar. Es el primero de los principios SOLID.

### 2. Qué características tiene, según su opinión, un “buen” código o código limpio?

- Consistencia en cuanto a nomenclatura de clases/recursos/variables
- Cumplir con los principios solid
- Utilizar un patrón de diseño
- Que sea testeable, es decir, no incorporar lógica dentro de las views, sólo en los presenter/controladores.
- Consistencia en lenguaje de programación. Si bien Java y Kotlin son compatibles, prefiero que solo uno de los lenguajes esté presente en las clases creadas por los desarrolladores.
- No mantener codigo comentado
- Respetar y mantener el mismo estilo de código

