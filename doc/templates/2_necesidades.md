# ESTUDO DE NECESIDADES E MODELO DE NEGOCIO

## Xustificación das necesidades detectadas que cubre o sistema a desenvolver
O principal problema e que o proceso de "check in" require de anotar moitos datos e a pesar de ser algo que se fai moitas veces ao longo do día, segue sendo algo que se fai de forma manual. Esto pode levar a que se cometan moitos erros e ao tratarse de documentos manuais fai que sexa máis difícil elaborar copias de seguridade.

O meu proxecto facilitaría e axilizaría ese proceso. Ao tratarse dunha aplicación e máis fácil controlar que os datos que se introducen son os correctos e que non se deixa nada en blanco. Aparte a aplicación ao gardar esos datos, fai que sexa moito máis fácil tratar con eles. Os datos ao estar gardados na nube fai que o hosteleiro non se teña que preocupar de perdelos e podería chegar a imprimilos físicamente por si o necesita.

O obxectivo principal da aplicación é axilizar e pulir o proceso de "check in" para poder facilitar ese proceso aos hosteleiros ou as persoas que teñen unha vivenda turística. Ademáis ao ser un proceso dixital axuda bastante a hora de facer copias de seguridade de esos datos. A aplicación contaría cunha interfaz intuitiva para que así os usuarios que non teñan un gran coñecemento sobre tecnoloxía poida manexala sen nengún problema.

Este proxecto responde a necesidade de automatizar o proceso de check in e facelo máis fácil para todos os hosteleiros. Tamén engade a posibilade de manexar mellor os datos gardados xa que se poderán facer copias de seguridade e incluso poder enviar unha copia de esos datos aos clientes que o solicinten. 
     
Como mencionei anteriormente a mellor forma de mellorar esta situación é a través dunha aplicación móvil. Gracias a gran cantidade de dispositivos móviles que hai e o extendido que está o uso de aplicacións móviles, desenvolvelo para esta plataforma é a mellor forma de que o poidan usar un gran número de hostaleiros (dende donos de grandes hoteles ata donos de vivendas turísticas).

Se resolvería esta situación creando unha aplicación intiutiva creando unha aplicación de check in o máis accesible posible para os clientes. Xa con esta aplicación se pode controlar moito mellor que os datos que se introducen son correctos e que non se deixa nada en blanco. Esto conseguiría un mellor tratamentos dos datos e tamén unha maior facilidade a hora de facer copias de seguridade.
 
* Os medios cos que dispoño poderíamos contar Gitlab como un deles, xa que nese repositorio se farán as actualizacións do proxecto. Outro medio necesario será Android Studio no cal se desenvolverá o proxecto e as futuras actualizacións e mantemento da mesma. Para controlar os usuarios e datos que se rexitren se contará con Firebase que en nun principio (cando hai pouco volumen de usuarios) é gratuíto, pero se nun futuro a aplicación tuvera éxito se pasaría considerar alquilar servidores propios ou pagar polas versións premium de Firebase. A pesar de iso, o volumen de datos que permite o servicio gratuito de Firebase é moi elevado.Firebase tamén me da a disposición un panel de control no cal podo controlar todos os datos que se suben e os usuarios que están rexistrados, polo que no caso de que a aplicación falle a hora de borrar datos ou usuarios o podo facer manualmente desde ahí.Aquí un exemplo de como é o panel de usuarios en Firebase:
![panel de control Firebase](doc/img/panelControl_firebase.png)
 
* Por un lado se fará a actividade principal que será a de realizar o proceso de check in. Por outro lado se fará un control de usuarios a través de Firebase.

* A metodoloxía que seguirei será crear a aplicación paralelamente mentres manexo a entrada de usuarios con Firebase. Ao rematar esa parte comezarei a personalizar a aplicación mentres fago control de erros.
     
* O proxecto se podería levar a cabo perfectamente con unha soa persona.
     
* Cóntase co tempo estimado para facer o traballo dado polo centro, que serían 125h.
    
