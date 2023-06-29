# Proyecto final

## Descripción

En este proxecto se vai elaborar unha aplicación en Android na cal o dono dun hotel ou vivenda turística poderá anotar os datos dos clientes que fagan uso das súas instalacións, o que se coñéce comunmente como "check in". Nesta aplicación, a aparte do proceso de "check in" tamén se poderá xestionar e observar ditos datos e introducir os datos e logo da tua vivenda para que aparezan impresos no rexistro de viaxeiros.

Esta idea surxiu a partir de ver como o "check in" é un proceso onde tes que anotar moitos datos (por exemplo nome, fecha de expedición do DNI, fecha de nacemento...) e é algo que tes que facer varias veces ao longo do día e hai que facelo a todos os clientes maiores de 14 anos. É un proceso que necesita moito tempo e é moi fácil deixar algo en blanco ou anotar algo de forma incorrecta. A través dunha aplicación móvil é máis fácil controlar que non se deixa ningún campo en blanco e que os datos son correctos.

Ademáis también é mais rápido xa que non é necesario ter que estar escribindo todos os datos de forma manual.

## Instalación / Posta en marcha
Nun futuro, cando a aplicacion esté na sua fase final se subirá a tenda de playStore para que os posibles clientes poidan descargala de forma fácil. Actualmente non está dispoñible esta posibilidade, por esa razón no proxecto, ![neste mesmo enlace](apk/checkIn.apk), se adxuntará o arquivo .apk para que se poida probar de forma sinxela. Con este arquivo se poderá instalar e executar a aplicación sen ningún problema.

É importante resaltar que ao tratarse dunha aplicación que utiliza firebase, o móvil co que se proba necesita conexión a internet para funcionar correctamente. Tamén é importante que o móvil conte cunha versión de Android 5.0 ou superior.

## Uso

O uso da aplicación tamén é moi sinxela. Simplemente ao iniciala aparecerá unha pantalla onde poderás iniciar sesión cun correo electrónico e un contrasinal. No caso de non ter unha conta terás que pulsar o botón de ¨Registrarse¨ e ahí introducir os datos que se pidan. 

Unha vez dentro teremos varias opcións pero as importantes serán dúas ¨Hacer Check in¨ e ¨Ver check in¨. Antes de pulsar en calquera das dúas opcións, no caso de ter un nome e un logo da túa vivenda, podes pulsar no botón inferior co debuxo dunha casa para así poder subir os datos da túa vivenda e que quede rexistrado no rexistro de viaxeiros. Despois de iso, podes continuar co proceso de check in.
Na primeira opción se verán unha serie de campos que habrá que ir cumplimentando cos datos que se piden. Ao final aparecerán dous botóns que serán ¨Agregar check in¨ e ¨Consultar check in¨. Na primeira opción se agregarán todos os datos e quedarán todos os campos en blanco (por si queres agregar outro usuario). Unha vez finalizado se pulsa na segunda opción e poderás ver todos os usuarios que rexistraches nese momento. Se algún dato o introduciches incorrectamente ou queres cambiar algo, simplemente tes que pulsar sobre a persoa que queiras modificar algún dato e se che devolverá a pantalla anterior para poder cambiar os datos que desexes. Cando estes conforme pulsa en ¨Finalizar¨ e todos os datos se subirán a un servidor en Firebase.
Todas esas consultas as poderás ver na opcion ¨Ver check in¨ na cal poderás eliminar os rexistros que vexas necesarios e ver os datos completos ao pulsar sobre eles.

## Sobre o autor

Son Brais Garrido Blanco, próximamente técnico superior en Desenvolvemento de Aplicacións Multiplataforma. Teño unha gran pasión polo mundo da tecnoloxía pero sobre todo polo mundo da programación.

Os meus puntos fortes son Java, co que se desenvolve a aplicación, tamén teño un gran coñecemento sobre Python e manexo de bases de datos e creación de estas (mediante SQL e Firebase). Tamén teño coñecementos de HTML, CSS e JavaScript.

Me decantei por este proxecto, xa que durante o último ano da miña vida me dediquei a xestionar unha vivenda de uso túristico. Ahí puiden ver de primeira man como funciona este sector e cales son as principais carencias que ten. Entre todas as tarefas que fixen a que me parecía máis tediosa e confusa era facer o "check in" aos clientes. A medida que fun aprendendo máis sobre desenvolvemento en android puiden ver o fácil e útil que podería ser ter unha aplicación que me axudara nesa tarefa.

## Licencia

Licencia usada: [Licencia Open Source Initiative](LICENSE)

## Índice

1. Anteproxecto
    * 1.1. [Idea](doc/templates/1_idea.md)
    * 1.2. [Necesidades](doc/templates/2_necesidades.md)
2. [Análise](doc/templates/3_analise.md)
3. [Planificación](doc/templates/4_planificacion.md)
4. [Deseño](doc/templates/5_deseño.md)
5. [Producción](doc/templates/6_producion.md)


## Guía de contribución

Se poderá contribuir optimizando o código da aplicación, realizando test automáticos para comprobar a solidez dos controladores de datos ou incluso añadindo novas funcionalidades.
Aínda que non é obligatorio e se trata de software libre, se alguén o edita para os seu uso persoal, sería de agradecería que se compartira para que máis xente poida facer uso de él ao igual que se fixo con este proxecto.