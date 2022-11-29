Projet de Baptiste CLOCHARD
master 1 informatique université de Lyon Lumière 2.

Le code source de l'application se trouve dans le dossier balleauprisonnier.

Le projet peut être lancé en se plaçant dans le dossier balleauprisonnier et en exécutant la commande "mvn javfx:run"

Un .exe de l'application exécutable sur windows x64 peut être trouvé dans le dossier portable_windows. Il peut être exécuté par simple double clic.
La génération de ce .exe peut être réalisée à l'aide de la commande "mvn gluonfx:build" dans l'invite de commande "x64 native tools command", référez vous au rapport pour connaitre les prérequis.

De même un fichier executable de l'application pour linux x se trouve dans le dossier portable_linux. Il peut être généré à l'aide de la commande "mvn gluonfx:build". Pour connaître les prérequis pour linux vous pouvez vous réferez à la documentation de gluonfx : https://docs.gluonhq.com/#platforms_linux

Le .exe doit être laissé à côté du dossier assets, mais il peut être exécuté ailleur sur n'importe quel système windows, même dépourvu de javafx ou de java.

