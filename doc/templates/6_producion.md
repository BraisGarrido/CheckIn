# Fase de produción

# Manual técnico e de administración

### Información relativa á instalación ou despregamento:

A instalación funciona igual que unha aplicación común. Simplemente é necesario que instales o archivo .apk ![proporcionado no proxecto](apk/checkIn.apk) nun móbil que teña como mínimo android 5.0. Tamén como a aplicación necesita contactar con Firebase para xestionar os usuarios e subir os datos, o móbil necesita dunha conexión a internet permanente para o seu correcto funcionamento.

### Información relativa á administración do sistema, é dicir, tarefas que se deberán realizar unha vez que o sistema estea funcionando, como por exemplo

No momento no que a aplicación esté en funcionamento seguramente non se dispoña de usuario, por ese motivo se terá que crear un novo usuario introducindo os datos que se pidan na pantalla de Signin. Despois de eso simplemente será iniciar sesión de forma normal e comezar a elaborar checkin. 

Non é necesario facer copia de seguridade xa que unha vez que se suban os datos a nube a base de datos é eliminada automáticamente. Os datos de Firebase están a bo recaudo e se actualizan automáticamente sempre que se introduzan novos datos.

### Información relativa ó matemento do sistema

No momento no que se detecte algún erro será arreglado o antes posible. A aplicación ao estar colgada en Play Store, os usuarios poderán deixar comentarios na páxina da aplicación e estos serán revisados periódicamente. 

As novas actualizacións e correccións de bugs se publicarán na páxina da aplicación en Play Store e así os usuarios o poderán descargar de forma automáticas gracias as ferramentas que proporciona a tenda.

Tamén hai que ter en conta que ao estar a aplicación conectada a Firebase, se se trata dunha modificación pequena dalgún parámetro da aplicación, se poderá correxir mediante a funcionalidade Remote Config que proporciona Firebase. De esa forma os usuarios non terán que descargar nada.

# Manual de usuario

### Formación de usuarios 
Non será necesario formar aos usuarios xa que a interfaz está diseñada para ser intuitiva. En caso de que un gran volumen de usuarios teñan dudas coa aplicación me plantearia rediseñar a interfaz ou crear un pequeno vídeo tutorial na propia aplicación.

### Instrucións iniciais
Cando a aplicación esté en marcha por primeira vez necesitarás crearte unha conta, para eso terás que pulsar no botón "Registrarse" e cubrir os campos cos datos que se piden. Unha vez dentro terás dúas opcións "Nuevo Check in" e "Ver Check in". Si pulsas na segunda non verás nada xa aínda non introduciches os datos. 

Si pulsas na primeira opción pasarás a unha pantalla onde verás unha serie de campos que terás que ir cubrindo e despois lle terás que pulsar en "Agregar check in". Unha vez pulsado se borrarán todos os datos confirmando que todo se rexitrou correctamente. Cando termines de facer todos os Check in que necesites pulsas na opción "Consultar check in" e verás nun recadro o nome e primeiro apelido de todas as persoas as que lles anotastes os datos. Ahí poderás modificar os datos si o consideras necesario. Unha vez finalizado pulsa en "Finalizar" para que os datos se suban a nube.

Unha vez feito iso si que podes entrar en "Ver check in" e ver todos os datos que tes na nube. Se verán da misma forma que antes, só que agora se pulsas sobre unha de elas poderás ver todos os datos.

Aparte se contas con nome e logo do teu negocio, na pantalla principal pulsando sobre o botón co debuxo dunha casa podes meter os datos e logo da túa vivenda para que estos datos tamén aparezan reflexados no rexistro de viaxeiros.

### FAQ

* Cantos check in podo facer?
Non hai unha cantidade límite de check in.

* Durante canto tempo se gardarán os check in?
Todos os check in gardados non se eliminarán ata polo menos 5 anos despois de ser rexistrados, de esta forma seguimos respetando o Artigo 16 de Protección de seguridade ciudadana de España e damos algo de tempo extra de margen por si é necesario. 

* Canto tempo dura a versión de proba?
A versión de proba dura 15 dende o momento no que te rexistras.

# Protección de datos de carácter persoal

Debido a que se trata dunha aplicación de check in, esta garda moitos datos de persoas de todo tipo de persoas e nacionalidades. Por eso mismo o manexo de esos datos quedan totalmente baixo a responsabilidade do usuario. O/Os programadores da aplicación simplemente proporcionamos unha base de datos en Firebase, pero en ningún momento veremos esos datos. Tamén seguindo o Artigo 16 de Protección de seguridade ciudadana de España e dando tempo extra, todos os datos se eliminarán de forma automática despois de 5 anos dende o momento no que se rexistraron.

En canto a información dos usuarios tampouco teremos acceso xa que os contrasinais en Firebase están encriptados, deixando so a vista os correos electrónicos.