# REQUIRIMENTOS DO SISTEMA
Este documento describe os requirimentos para CheckIn especificando que funcionalidade ofrecerá e de que xeito.

## Descrición Xeral

O meu proxecto se trata dunha aplicación Android na cal o dono dun hotel ou negocio relacionado ao turismo pode anotar e xestionar os datos dos clientes que usen os seus servizos, o que se coñece comunmente como "CheckIn". O obxetivo de dito proxecto é facilitar e axilizar ese proceso obrigatorio a todas as persoas que o necesiten.

## Funcionalidades
 1. Creación dunha BD para clientes
 * O programa creará unha base de datos temporal na cal se gardarán os clientes aos que lle fagas checkIn no momento.

 2. Xestión de clientes na BD
 * Se poderá dar de alta a clientes novos.
 * Se poderán modificar datos.
 * Se poderán eliminar clientes.

 3. Xestión do usuario
 * Ver perfil
 * Crear conta

## Tipos de usuarios

*Usuario xerente: Será o usuario que se encargue de facer os checkin aos clientes. Terá acceso total a base de datos de clientes e poderá manexala como él queira.

## Avaliación da viabilidade técnica do proxecto

### Hardware requerido
Precísase dun teléfono móvil con conexión a internet. Simplemente necesita ter o hardware suficiente para executar android 5.0 ou superior. Facendo que os requisitos de hardware sexan sinxelos será máis de ser descargada por un maior número de usuarios.

### Software
O télefono móvil ten que contar con software Android e terá que ser a versión 5.0 ou superior. Xa que a aplicación utiliza firebase para xestionar algúns datos o dispositivo móvil terá que contar con conexión a internet para o seu correcto funcionamento. 
Decidin optar por esta versión de android xa que a gran maioría de móviles utilizan esta versión (según os datos aportados por Android Studio a aplicación funcionaría no 99,3% dos dispositivos android actuais) polo que sería máis fácil para os usuarios cumplir ditos requisitos.
Despois tamén optei pola base de datos na nube xa que ao subirse os datos a firebase se aumenta a seguridade e se minimiza o resgo de perda de datos.

## Interfaces externos
A aplicación contará exclusivamente cunha interfaz de usuario coa cal o usuario poderá interactuar con ela. Os datos que se rexistran en Firebase serán mostrados mediante un RecyclerView polo que non é necesario que o usuario acceda a dita plataforma.

## Análise de riscos e interesados
O impacto positivo serían as persoas que acceden a aplicación mediante o modelo de suscripción mencionado no proxecto. Xa que a aplicación ao estar en Play Store e susceptible de ser descargada e probada por un gran volumen de usuarios. Estos usuarios poderían deixar ideas e comentar erros que ocurran na aplicación, de esa maneira esta aplicación podería mellorar contínuamente gracias aos seus comentarios.
Ao tratarse dunha aplicación .apk é moi complicado que usuarios indeseados poidan acceder ao código polo que nese apartado a aplicación estaría a salvo. 

O problema é que calquera pode probar a aplicación e probar todas as súas funcionalidades polo que podería ser copiado pola competencia fácilmente. Esto é un inconveniente moi difícil de solucionar pero ao mesmo tempo hai que ter en conta que ser o primeiro en implementar algo novo dá bastante ventaxa, polo que non é moi preocupante.

## Actividades
1.- Creación de un sistema de Login e SignIn con Firebase.

2.- Menú coas opcións principais.

3.- Pantalla para introducir os datos da túa vivenda.

4.- Pantalla con EditText para introducir datos.

5.- Comprobación de que todos os datos están correctos e está todo introducido.

6.- Posibilidade de modificar calquer dato antes de subilo a nube.

7.- Introducción dos datos nunha base de datos SQLite temporal e local.

8.- Visualización dos datos SQLite en un RecyclerView.

9.- Subida de datos nunha base de datos online en Firebase.

10.- Construcción dunha interfaz donde mostrar todos os datos colgados en Firebase.

11.- Posibilidade de poder eliminar os datos de Firebase en calquera momento.

## Melloras futuras
Nun futuro o que tería máis prioridade para introducir sería a posibilidade de que os usuarios poidan introducir varias vivendas. De esa forma os usuarios se poderían filtrar os checkin pola vivenda na que foi.

Outra mellora sería introducir unha pequena zona ao final da pantalla de datos na cal os clientes poidan firmar na pantalla e que esa firma se garde co resto dos datos.

Despois tamén se poden aplicar outras mellorar como optimización do código, engadir un modo oscuro para maior comodidade... pero estas teñen menos prioridade.