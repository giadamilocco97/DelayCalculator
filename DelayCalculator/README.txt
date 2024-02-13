DelayCalculator:
Il progetto ha lo scopo di produrre un file csv con le informazioni riguardanti i cinque treni che hanno subito piú
ritardi e quanto ritardo hanno fatto.

Si riceve in input due file csv:
    - planned.csv che contiene in ordine cronologico i treni e l'orario di passaggio previsto ad una determinata
    stazione,
    - actual.csv che contiene i treni e l'orario effettivo di passaggio in una determinata stazione.

Partendo da questi due file, ho creato 3 oggetti che possano contenere le informazioni memorizzate nei csv: TrainRecord,
 ActualTrainRecord e PlannedTrainRecord.
TrainRecord é una classe astratta che contiene le informazioni principali e condivise sia da PlannedTrainRecord che da
ActualTrainRecord, che sono:
    - trainNumber, una stringa che contiene il numero identificativo del treno;
    - stationName, una stringa che contiene la stazione in cui sta passando;
    - trainTime, un ZonedDateTime che contiene l'orario di passaggio nel formato ISO-8601 in cui viene specificato anche
     il valore del timezone.

PlannedTrainRecord é una classe che estende TrainRecord, non ha ulteriori parametri aggiuntivi in quanto non necessari e
 serve per memorizzare le informazioni riguardanti gli orari pianificati dei treni.
ActualTrainRecord, come PlannedTrainRecord, é una classe che estende TrainRecord e non ha ulteriori parametri e serve
per memorizzare le informazioni riguardanti gli orari effettivi di passaggio dei treni.

In seguito ho implementato una classe che legga il file csv, ReadCSVFile. Questa classe nel metodo readCSV, prende in
ingresso una stringa contenente il nome del file comprensiva dell'estensione e trasforma come prima cosa il file csv in
una lista di lista di stringhe utilizzando come delimitatore dei campi la virgola e in seguito in una lista di
PlannedTrainRecord nel caso in cui il file che sto leggendo é planned.csv altrimenti in una lista di ActualTrainRecord
se sto leggendo il file actual.csv.

A questo punto, partendo dalla lista dei treni pianificati, filtro la lista per ottenere una nuova lista che contenga
solamente i numeri distinti dei treni.
Ora, ciclo per ogni numero del treno contenuto nella lista e per entrambe le liste dei pianificati e gli effettivi
filtro quella lista per il numero del treno in modo tale da trovare tutte le occorrenze nella lista del treno
specificato, e utilizzando la dimensione della lista recupero l'ultimo record di entrambe le liste cosicché sia
possibile calcolare la differenza tra l'orario di passaggio previsto e quello effettivo.
Ho scelto di utilizzare questo approccio perché non é necessario calcolare la differenza di orario per ogni passaggio
del treno ma mi serve solamente quello dell'ultimo passaggio.

Per memorizzare le informazioni riguardanti i treni che hanno fatto piú ritardo ho creato una classe: OutputTrainRecord
che contiene le variabili:
    - trainNumber;
    - destination, che é quindi l'ultima stazione di passaggio;
    - plannedTime, un ZonedDateTime che contiene l'orario di passaggio previsto;
    - actualTime, un ZonedDateTime che contiene l'orario di passaggio effettivo.
Ho inserito anche un getter che effettua il calcolo della differenza in secondi tra l'orario pianificato e l'orario
effettivo.
Questa classe implementa Comparable<Object> in quanto per ottenere la lista con i 5 treni piú ritardatari, mi é
necessario ordinare in modo decrescente l'oggetto per il ritardo.

A questo punto posso utilizzare i due record ottenuti nel ciclo di prima e posso instanziare un nuovo OutputTrainRecord
che contiene il numero del treno, la destinazione e l'orario previsto che recupero dal plannedTrainRecord e l'orario
effettivo che recupero dall'actualTrainRecord e aggiungo questo nuovo record dell'output alla lista che utilizzerò per
creare il csv finale.
Prima peró di generare il csv, filtro la collezione degli oggetti di output in modo tale che vengano ordinati per il
ritardo e prendo i primi 5 valori della lista per ottenere quelli con piú ritardo.

Per generare il file csv di output, ho creato una classe CreateCSVFile, in cui ho specificato l'intestazione che dovrà
avere il file: Numero Treno, Destinazione, Ora Pianificata, Ora Effettiva, Ritardo. Nel metodo generateCsv viene creato
un file csv denominato: output.csv e creo una StringBuilder che conterrà il contenuto del csv, ciclo la lista degli
oggetti di output che ho come valore di ingresso del metodo in modo tale da trascrivere i valori del numero del treno,
destinazione, orario pianificato, orario effettivo e ritardo come stringhe concatenate e utilizzando la virgola come
carattere delimitatore del campo e il ritorno a capo come delimitatore della riga.


Esecuzione del codice:
- per ambiente Linux:
eseguire a riga di comando per compilare il progetto: javac Main.java
sempre a riga di comando eseguire: java Main
all'interno della directory del progetto verrà aggiunto il file: output.csv.
- per ambiente Macos:
utilizzare IDE come IntelliJ o Eclipse per eseguire il comando run della classe Main.java.
Per eclipse utilizzare il comando 'Run As' facendo click destro del mouse sulla classe Main e selezionando la voce:
Java Application.
Su Intellij sempre dalla classe Main cliccare il tasto play in alto a destra oppure fare tasto destro sulla classe Main
ed eseguire il comando: Run 'Main.main()'.