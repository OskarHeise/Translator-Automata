# Translator-Automata

# Progetto di Laboratorio

**Descrizione Finale – Febbraio 2024**
**Progetto di Laboratorio 2023/24**
**Traduttore Semplice**
**Versione Finale**
**Bini, Radicioni, Schifanella C.**
**Febbraio 2024**

## Indice
1. [Composizione del Gruppo Studenti](#1-composizione-del-gruppo-studenti)
2. [Sottomissione](#2-sottomissione)
3. [Modalità di Esame di Laboratorio](#3-modalità-di-esame-di-laboratorio)
4. [Note Importanti](#4-note-importanti)
5. [Calcolo del Voto Finale](#5-calcolo-del-voto-finale)
6. [Validità del Testo di Laboratorio](#6-validità-del-testo-di-laboratorio)
7. [Implementazione di un DFA in Java](#7-implementazione-di-un-dfa-in-java)
8. [Analisi Lessicale](#8-analisi-lessicale)
9. [Analisi Sintattica](#9-analisi-sintattica)
10. [Traduzione Diretta dalla Sintassi](#10-traduzione-diretta-dalla-sintassi)
11. [Generazione del Bytecode](#11-generazione-del-bytecode)

---

## 1. Composizione del Gruppo Studenti
Il progetto di laboratorio viene sviluppato da un gruppo composto da 1 a un massimo di 3 membri. Il gruppo dovrebbe essere composto da studenti dello stesso turno che discuteranno con il docente del loro turno. Tuttavia, è anche consentito discutere del progetto di laboratorio in un gruppo di studenti provenienti da turni diversi. In questo caso, tutti gli studenti del gruppo discuteranno con lo stesso docente. Esempio: Tizio (turno T1) e Caio (turno T2) decidono di fare il progetto insieme. Lo presentano e vengono convocati dal Prof. Sempronio il giorno X. In quel giorno, sia Tizio che Caio si presentano e ricevono valutazioni dal Prof. Sempronio (docente del T1, anche se Caio fa parte del turno T2, il cui docente di riferimento è il Prof. Calpurnio).

## 2. Sottomissione
Il progetto consiste in:
1. Codice sorgente.
2. Un breve rapporto che riassuma le scelte progettuali effettuate.
Il progetto viene presentato compilando il seguente modulo Google Form, accessibile con le credenziali istituzionali:
- [Link al Google Form](https://forms.gle/kDDaGRoW5mrdhi696)
Questo modulo richiederà il caricamento di:
- Il progetto stesso (un singolo file in formato .tgz o .zip, NON .rar).
- Cognome, nome, ID e email di ciascun membro del gruppo.
Dopo aver caricato il progetto, verrai convocato dal docente per la discussione (vedi Sezione 1 in caso di gruppo composto da studenti di turni diversi). Nota: Compila un solo modulo per ogni progetto (non uno per ciascun membro del gruppo). Qualsiasi altra sottomissione prima dell'appuntamento cancellerà la data dell'appuntamento.
La sottomissione deve essere effettuata almeno 10 giorni prima degli esami scritti per consentire al docente di pianificare le discussioni:
- Se sottomesso almeno 10 giorni prima di una data di esame, il docente proporrà una data di discussione entro l'esame successivo.
- Altrimenti, la data sarà dopo l'esame successivo.
Esempio: Per garantire un appuntamento per la discussione del progetto durante l'esame del 24/01/2023, inviare entro le 23:59 del 14/01/2023.

## 3. Modalità di Esame di Laboratorio
Il progetto descritto in questo documento può essere discusso se sottomesso entro il 30 novembre 2023. A partire da dicembre 2023, verrà discusso il progetto assegnato durante l'anno accademico 2023/24.
Tutti i membri del gruppo devono partecipare alla discussione. La valutazione del progetto è individuale ed espressa in trentesimi. Durante la discussione:
- Ti verrà chiesto di spiegare il progetto.
- Potrebbero essere poste domande relative al programma del corso "Linguaggi Formali e Traduttori", non necessariamente legate allo sviluppo del progetto.
Devi ottenere un punteggio minimo di 18 su 30 per essere ammesso all'esame scritto. Se superi la discussione del progetto, il punteggio ottenuto ti permetterà di partecipare all'esame scritto per i cinque esami successivi dopo la data di superamento. Non ci sono eccezioni o deroghe a questo periodo di validità.
In caso di insuccesso, lo studente può ripresentarsi solo almeno un mese dopo la data di insuccesso.
Si prega di notare che il punteggio del progetto conta per 1/4 del voto finale del corso di Linguaggi Formali e Traduttori.

## 4. Note Importanti
- Per discutere del progetto di laboratorio, è necessario aver superato prima la prova scritta relativa al modulo teorico. Il progetto di laboratorio deve essere superato nella sessione d'esame in cui si supera la prova scritta, altrimenti è necessario sostenere nuovamente la prova scritta.
- Presentare del codice "funzionante" non è condizione sufficiente per superare la prova di laboratorio. In altre parole, è possibile essere respinti presentando codice funzionante (se lo studente dimostra di non avere familiarità sufficiente con il codice e i concetti correlati).
- Il progetto di laboratorio può essere svolto individualmente o in gruppi formati da un massimo di 3 studenti. Anche se il codice è stato sviluppato in collaborazione con altri studenti, i punteggi ottenuti dai singoli studenti sono indipendenti. Ad esempio, con lo stesso codice presentato, è possibile che uno studente ottenga 30, un altro 25 e un altro ancora sia respinto.
- Durante la discussione, potrebbe essere richiesto di apportare modifiche al codice del progetto. È quindi opportuno presentarsi all'esame con una conoscenza adeguata del progetto e degli argomenti di teoria correlati.

## 5. Calcolo del Voto Finale
I voti della prova scritta e della prova di laboratorio sono espressi in trentesimi. Il voto finale è determinato calcolando la media pesata del voto della prova scritta e del laboratorio, secondo il loro contributo in CFU (con una eventuale modifica nel caso in cui lo studente ha scelto di sostenere una prova orale), cioè:
\[ \text{voto finale} = \frac{\text{voto dello scritto} \times 2 + \text{voto del laboratorio}}{3} \pm \text{eventuale esito orale} \]

## 6. Validità del Testo di Laboratorio
Il presente testo di laboratorio è valido sino alla sessione di febbraio 2024.

## 7. Implementazione di un DFA in Java
Lo scopo di questo esercizio è l'implementazione di un metodo Java che sia in grado di discriminare le stringhe del linguaggio riconosciuto da un automa a stati finiti deterministico (DFA) dato.
Il primo automa che prendiamo in considerazione è definito sull'alfabeto {0, 1} e riconosce le stringhe in cui compaiono almeno 3 zeri consecutivi.
L'implementazione Java del DFA è mostrata nel file `DFA.java`. L'automa è implementato nel metodo `scan` che accetta una stringa `s` e restituisce un valore booleano che indica se la stringa appartiene o meno al linguaggio riconosciuto dall'automa. Lo stato dell'automa è rappresentato da una variabile intera `state`, mentre la variabile `i` contiene l'indice del prossimo carattere della stringa `s` da analizzare. Il corpo principale del metodo è un ciclo che, analizzando il contenuto della stringa `s` un carattere alla volta, effettua un cambiamento dello stato dell'automa secondo la sua funzione di transizione. Notare che l'implementazione assegna il valore -1 alla variabile `state` se viene incontrato un simbolo diverso da 0 e 1. Tale valore non è uno stato valido, ma rappresenta una condizione di errore irrecuperabile.

## 8. Analisi Lessicale
L'analizzatore lessicale sviluppato in questo progetto legge un testo e restituisce una sequenza di token corrispondenti alle unità lessicali del linguaggio. I token del linguaggio sono descritti nella Tabella 1.
- Gli identificatori corrispondono all'espressione regolare: `(a+...+z+A+...+Z)(a+...+z+A+...+Z+0+...+9)*`
- I numeri corrispondono all'espressione regolare `0 + (1 + ... + 9)(0 + ... + 9)*`
L'analizzatore lessicale ignora tutti i caratteri riconosciuti come "spazi" e segnala la presenza di caratteri illeciti, come ad esempio `#` o `@`.
L'output dell'analizzatore lessicale è una sequenza di token, come mostrato negli esempi della Sezione 2.

## 9. Analisi Sintattica
L'esercizio 3.1 richiede di scrivere un analizzatore sintattico a discesa ricorsiva per parsificare espressioni aritmetiche molto semplici, composte soltanto da numeri non negativi, operatori di somma e sottrazione, moltiplicazione e divisione, e simboli di parentesi. L'analizzatore deve riconoscere le espressioni generate dalla grammatica `G_expr` descritta nel documento.
Il programma deve fare uso dell'analizzatore lessicale sviluppato in precedenza. L'output del programma deve consistere nell'elenco di token dell'input seguito da un messaggio che indica se l'input corrisponde o meno alla grammatica.

## 10. Traduzione Diretta dalla Sintassi
L'esercizio 4.1 richiede di modificare l'analizzatore sintattico dell'esercizio 3.1 in modo da valutare le espressioni aritmetiche semplici, seguendo uno schema di traduzione diretta dalla sintassi fornito nel documento.
Il terminale `NUM` ha l'attributo `value`, che è il valore numerico del terminale, fornito dall'analizzatore lessicale.
La struttura del programma è fornita nel documento.

## 11. Generazione del Bytecode
L'obiettivo di questa parte del laboratorio è realizzare un traduttore per i programmi scritti nel linguaggio P, visto nell'esercizio 3.2. Il traduttore deve generare bytecode eseguibile dalla Java Virtual Machine (JVM). La generazione del bytecode avviene tramite un linguaggio mnemonico che fa riferimento alle istruzioni della JVM e viene tradotto nel formato .class dal programma assembler Jasmin.
La struttura del programma per la generazione del bytecode è fornita nel documento.