* Para implementar a primeira fase da aplicación as 125h do centro serían suficientes. Se nun futuro se queren implementar novas funcionalidades como servidor propio para gardar datos ou poder usar a cámara do móvil para gardar os datos poderíamos estar falando de máis de 200h.

## Posibilidades de comercialización (viabilidade, competidores…).
1.	Viabilidade.

    Para elaborar unha aplicación de ese estilo non é necesario dunha gran cantidade de maquinaria. Polo menos para unha primeira posta en marcha da aplicación simplemente é necesario contar cun ordenador que consega executar Android Studio é cun móvil para poder executar e probar os cambios que se vaian realizando en dita aplicación. O control de usuarios e de datos sería mediante Firebase que nun principio sería gratuíta ata que se alcanzara unha cantidade de usuarios e de datos moi elebada. Para as actualizacións e corrección de erros se utilizaría gitlab xa que é a plataforma proposta polo centro.

    En caso de que nun futuro a aplicación teña éxito habería que plantearse mudar de Firebase e alquilar servidores propios ou incluso pagar pola versión premium de Firebase. Pero para que iso suceda tería que haber un volumen de usuarios moi elebado.
        
    Non existe ningún impedimento técnico actualmente xa que a propia Google nos proporcionaría casi todas as ferramentas e librerías necesarias para este proxecto.
        
    Os costes do proxecto son moi baixos. Principalmente como costes iniciais podemos contar o gasto eléctrico do ordenador no que se elaborará dita aplicación e o gasto de tempo e esforzo da persoa encargada en facer dita aplicación.
        
    Con estos gastos sería moi fácil obter beneficios cun sistema de suscripción mensual ou pola integración de anuncios dentro da mesma. Máis adiante especificarei as formas de obter beneficios.

        
2.	Competencia.

    Ao tratarse dun sector bastante específico non existen moitas aplicación neste mercado. A aplicación que actualmente domina o mercado ten un sistemna na que cobra aos usuarios por cada "check in" que fagan na sua aplicación. Isto fai que moitos hostaleiros que non teñen un negocio moi grande opten por seguir facendo este proceso a man. Existen outras apliacións que piden outros requisitos como por exemplo usar obligatoriamente a sua web para publicitar o teu hotel ou outras que directamente son vendidas aos hostaleiros. Como se pode ver case non existen apicacións de este estilo que se distribuian de forma libre, e as que sí o fan son pouco asequibles.

    Existen programas de xestión hoteleira nos cales se permite realizar check in a clientes. Pero esas aplicacións non están centradas en ese apartado e ademáis solo están dispoñibles para ordenador e solo si as compras directamente.

## Ideas para a súa comercialización.

Esta aplicación iría dirixida a un público moi específico polo que a publicidade ten que ir dirixida a eles. A idea principal sería patrocinarse directamente nas redes sociais xa que en esas plataformas son utilizadas diariamente por moitos hostaleiros. Así teríamos máis posibilidades de ser vistos.
    
Tamén sería importante ter un bo posicionamento web SEO para así, cando algun novo hosteleiro busque por unha alternativa ao "check in" tradicional apareza o noso producto.
    
Pensando na forma de poder obter beneficios cheguei a conclusión que a mellor forma para comezar sería a través de unha suscripción mensual na cal terás acceso completo a aplicación. A aplicación estaría dispoñible en play store e poderían crearse unha conta gratuítamente, a partir de ahí terían o plazo dun mes para poder probala de forma libre e a partir de ahí tería que suscribirse para poder seguir realizando "check in". O usuario seguiría podendo acceder aos "check in" que realizou e podería seguir descargando esos datos independentemente de que pagara suscripción ou non.
    
Opto por esta decisión xa que me parece a mellor forma de facela accesible para un maior número de usuarios. De esta forma os usuarios non terán que preocuparse da cantidade de check in que realicen. Nun futuro se podería plantear un modelo de publicidade integrada na aplicación, pero esa opción me parece menos atractiva.